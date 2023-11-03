package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardAnnounceListVO;

import java.util.List;

public interface AnnounceService {
    //Announce게시판 목록 조회
    public List<BoardAnnounceListVO> selectAnBoardList(BoardAnnounceListVO boardAnnounceListVO);
    //Announce게시판 글 등록(비공개)
    public int insertAnBoard(BoardAnnounceListVO boardAnnounceListVO);
    //Announce게시판 상세 페이지(아우터조인 사용)
    public BoardAnnounceListVO selectAnBoardDetail(int anBoardNum);
    //Announce게시판 조회수 증가
    public int updateAnBoardCnt(int anBoardNum);
    //Announce게시판 글 삭제
    public int deleteAnBoard(int anBoardNum);
    //Announce게시판 글 수정
    public int updateAnBoard(BoardAnnounceListVO boardAnnounceListVO);
    //게시글 갯수 조회
    public int selectBoardCnt();
}
