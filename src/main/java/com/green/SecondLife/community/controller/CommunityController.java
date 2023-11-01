package com.green.SecondLife.community.controller;

import com.green.SecondLife.community.service.CommunityService;
import com.green.SecondLife.community.vo.BoardCommentListVO;
import com.green.SecondLife.community.vo.BoardFreeListVO;
import com.green.SecondLife.member.vo.SubMenuVO;
import com.green.SecondLife.util.ConstantVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class CommunityController {
    private final CommunityService communityService;

    //자유 게시판 출력
    @RequestMapping("/freeBoardList")
    public String freeBoardList(Model model, BoardFreeListVO boardFreeListVO, SubMenuVO subMenuVO){
        //페이지 정보 세팅
        int totalDataCnt = communityService.selectBoardCnt(); //전체 게시글 갯수 조회해서
        boardFreeListVO.setTotalDataCnt(totalDataCnt);//세터 호출해서 전체 게시글 갯수 전달
        boardFreeListVO.setPageInfo();//변수값 설정한 메소드 호출(상속관계라 사용가능)

        //게시글 목록 조회
        List<BoardFreeListVO> freeBoardList = communityService.selectFreeBoardList(boardFreeListVO);
        model.addAttribute("freeBoardList", freeBoardList);

        return "board/free_board";
    }

    //내글 찾기 버튼 누르면 비동기로 (내정보 페이지를 새로 만들어서 활용할 것)
//    @ResponseBody
//    @PostMapping("/freeMyBoard")
//    public List<BoardFreeListVO> freeMyBoard(BoardFreeListVO boardFreeListVO, HttpSession session){
//        //세션에 저장된 memberID를 VO에 넣기
//        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
//        boardFreeListVO.setFreeBoardWriter(loginInfo.getMemberId());
//        //페이지 정보 세팅
//        int totalDataCnt = communityService.selectMyBoardCnt(boardFreeListVO); //전체 게시글 갯수 조회해서
//        boardFreeListVO.setTotalPageCnt(totalDataCnt);//세터 호출해서 전체 게시글 갯수 전달
//        boardFreeListVO.setPageInfo();//변수값 설정한 메소드 호출(상속관계라 사용가능)
//        //내글 찾기 조회
//        return communityService.selectFreeMyBoard(boardFreeListVO);
//    }

    //등록버튼 누르면 등록 페이지로 이동
    @GetMapping("/regBoardForm")
    public String regBoardForm(SubMenuVO subMenuVO){
        return "board/reg_board";
    }
    //글 등록 페이지에서 등록하기 누르면 글 등록 쿼리 실행
    @PostMapping("/regBoard")
    public String regBoard(BoardFreeListVO boardFreeListVO, Authentication authentication){
        //세터에 작성자 넣기
        boardFreeListVO.setFreeBoardWriter(authentication.getName());

        //글 등록 쿼리
        communityService.insertFreeBoard(boardFreeListVO);
        return "redirect:/board/freeBoardList?menuCode="+ ConstantVariable.MENU_CODE_BOARD;
    }
    //글 제목 클릭했을때 해당글의 상세페이지 이동
    @RequestMapping("/boardDetail")
    public String boardDetail(int freeBoardNum, Model model, SubMenuVO subMenuVO){
        model.addAttribute("board", communityService.selectFreeBoardDetail(freeBoardNum));
        //조회수 증가
        communityService.updateFreeBoardCnt(freeBoardNum);
        //댓글 조회해서 html로 던지기
        model.addAttribute("comment", communityService.selectFreeBoardComment(freeBoardNum));
        return "board/board_detail";
    }
    //글 상세페이지에서 삭제버튼 클릭하였을 때
    @GetMapping("/deleteFreeBoard")
    public String deleteFreeBoard(int freeBoardNum){
        communityService.deleteFreeBoard(freeBoardNum);
        return "redirect:/board/freeBoardList?menuCode="+ConstantVariable.MENU_CODE_BOARD;
    }

    //수정페이지에서 수정 버튼을 눌렀을 때 수정 쿼리 실행
    @RequestMapping("/updateFreeBoard")
    public String updateFreeBoard(BoardFreeListVO boardFreeListVO, int freeBoardNum){
        System.out.println(boardFreeListVO);
        System.out.println(freeBoardNum);
        //수정 쿼리
        communityService.updateFreeBoard(boardFreeListVO);
        //수정이 완료되면 해당 게시글 상세페이지로 freeBoardNum=숫자 데이터를 던질 수 있다.
        return "redirect:/board/boardDetail?freeBoardNum=" + boardFreeListVO.getFreeBoardNum()+"&menuCode="+ConstantVariable.MENU_CODE_BOARD;
    }
    //상세 페이지에서 댓글 작성버튼 클릭하면 비동기로 insert 쿼리 실행
    @ResponseBody
    @PostMapping("/freeBoardComment")
    public boolean freeBoardComment(BoardCommentListVO boardCommentListVO, Authentication authentication){
        //admin
        System.out.println(authentication.getName());

        //로그인 정보가 없다면 댓글 작성하지 못하게
        if (authentication == null){//로그인 정보가 없을 때
            return false;//board.js로 false리턴
        }
        //로그인 정보가 있다면 if문 실행되지않고 쿼리가 실행된 후 true 리턴
        communityService.insertFreeBoardComment(boardCommentListVO);
        return true;//board.js로 true리턴
    }

    //상세 페이지에서 댓글 삭제버튼 클릭하면 delete 쿼리 실행
    @ResponseBody
    @PostMapping("/freeDeleteComment")
    public void freeBoardComment(int commentId){
        communityService.deleteFreeBoardComment(commentId);
    }

    //상세 페이지에서 댓글 수정 버튼 클릭하면 update 쿼리 실행
    @ResponseBody
    @PostMapping("/freeUpdateComment")
    public void freeDeleteComment(BoardCommentListVO boardCommentListVO){
        System.out.println(boardCommentListVO);
        communityService.updateFreeBoardComment(boardCommentListVO);
    }
}
