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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qa")
public class QaController {
    private final QaService qaService;

    //Q&A 게시판 출력
    @RequestMapping("/qaBoardList")
    public String qaBoardList(Model model, BoardQaListVO boardQaListVO, SubMenuVO subMenuVO){
        //페이지 정보 세팅
        int totalDataCnt = qaService.selectBoardCnt(); //전체 게시글 갯수 조회해서
        boardQaListVO.setTotalPageCnt(totalDataCnt);//세터 호출해서 전체 게시글 갯수 전달
        boardQaListVO.setPageInfo();//변수값 설정한 메소드 호출(상속관계라 사용가능)

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
    public String regBoard(BoardQaListVO boardQaListVO, HttpSession session, MultipartFile[] qaImg){//인터페이스



        //파일 업로드
        List<QaImgVO> qaImgList = UploadUtil.qaMultiFileUpload(qaImg);





//        //다음에 들어가야할 QA_BOARD_NUM 조회
//        //int qaBoardNum = qaService.selectNextQaBoardNum();
//        //다음에 들어갈 QA_CODE조회
//        String qaCode = qaService.selectNextQaCode();
//        //이미지정보가 모두 들어갈 통
//        List<QaImgVO> qaImgList = new ArrayList<>();
//        //이미지 정보 하나 들어갈 통
//        qaImgVO.setQaOriginFileName(originFileName);
//        qaImgVO.setQaAttachedFileName(attachedFileName);
//        qaImgVO.setQaCode(qaCode);
//        //qaImgVO.setQaBoardNum(qaBoardNum);
//        System.out.println(qaImgVO);
//        //통에다가 채우기
//        qaImgList.add(qaImgVO);
//        boardQaListVO.setQaImgList(qaImgList);

        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        boardQaListVO.setQaBoardWriter(loginInfo.getMemberId());
        //글 등록 쿼리 실행
        qaService.insertQaBoard(boardQaListVO);
        //이미지 정보를 db에 등록 쿼리
        qaService.insertQaImgs(boardQaListVO);
        return "redirect:/qa/qaBoardList";
    }
    //글 제목 클릭했을때 해당글의 상세페이지 이동
    @RequestMapping("/boardDetail")
    public String boardDetail(int qaBoardNum, Model model){
        model.addAttribute("board", qaService.selectQaBoardDetail(qaBoardNum));
        //조회수 증가
        qaService.updateQaBoardCnt(qaBoardNum);
        //댓글 조회해서 html로 던지기
        model.addAttribute("comment", qaService.selectQaBoardComment(qaBoardNum));
        return "board/qa/board_detail";
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
