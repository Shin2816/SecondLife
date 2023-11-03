package com.green.SecondLife.community.controller;

import com.green.SecondLife.community.service.AnnounceService;
import com.green.SecondLife.community.vo.BoardAnnounceListVO;
import com.green.SecondLife.community.vo.BoardQaListVO;
import com.green.SecondLife.community.vo.QaImgVO;
import com.green.SecondLife.member.vo.SubMenuVO;
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
        announceService.insertAnBoard(boardAnnounceListVO);
        return "redirect:/qa/qaBoardList";
    }

}
