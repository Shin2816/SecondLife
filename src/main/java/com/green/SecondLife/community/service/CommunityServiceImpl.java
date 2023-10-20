package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardCommentListVO;
import com.green.SecondLife.community.vo.BoardFreeListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService{
    private final SqlSessionTemplate sqlSession;

    //자유게시판 목록 조회
    @Override
    public List<BoardFreeListVO> selectFreeBoardList(BoardFreeListVO boardFreeListVO) {
        return sqlSession.selectList("communityMapper.selectFreeBoardList", boardFreeListVO);
    }
    //자유게시판 글 등록
    @Override
    public int insertFreeBoard(BoardFreeListVO boardFreeListVO) {
        return sqlSession.insert("communityMapper.insertFreeBoard", boardFreeListVO);
    }
    //자유게시판 글 상세 조회
    @Override
    public BoardFreeListVO selectFreeBoardDetail(int freeBoardNum) {
        return sqlSession.selectOne("communityMapper.selectFreeBoardDetail", freeBoardNum);
    }
    //자유게시판 글 삭제
    @Override
    public int deleteFreeBoard(int freeBoardNum) {
        return sqlSession.delete("communityMapper.deleteFreeBoard", freeBoardNum);

    }
    //자유게시판 글 수정
    @Override
    public int updateFreeBoard(BoardFreeListVO boardFreeListVO) {
        return sqlSession.update("communityMapper.updateFreeBoard", boardFreeListVO);
    }
    //조회수 증가
    @Override
    public int updateFreeBoardCnt(int freeBoardNum) {
        return sqlSession.update("communityMapper.updateFreeBoardCnt", freeBoardNum);
    }
    //댓글 조회
    @Override
    public List<BoardFreeListVO> selectFreeBoardComment(int freeBoardNum) {
        return sqlSession.selectList("communityMapper.selectFreeBoardComment", freeBoardNum);
    }
    //댓글 작성
    @Override
    public int insertFreeBoardComment(BoardCommentListVO boardCommentListVO) {
        return sqlSession.insert("communityMapper.insertFreeBoardComment", boardCommentListVO);
    }

    //댓글 삭제
    @Override
    public int deleteFreeBoardComment(int commentId) {
        return sqlSession.delete("communityMapper.deleteFreeBoardComment", commentId);
    }

    //댓글 수정
    @Override
    public int updateFreeBoardComment(BoardCommentListVO boardCommentListVO) {
        return sqlSession.update("communityMapper.updateFreeBoardComment", boardCommentListVO);
    }
    //메인페이지 자유게시판
    @Override
    public List<BoardFreeListVO> selectMainFreeBoardList() {
        return sqlSession.selectList("communityMapper.selectMainFreeBoardList");
    }
    //게시글 갯수 조회
    @Override
    public int selectBoardCnt() {
        return sqlSession.selectOne("communityMapper.selectBoardCnt");
    }

    @Override
    public int selectMyBoardCnt(BoardFreeListVO boardFreeListVO) {
        return sqlSession.selectOne("communityMapper.selectMyBoardCnt", boardFreeListVO);
    }

    //내글 찾기 조회
    @Override
    public List<BoardFreeListVO> selectFreeMyBoard(BoardFreeListVO boardFreeListVO) {
        return sqlSession.selectList("communityMapper.selectFreeMyBoard", boardFreeListVO);
    }

}
