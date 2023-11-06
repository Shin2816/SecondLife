package com.green.SecondLife.community.controller;

import com.green.SecondLife.community.service.AnnounceService;
import com.green.SecondLife.community.vo.BoardAnnounceListVO;
import com.green.SecondLife.community.vo.BoardFreeListVO;
import com.green.SecondLife.community.vo.BoardQaListVO;
import com.green.SecondLife.community.vo.QaImgVO;
import com.green.SecondLife.member.vo.SubMenuVO;
import com.green.SecondLife.util.ConstantVariable;
import com.green.SecondLife.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/announce")
public class AnnounceController {
    private final AnnounceService announceService;

    //Q&A 게시판 출력
    @RequestMapping("/anBoardList")
    public String anBoardList(Model model, BoardAnnounceListVO boardAnnounceListVO, SubMenuVO subMenuVO, Authentication authentication){
        //페이지 정보 세팅
        int totalDataCnt = announceService.selectBoardCnt(); //전체 게시글 갯수 조회해서
        boardAnnounceListVO.setTotalDataCnt(totalDataCnt);//세터 호출해서 전체 게시글 갯수 전달
        boardAnnounceListVO.setPageInfo();//변수값 설정한 메소드 호출(상속관계라 사용가능)

        model.addAttribute("authentication", authentication);

        //게시글 목록 조회
        List<BoardAnnounceListVO> anBoardList = announceService.selectAnBoardList(boardAnnounceListVO);
        model.addAttribute("anBoardList", anBoardList);
        return "board/announce/an_board";
    }
    //등록버튼 누르면 등록 페이지로 이동
    @GetMapping("/regAnBoardForm")
    public String regAnBoardForm(SubMenuVO subMenuVO){
        return "board/announce/reg_board";
    }
    //글 등록 페이지에서 등록하기 누르면 글 등록 쿼리 실행
    @PostMapping("/regBoard")
    public String regBoard(BoardAnnounceListVO boardAnnounceListVO, Authentication authentication, SubMenuVO subMenuVO){
        boardAnnounceListVO.setAnBoardWriter(authentication.getName());// 작성자 이름 넣기
        System.out.println(boardAnnounceListVO);
        announceService.insertAnBoard(boardAnnounceListVO);
        return "redirect:/announce/anBoardList";
    }
    //글 제목 클릭했을때 해당글의 상세페이지 이동
    @RequestMapping("/boardDetail")
    public String boardDetail(int anBoardNum, Model model, SubMenuVO subMenuVO){
        model.addAttribute("board", announceService.selectAnBoardDetail(anBoardNum));
        //조회수 증가
        announceService.updateAnBoardCnt(anBoardNum);
        return "board/announce/board_detail";
    }
    //글 상세페이지에서 삭제버튼 클릭하였을 때
    @GetMapping("/deleteAnBoard")
    public String deleteFreeBoard(int anBoardNum){
        announceService.deleteAnBoard(anBoardNum);
        return "redirect:/announce/anBoardList?menuCode="+ ConstantVariable.MENU_CODE_BOARD;
    }

    //수정페이지에서 수정 버튼을 눌렀을 때 수정 쿼리 실행
    @RequestMapping("/updateAnBoard")
    public String updateFreeBoard(BoardAnnounceListVO boardAnnounceListVO){
        //수정 쿼리
        announceService.updateAnBoard(boardAnnounceListVO);
        //수정이 완료되면 해당 게시글 상세페이지로 freeBoardNum=숫자 데이터를 던질 수 있다.
        return "redirect:/announce/boardDetail?anBoardNum=" + boardAnnounceListVO.getAnBoardNum()+"&menuCode="+ConstantVariable.MENU_CODE_BOARD;
    }
}
