package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardGalleryListVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<BoardGalleryListVO> selectGalBoardList(BoardGalleryListVO boardGalleryListVO) {
        return sqlSession.selectList("GalleryMapper.selectGalBoardList", boardGalleryListVO);
    }
}
