package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardCommentListVO;
import com.green.SecondLife.community.vo.BoardQaListVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QaServiceImpl implements QaService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<BoardQaListVO> selectQaBoardList(BoardQaListVO boardQaListVO) {
        return sqlSession.selectList("qaMapper.selectQaBoardList", boardQaListVO);
    }

    @Override
    public int selectBoardCnt() {
        return sqlSession.selectOne("qaMapper.selectBoardCnt");
    }

    @Override
    public int insertQaBoard(BoardQaListVO boardQaListVO) {
        return sqlSession.insert("qaMapper.insertQaBoard", boardQaListVO);
    }

    @Override
    public List<BoardQaListVO> selectMainQaBoardList() {
        return sqlSession.selectList("qaMapper.selectMainQaBoardList");
    }

    @Override
    public BoardQaListVO selectQaBoardDetail(int qaBoardNum) {
        return sqlSession.selectOne("qaMapper.selectQaBoardDetail", qaBoardNum);
    }

    @Override
    public int updateQaBoardCnt(int qaBoardNum) {
        return sqlSession.update("qaMapper.updateQaBoardCnt", qaBoardNum);
    }

    @Override
    public List<BoardQaListVO> selectQaBoardComment(int qaBoardNum) {
        return sqlSession.selectList("qaMapper.selectQaBoardComment", qaBoardNum);
    }

    @Override
    public int insertQaBoardComment(BoardCommentListVO boardCommentListVO) {
        return sqlSession.insert("qaMapper.insertQaBoardComment", boardCommentListVO);
    }

    @Override
    public int deleteQaBoard(int qaBoardNum) {
        return sqlSession.delete("qaMapper.deleteQaBoard", qaBoardNum);
    }

    @Override
    public int updateQaBoard(BoardQaListVO boardQaListVO) {
        return sqlSession.update("qaMapper.updateQaBoard", boardQaListVO);
    }

    @Override
    public int deleteQaBoardComment(int commentId) {
        return sqlSession.delete("qaMapper.deleteQaBoardComment", commentId);
    }

    @Override
    public int updateQaBoardComment(BoardCommentListVO boardCommentListVO) {
        return sqlSession.update("qaMapper.updateQaBoardComment", boardCommentListVO);
    }
}
