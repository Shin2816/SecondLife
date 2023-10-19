package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardCommentListVO;
import com.green.SecondLife.community.vo.BoardFreeListVO;

import java.util.List;

public interface CommunityService {
    //자유게시판 목록 조회
    public List<BoardFreeListVO> selectFreeBoardList(BoardFreeListVO boardFreeListVO);
    //자유게시판 글 등록
    public int insertFreeBoard(BoardFreeListVO boardFreeListVO);
    //자유게시판 글 상세조회
    public BoardFreeListVO selectFreeBoardDetail(int freeBoardNum);
    //자유게시판 글 삭제
    public int deleteFreeBoard(int freeBoardNum);
    //자유게시판 글 수정
    public int updateFreeBoard(BoardFreeListVO boardFreeListVO);
    //자유게시판 조회수 증가
    public int updateFreeBoardCnt(int freeBoardNum);
    //자유게시판 댓글 목록 조회
    public List<BoardFreeListVO> selectFreeBoardComment(int freeBoardNum);
    //자유게시판 댓글 등록
    public int insertFreeBoardComment(BoardCommentListVO boardCommentListVO);
    //자유게시판 댓글 삭제
    public int deleteFreeBoardComment(int commentId);
    //자유게시판 댓글 수정
    public int updateFreeBoardComment(BoardCommentListVO boardCommentListVO);
    //자유게시판 메인페이지 출력
    public List<BoardFreeListVO> selectMainFreeBoardList();
    //게시글 갯수 조회
    public int selectBoardCnt();
    //내가 쓴 글 조회
    public List<BoardFreeListVO> selectFreeMyBoard()
    ///////////////////////////////////// Q&A ////////////////////////////////////////////

}
