package com.green.SecondLife.community.controller;

import com.green.SecondLife.community.service.GalleryService;
import com.green.SecondLife.community.vo.BoardGalleryListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/gallery")
public class GalleryController {
    private final GalleryService galleryService;

    @RequestMapping("/galleryBoardList")
    public String selectGalBoardList(Model model, BoardGalleryListVO boardGalleryListVO){
        //페이지 정보 세팅
        int totalDataCnt = galleryService.selectBoardCnt(); //전체 게시글 갯수 조회해서
        boardGalleryListVO.setTotalPageCnt(totalDataCnt);//세터 호출해서 전체 게시글 갯수 전달
        boardGalleryListVO.setPageInfo();//변수값 설정한 메소드 호출(상속관계라 사용가능)

        //게시글 목록 조회
        model.addAttribute("galleryBoardList", galleryService.selectGalBoardList(boardGalleryListVO));
        return "board/gallery/gallery_board";
    }
}
