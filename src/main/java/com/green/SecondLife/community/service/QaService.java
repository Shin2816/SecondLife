package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardQaListVO;

import java.util.List;

public interface QaService {
    ///////////////////////////////////// Q&A ////////////////////////////////////////////
    //Q&A 게시판 목록 조회
    public List<BoardQaListVO> selectQaBoardList(BoardQaListVO boardQaListVO);
}
