package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardGalleryListVO;

import java.util.List;

public interface GalleryService {
    //갤러리 게시판 목록 조회
    public List<BoardGalleryListVO> selectGalBoardList(BoardGalleryListVO boardGalleryListVO);
    //게시글 갯수 조회
    public int selectBoardCnt();
}
