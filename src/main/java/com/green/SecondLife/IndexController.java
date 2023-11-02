package com.green.SecondLife;

import com.green.SecondLife.community.service.GalleryService;
import com.green.SecondLife.community.service.QaService;
import com.green.SecondLife.community.vo.BoardCommentListVO;
import com.green.SecondLife.community.vo.BoardFreeListVO;
import com.green.SecondLife.community.vo.BoardGalleryListVO;
import com.green.SecondLife.community.vo.BoardQaListVO;
import com.green.SecondLife.member.vo.SubMenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class IndexController {
    private final QaService qaService;
    private final GalleryService galleryService;

    @GetMapping("/")
    public String main(Model model, SubMenuVO subMenuVO, BoardQaListVO boardQaListVO, BoardGalleryListVO boardGalleryListVO, Authentication authentication){
        List<BoardQaListVO> qaBoardList = qaService.selectQaBoardList(boardQaListVO);//게시글 목록 조회
        model.addAttribute("qaBoardList", qaBoardList);//게시글정보
        model.addAttribute("authentication", authentication);//유저정보

        //게시글 목록 조회
        model.addAttribute("galleryBoardList", galleryService.selectGalBoardList(boardGalleryListVO));
        return "main";
    }
}
