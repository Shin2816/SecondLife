package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardCommentListVO;
import com.green.SecondLife.community.vo.BoardQaListVO;

import java.util.List;

public interface QaService {
    ///////////////////////////////////// Q&A ////////////////////////////////////////////
    //QA게시판 목록 조회
    public List<BoardQaListVO> selectQaBoardList(BoardQaListVO boardQaListVO);
    //게시글 갯수 조회
    public int selectBoardCnt(BoardQaListVO boardQaListVO);
    //QA게시판 글 등록(비공개)
    public void insertQaBoardClose(BoardQaListVO boardQaListVO);
    //QA게시판 글 등록(비공개)
    public void insertQaBoardOpen(BoardQaListVO boardQaListVO);
    //QA 메인페이지
    public List<BoardQaListVO> selectMainQaBoardList();
    //QA 상세 페이지(아우터조인 사용)
    public BoardQaListVO selectQaBoardDetail(int qaBoardNum);
    //QA 조회수 증가
    public int updateQaBoardCnt(int qaBoardNum);
    //QA 댓글 목록 조회
    public List<BoardQaListVO> selectQaBoardComment(int qaBoardNum);
    //QA 댓글 등록
    public int insertQaBoardComment(BoardCommentListVO boardCommentListVO);
    //QA 글 삭제
    public int deleteQaBoard(int qaBoardNum);
    //QA 글 수정
    public int updateQaBoard(BoardQaListVO boardQaListVO);
    //QA 댓글 삭제
    public int deleteQaBoardComment(int commentId);
    //QA 댓글 수정
    public int updateQaBoardComment(BoardCommentListVO boardCommentListVO);
    //다음 QA_BOARD_NUM 조회
    public int selectNextQaBoardNum();
    //해당하는 비밀번호 조회
    public String selectQaPw(int qaBoardNum);
}
