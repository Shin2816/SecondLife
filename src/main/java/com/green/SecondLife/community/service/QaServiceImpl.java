package com.green.SecondLife.community.service;

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
}
