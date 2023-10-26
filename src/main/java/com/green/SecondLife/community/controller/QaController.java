package com.green.SecondLife.community.controller;

import com.green.SecondLife.community.service.QaService;
import com.green.SecondLife.community.vo.BoardCommentListVO;
import com.green.SecondLife.community.vo.BoardQaListVO;
import com.green.SecondLife.community.vo.QaImgVO;
import com.green.SecondLife.member.vo.MemberVO;
import com.green.SecondLife.member.vo.SubMenuVO;
import com.green.SecondLife.util.ConstantVariable;
import com.green.SecondLife.util.UploadUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qa")
public class QaController {
    private final QaService qaService;

    //Q&A 게시판 출력
    @RequestMapping("/qaBoardList")
    public String qaBoardList(Model model, BoardQaListVO boardQaListVO, SubMenuVO subMenuVO, HttpSession session){
        //페이지 정보 세팅
        int totalDataCnt = qaService.selectBoardCnt(); //전체 게시글 갯수 조회해서
        boardQaListVO.setTotalPageCnt(totalDataCnt);//세터 호출해서 전체 게시글 갯수 전달
        boardQaListVO.setPageInfo();//변수값 설정한 메소드 호출(상속관계라 사용가능)

        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        model.addAttribute("loginInfo", loginInfo);

        //게시글 목록 조회
        List<BoardQaListVO> qaBoardList = qaService.selectQaBoardList(boardQaListVO);
        model.addAttribute("qaBoardList", qaBoardList);
        return "board/qa/qa_board";
    }
    //등록버튼 누르면 등록 페이지로 이동
    @GetMapping("/regQaBoardForm")
    public String regQaBoardForm(){
        return "board/qa/reg_board";
    }
    //글 등록 페이지에서 등록하기 누르면 글 등록 쿼리 실행
    @PostMapping("/regBoard")
    public String regBoard(BoardQaListVO boardQaListVO, HttpSession session, MultipartFile[] qaImg){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        int nextBoardNum = qaService.selectNextQaBoardNum();//빈 값 채울 pk 조회해서 저장

        //만약 이미지를 첨부 했다면?
        if(qaImg != null){
            System.out.println("이미지 첨부");
            //파일 업로드 메소드를 변수에 저장
            List<QaImgVO> qaImgList = UploadUtil.qaMultiFileUpload(qaImg);
            //하나씩 돌면서 vo boardNum안에 다음 boardNum 전달
            for(QaImgVO e : qaImgList){
                e.setQaBoardNum(nextBoardNum);
            }
            boardQaListVO.setQaBoardWriter(loginInfo.getMemberId());//vo안에 writer를 세션에 저장된 id로 갖고옴
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
        }
        else {//파일 첨부를 하지 않았다면
            boardQaListVO.setQaBoardNum(nextBoardNum);
            boardQaListVO.setQaBoardWriter(loginInfo.getMemberId());//vo안에 writer를 세션에 저장된 id로 갖고옴
            System.out.println("이미지 미첨부");
            System.out.println(boardQaListVO);
            //만약 password값이 비어있다면?
            if (boardQaListVO.getQaBoardPassword() == null){
                //공개 등록 쿼리 실행
                qaService.insertQaBoardOpen(boardQaListVO);
            } else {
                //비공개 등록 쿼리 실행
                qaService.insertQaBoardClose(boardQaListVO);
            }
        }

        return "redirect:/qa/qaBoardList";
    }
    //글 tr태그를 클릭했을때 해당글의 상세페이지 이동
    @RequestMapping("/boardDetail")
    public String boardDetail(int qaBoardNum, String qaCheckPwInput, Model model, BoardQaListVO boardQaListVO, HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        //저장된 비밀번호 조회 쿼리
         String qaPw = qaService.selectQaPw(qaBoardNum);
         //로그인을 했다면?
         if(loginInfo != null){// 저장되어진 비밀번호와 입력한 비밀번호가 같거나, 관리자라면?

            if (qaPw.equals(qaCheckPwInput) || "ADMIN".equals(loginInfo.getMemberRoll())){
                //board이름으로 디테일정보 던지기
                model.addAttribute("board", qaService.selectQaBoardDetail(qaBoardNum));
                //조회수 증가
                qaService.updateQaBoardCnt(qaBoardNum);
                //댓글 조회해서 html로 던지기
                model.addAttribute("comment", qaService.selectQaBoardComment(qaBoardNum));
                return "board/qa/board_detail";
            }
            else {
                return "board/qa/qa_result";
            }
         } else {// 로그인 하지 않았을 때, 저장되어진 비밀번호와 입력한 비밀번호가 같다면?
             if (qaPw.equals(qaCheckPwInput)){
                 //board이름으로 디테일정보 던지기
                 model.addAttribute("board", qaService.selectQaBoardDetail(qaBoardNum));
                 //조회수 증가
                 qaService.updateQaBoardCnt(qaBoardNum);
                 //댓글 조회해서 html로 던지기
                 model.addAttribute("comment", qaService.selectQaBoardComment(qaBoardNum));
                 return "board/qa/board_detail";
             }
             else {
                 return "board/qa/qa_result";
             }
         }
    }
    //상세 페이지에서 댓글 작성버튼 클릭하면 비동기로 insert 쿼리 실행
    @ResponseBody
    @PostMapping("/qaBoardComment")
    public boolean qaBoardComment(BoardCommentListVO boardCommentListVO, HttpSession session){
        //로그인 정보가 없다면 댓글 작성하지 못하게
        if (session.getAttribute("loginInfo") == null){//로그인 정보가 없을 때
            return false;//board.js로 false리턴
        }
        //로그인 정보가 있다면 if문 실행되지않고 쿼리가 실행된 후 true 리턴
        qaService.insertQaBoardComment(boardCommentListVO);
        return true;//board.js로 true리턴
    }
    //글 상세페이지에서 삭제버튼 클릭하였을 때
    @GetMapping("/deleteQaBoard")
    public String deleteQaBoard(int qaBoardNum){
        qaService.deleteQaBoard(qaBoardNum);
        return "redirect:/qa/qaBoardList";
    }
    //수정페이지에서 수정 버튼을 눌렀을 때 수정 쿼리 실행
    @RequestMapping("/updateQaBoard")
    public String updateQaBoard(BoardQaListVO boardQaListVO){
        //수정 쿼리
        qaService.updateQaBoard(boardQaListVO);
        //수정이 완료되면 해당 게시글 상세페이지로 freeBoardNum=숫자 데이터를 던질 수 있다.
        return "redirect:/qa/boardDetail?qaBoardNum=" + boardQaListVO.getQaBoardNum();
    }
    //상세 페이지에서 댓글 삭제버튼 클릭하면 delete 쿼리 실행
    @ResponseBody
    @PostMapping("/qaDeleteComment")
    public void qaBoardComment(int commentId){
        qaService.deleteQaBoardComment(commentId);
    }
    //상세 페이지에서 댓글 수정 버튼 클릭하면 update 쿼리 실행
    @ResponseBody
    @PostMapping("/qaUpdateComment")
    public void qaDeleteComment(BoardCommentListVO boardCommentListVO){
        qaService.updateQaBoardComment(boardCommentListVO);
    }
}
