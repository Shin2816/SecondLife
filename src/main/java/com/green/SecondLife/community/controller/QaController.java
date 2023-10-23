package com.green.SecondLife.community.controller;

import com.green.SecondLife.community.service.QaService;
import com.green.SecondLife.community.vo.BoardFreeListVO;
import com.green.SecondLife.community.vo.BoardQaListVO;
import com.green.SecondLife.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    public String regBoard(BoardQaListVO boardQaListVO, HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        boardQaListVO.setQaBoardWriter(loginInfo.getMemberId());
        qaService.insertQaBoard(boardQaListVO);
        return "redirect:/qa/qaBoardList";
    }
    //글 제목 클릭했을때 해당글의 상세페이지 이동
    @RequestMapping("/boardDetail")
    public String boardDetail(int freeBoardNum, Model model){
        //model.addAttribute("board", communityService.selectFreeBoardDetail(freeBoardNum));
        //조회수 증가
        //communityService.updateFreeBoardCnt(freeBoardNum);
        //댓글 조회해서 html로 던지기
        //model.addAttribute("comment", communityService.selectFreeBoardComment(freeBoardNum));
        return "board/board_detail";
    }
}
