package com.green.SecondLife.community.controller;

import com.green.SecondLife.community.service.QaService;
import com.green.SecondLife.community.vo.BoardCommentListVO;
import com.green.SecondLife.community.vo.BoardQaListVO;
import com.green.SecondLife.member.vo.MemberVO;
import com.green.SecondLife.util.ConstantVariable;
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
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qa")
public class QaController {
    private final QaService qaService;

    //Q&A 게시판 출력
    @RequestMapping("/qaBoardList")
    public String qaBoardList(Model model, BoardQaListVO boardQaListVO){
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
    public String regBoard(BoardQaListVO boardQaListVO, HttpSession session, MultipartFile qaImg){//인터페이스
        //첨부파일
        String originFileName = qaImg.getOriginalFilename();//파일 이름 저장
        System.out.println(originFileName);//첨부파일이름 확인

        //첨부될 파일명
        String uuid = UUID.randomUUID().toString();//랜덤한 난수 생성해서 파일이름이 겹치지 않도록
        int dotIndex = originFileName.lastIndexOf(".");//가장 마지막위치의 .위치 index를 저장
        String extension = originFileName.substring(dotIndex);//원본파일의 확장자명
        String attachedFileName = uuid + extension; //난수 + 확장자 저장
        //파일 첨부경로, (상수)+난수+확장자로 저장
        File file = new File(ConstantVariable.UPLOAD_PATH_COMMUNITY_QA + attachedFileName);
        try {
            qaImg.transferTo(file);//qaImg 첨부파일에 대한 정보를 변환
        } catch (IOException e) {
            throw new RuntimeException(e);
        }//이미지 첨부가 안될때 파일 생성도 되지가 않으니 트라이 캐치(예외처리) 활용

        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        boardQaListVO.setQaBoardWriter(loginInfo.getMemberId());
        //글 등록 쿼리 실행
        qaService.insertQaBoard(boardQaListVO);
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
