package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardFreeListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService{
    private final sqlSessionTemplate sqlSession;

    @Override
    public List<BoardFreeListVO> selectFreeBoardList(BoardFreeListVO boardFreeListVO) {
        return null;
    }
}
