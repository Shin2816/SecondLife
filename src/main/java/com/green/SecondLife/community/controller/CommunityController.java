package com.green.SecondLife.community.controller;

import com.green.SecondLife.community.service.CommunityService;
import com.green.SecondLife.community.vo.BoardFreeListVO;
import com.green.SecondLife.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ListableBeanFactoryExtensionsKt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    //게시판 출력
    @RequestMapping("/freeBoardList")
    public String freeBoardList(Model model){
        List<BoardFreeListVO> freeBoardList = communityService.selectFreeBoardList();
        model.addAttribute("freeBoardList", freeBoardList);

        return "board/free_board";
    }

    //등록버튼 누르면 등록 페이지로 이동
    @GetMapping("/regBoardForm")
    private String regBoardForm(){
        return "board/reg_board";
    }
    //글 등록 페이지에서 등록하기 누르면 글 등록 쿼리 실행
    @PostMapping("/regBoard")
    private String regBoard(BoardFreeListVO boardFreeListVO, HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        boardFreeListVO.setFreeBoardWriter(loginInfo.getMemberId());
        communityService.insertFreeBoard(boardFreeListVO);
        return "redirect:/freeBoardList";
    }
    //글 제목 클릭했을때 해당글의 상세페이지 이동
    @GetMapping("/boardDetail")
    private String boardDetail(int freeBoardNum, Model model){
        model.addAttribute("board", communityService.selectFreeBoardDetail(freeBoardNum));

        communityService.updateFreeBoardCnt(freeBoardNum);

        return "board/board_detail";
    }
    //글 상세페이지에서 삭제버튼 클릭하였을 때
    @GetMapping("/deleteFreeBoard")
    private String deleteFreeBoard(int freeBoardNum){
        communityService.deleteFreeBoard(freeBoardNum);
        return "redirect:/freeBoardList";
    }
    //글 상세페이지에서 수정 버튼을 클릭하였을때 수정페이지로 이동
    @GetMapping("/updateFreeBoardForm")
    private String updateFreeBoardForm(int freeBoardNum, Model model){
        model.addAttribute("board", communityService.selectFreeBoardDetail(freeBoardNum));
        return "board/update_board";
    }
    //수정페이지에서 수정 버튼을 눌렀을 때 수정 쿼리 실행
    @PostMapping("/updateBoard")
    private String updateBoard(BoardFreeListVO boardFreeListVO){
        communityService.updateFreeBoard(boardFreeListVO);
        //수정이 완료되면 게시글 상세페이지로 문자열+숫자열 freeBoardNum=숫자 데이터를 던질 수 있다.
        return "redirect:/boardDetail?freeBoardNum=" + boardFreeListVO.getFreeBoardNum();
    }
}
