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

        //게시글 목록 조회
        model.addAttribute("galleryBoardList", galleryService.selectGalBoardList(boardGalleryListVO));
        return "board/gallery/gallery_board";
    }
}
