package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardQaListVO;

import java.util.List;

public interface QaService {
    ///////////////////////////////////// Q&A ////////////////////////////////////////////
    //QA게시판 목록 조회
    public List<BoardQaListVO> selectQaBoardList(BoardQaListVO boardQaListVO);
    //게시글 갯수 조회
    public int selectBoardCnt();
    //QA게시판 글 등록
    public int insertQaBoard(BoardQaListVO boardQaListVO);
}
