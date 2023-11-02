package com.green.SecondLife.community.controller;

import com.green.SecondLife.community.service.AnnounceService;
import com.green.SecondLife.community.vo.BoardAnnounceListVO;
import com.green.SecondLife.community.vo.BoardQaListVO;
import com.green.SecondLife.member.vo.SubMenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@Controller
public class AnnounceController {
    private final AnnounceService announceService;

    //Q&A 게시판 출력
    @RequestMapping("/anBoardList")
    public String qaBoardList(Model model, BoardAnnounceListVO boardAnnounceListVO, SubMenuVO subMenuVO, Authentication authentication){
        //페이지 정보 세팅
        int totalDataCnt = announceService.selectBoardCnt(); //전체 게시글 갯수 조회해서
        boardAnnounceListVO.setTotalDataCnt(totalDataCnt);//세터 호출해서 전체 게시글 갯수 전달
        boardAnnounceListVO.setPageInfo();//변수값 설정한 메소드 호출(상속관계라 사용가능)

        model.addAttribute("authentication", authentication);

        //게시글 목록 조회
        List<BoardAnnounceListVO> anBoardList = announceService.selectAnBoardList(boardAnnounceListVO);
        model.addAttribute("qaBoardList", anBoardList);
        return "board/announce/an_board";
    }
}
