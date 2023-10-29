package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardCommentListVO;
import com.green.SecondLife.community.vo.BoardQaListVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //트랜잭션, 어떤 이유건 오류가 나면 중지(비공개)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertQaBoardClose(BoardQaListVO boardQaListVO) {
        sqlSession.insert("qaMapper.insertQaBoardClose", boardQaListVO);

        if(boardQaListVO.getQaImgList().size() != 0){
            sqlSession.insert("qaMapper.insertQaImgs", boardQaListVO);
        }
    }
    //트랜잭션, 어떤 이유건 오류가 나면 중지(공개)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertQaBoardOpen(BoardQaListVO boardQaListVO) {
        sqlSession.insert("qaMapper.insertQaBoardOpen", boardQaListVO);
        if (boardQaListVO.getQaImgList().size() != 0){
            sqlSession.insert("qaMapper.insertQaImgs", boardQaListVO);
        }
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

    @Override
    public int selectNextQaBoardNum() {
        return sqlSession.selectOne("qaMapper.selectNextQaBoardNum");
    }

    @Override
    public String selectQaPw(int qaBoardNum) {
        return sqlSession.selectOne("qaMapper.selectQaPw", qaBoardNum);
    }
}
