package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardCommentListVO;
import com.green.SecondLife.community.vo.BoardGalleryListVO;

import java.util.List;

public interface GalleryService {
    //갤러리 게시판 목록 조회
    public List<BoardGalleryListVO> selectGalBoardList(BoardGalleryListVO boardGalleryListVO);
    //게시글 갯수 조회
    public int selectBoardCnt(BoardGalleryListVO boardGalleryListVO);
    //다음 GAL_BOARD_NUM 조회
    public int selectNextGalBoardNum();
    //게시글 등록
    public void insertGalBoard(BoardGalleryListVO boardGalleryListVO);
    //게시글 상세조회
    public BoardGalleryListVO selectGalBoardDetail(int galBoardNum);
    //조회수 증가
    public int updateGalBoardCnt(int galBoardNum);
    //댓글 조회
    public List<BoardGalleryListVO> selectGalBoardComment(int galBoardNum);
    //게시글 삭제
    public int deleteGalBoard(int galBoardNum);
    //게시글 수정
    public int updateGalBoard(BoardGalleryListVO boardGalleryListVO);
    //댓글 작성
    public int insertGalBoardComment(BoardCommentListVO boardCommentListVO);
    //댓글 삭제
    public int deleteGalBoardComment(int commentId);
    //댓글 수정
    public int updateGalBoardComment(BoardCommentListVO boardCommentListVO);
}
