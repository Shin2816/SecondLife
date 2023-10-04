package com.green.SecondLife.community.service;

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
}
