package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardGalleryListVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService{
    private final SqlSessionTemplate sqlSession;
    //게시글 목록 조회
    @Override
    public List<BoardGalleryListVO> selectGalBoardList(BoardGalleryListVO boardGalleryListVO) {
        return sqlSession.selectList("galleryMapper.selectGalBoardList", boardGalleryListVO);
    }
    //게시글 갯수 조회
    @Override
    public int selectBoardCnt() {
        return sqlSession.selectOne("galleryMapper.selectBoardCnt");
    }
    //다음 GAL_BOARD_NUM 조회
    @Override
    public int selectNextGalBoardNum() {
        return sqlSession.selectOne("galleryMapper.selectNextGalBoardNum");
    }
    //트랜잭션, 어떤 이유건 오류가 나면 중지
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertGalBoard(BoardGalleryListVO boardGalleryListVO) {
        sqlSession.insert("galleryMapper.insertGalBoard", boardGalleryListVO);

        if (boardGalleryListVO.getGalImgList().size() != 0){
            sqlSession.insert("galleryMapper.insertGalImgs", boardGalleryListVO);
        }
    }
}
