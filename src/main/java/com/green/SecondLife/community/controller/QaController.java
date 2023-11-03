package com.green.SecondLife.community.controller;

import com.green.SecondLife.community.service.QaService;
import com.green.SecondLife.community.vo.BoardCommentListVO;
import com.green.SecondLife.community.vo.BoardQaListVO;
import com.green.SecondLife.community.vo.QaImgVO;
import com.green.SecondLife.member.vo.MemberVO;
import com.green.SecondLife.member.vo.SubMenuVO;
import com.green.SecondLife.util.UploadUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qa")
public class QaController {
    private final QaService qaService;

    //Q&A 게시판 출력
    @RequestMapping("/qaBoardList")
    public String qaBoardList(Model model, BoardQaListVO boardQaListVO, SubMenuVO subMenuVO, Authentication authentication){
        //페이지 정보 세팅
        int totalDataCnt = qaService.selectBoardCnt(); //전체 게시글 갯수 조회해서
        boardQaListVO.setTotalDataCnt(totalDataCnt);//세터 호출해서 전체 게시글 갯수 전달
        boardQaListVO.setPageInfo();//변수값 설정한 메소드 호출(상속관계라 사용가능)

        model.addAttribute("authentication", authentication);

        //게시글 목록 조회
        List<BoardQaListVO> qaBoardList = qaService.selectQaBoardList(boardQaListVO);
        model.addAttribute("qaBoardList", qaBoardList);
        return "board/qa/qa_board";
    }
    //등록버튼 누르면 등록 페이지로 이동
    @GetMapping("/regQaBoardForm")
    public String regQaBoardForm(SubMenuVO subMenuVO){
        return "board/qa/reg_board";
    }
    //글 등록 페이지에서 등록하기 누르면 글 등록 쿼리 실행
    @PostMapping("/regBoard")
    public String regBoard(BoardQaListVO boardQaListVO, Authentication authentication, MultipartFile[] qaImg, SubMenuVO subMenuVO){
        int nextBoardNum = qaService.selectNextQaBoardNum();//빈 값 채울 pk 조회해서 저장

        //파일 업로드 메소드를 변수에 저장
        List<QaImgVO> qaImgList = UploadUtil.qaMultiFileUpload(qaImg);
        //하나씩 돌면서 vo boardNum안에 다음 boardNum 전달
        for(QaImgVO e : qaImgList){
            e.setQaBoardNum(nextBoardNum);
        }

        boardQaListVO.setQaBoardWriter(authentication.getName());//vo안에 writer를 세션에 저장된 id로 갖고옴
        boardQaListVO.setQaBoardNum(nextBoardNum);//다음 들어갈 글 번호 조회된 것을 빈값으로 채움
        boardQaListVO.setQaImgList(qaImgList);

        //만약 password값이 비어있다면?
        if (boardQaListVO.getQaBoardPassword() == null){
            //공개 등록 쿼리 실행
            qaService.insertQaBoardOpen(boardQaListVO);
        } else {
            //비공개 등록 쿼리 실행
            qaService.insertQaBoardClose(boardQaListVO);
        }

        return "redirect:/qa/qaBoardList";
    }
    //글 tr태그를 클릭했을때 해당글의 상세페이지 이동
    @RequestMapping("/boardDetail")
    public String boardDetail(Model model, BoardCommentListVO boardCommentListVO, SubMenuVO subMenuVO, Authentication authentication){

        //데이터베이스에 저장된 비밀번호를 조회해서 저장함
        String qaPw = qaService.selectQaPw(boardCommentListVO.getCommentNum());
        
        boolean isAuthentication = true;
        if(authentication != null){
            User user =  (User) authentication.getPrincipal();
            List<GrantedAuthority> authoList = new ArrayList<>(user.getAuthorities());

            List<String> strAuthoList = new ArrayList<>();
            for(GrantedAuthority e : authoList){
                strAuthoList.add(e.getAuthority());
            }
            isAuthentication = user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        }
        BoardQaListVO board = qaService.selectQaBoardDetail(boardCommentListVO.getCommentNum());//해당글 상세정보 조회하고
        board.setQaBoardPassword(qaService.selectQaPw(boardCommentListVO.getCommentNum()));

        //   공개이 거나,         로그인을 했고                관리자라면 프리패스!
        if(qaPw == null || (authentication != null && isAuthentication)){
            //board이름으로 디테일정보 던지기
            model.addAttribute("board", board);//아우터조인
            model.addAttribute("authentication", authentication);
            //조회수 증가
            qaService.updateQaBoardCnt(boardCommentListVO.getCommentNum());
            //댓글 조회해서 html로 던지기
            model.addAttribute("comment", qaService.selectQaBoardComment(boardCommentListVO.getCommentNum()));
            return "board/qa/board_detail";
        } else if (qaPw.equals(boardCommentListVO.getQaCheckPwInput())){ // 비공개
            //board이름으로 디테일정보 던지기
            model.addAttribute("board", board);//아우터조인
            //조회수 증가
            qaService.updateQaBoardCnt(boardCommentListVO.getCommentNum());
            //댓글 조회해서 html로 던지기
            model.addAttribute("comment", qaService.selectQaBoardComment(boardCommentListVO.getCommentNum()));
            model.addAttribute("qaCheckPwInput", boardCommentListVO.getQaCheckPwInput());
            return "board/qa/board_detail";
        } else {//비밀번호 틀렸다면
            return "board/qa/qa_result";//alert창 띄우기
        }
    }
    //상세 페이지에서 댓글 작성버튼 클릭하면 insert 쿼리 실행
    @PostMapping("/qaBoardComment")
    public String qaBoardComment(BoardCommentListVO boardCommentListVO, RedirectAttributes redirectAttributes){
        qaService.insertQaBoardComment(boardCommentListVO); //댓글 작성 쿼리
        redirectAttributes.addFlashAttribute("boardCommentListVO", boardCommentListVO);
        return "redirect:/qa/boardDetail"; //디테일 컨트롤러로 다시 이동할 때 값 가져가기
    }
    //글 상세페이지에서 삭제버튼 클릭하였을 때
    @GetMapping("/deleteQaBoard")
    public String deleteQaBoard(int qaBoardNum){
        qaService.deleteQaBoard(qaBoardNum);
        return "redirect:/qa/qaBoardList";
    }
    //수정 모달에서 수정 버튼을 눌렀을 때 수정 쿼리 실행--------------------------------------------------
    @RequestMapping("/updateQaBoard")
    public String updateQaBoard(BoardQaListVO boardQaListVO, BoardCommentListVO boardCommentListVO, RedirectAttributes redirectAttributes){
        qaService.updateQaBoard(boardQaListVO);//수정 쿼리
        boardCommentListVO.setCommentNum(boardQaListVO.getQaBoardNum());//세터로 commentNum을 qaBoardNum으로 수정
        redirectAttributes.addFlashAttribute("boardCommentListVO", boardCommentListVO);//commentNum, qaCheckPwInput값을 가지고 다시 디테일로

        return "redirect:/qa/boardDetail";
    }
    //상세 페이지에서 댓글 삭제버튼 클릭하면 delete 쿼리 실행
    @PostMapping("/qaDeleteComment")
    public String qaDeleteComment(BoardCommentListVO boardCommentListVO, RedirectAttributes redirectAttributes){
        qaService.deleteQaBoardComment(boardCommentListVO.getCommentId());//삭제 쿼리
        redirectAttributes.addFlashAttribute("boardCommentListVO", boardCommentListVO);//commentNum, qaCheckPwInput값을 가지고 다시 디테일로

        return "redirect:/qa/boardDetail";
    }
    //상세 페이지에서 댓글 수정 버튼 클릭하면 update 쿼리 실행
    @PostMapping("/qaUpdateComment")
    public String qaUpdateComment(BoardCommentListVO boardCommentListVO, RedirectAttributes redirectAttributes){
        qaService.updateQaBoardComment(boardCommentListVO);//수정 쿼리
        redirectAttributes.addFlashAttribute("boardCommentListVO", boardCommentListVO);
        return "redirect:/qa/boardDetail";
    }
}
