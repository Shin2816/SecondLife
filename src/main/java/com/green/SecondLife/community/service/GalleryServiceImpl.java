package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardCommentListVO;
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
    public int selectBoardCnt(BoardGalleryListVO boardGalleryListVO) {
        return sqlSession.selectOne("galleryMapper.selectBoardCnt", boardGalleryListVO);
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
    //세부정보
    @Override
    public BoardGalleryListVO selectGalBoardDetail(int galBoardNum) {
        return sqlSession.selectOne("galleryMapper.selectGalBoardDetail", galBoardNum);
    }
    //조회수 증가
    @Override
    public int updateGalBoardCnt(int galBoardNum) {
        return sqlSession.update("galleryMapper.updateGalBoardCnt", galBoardNum);
    }
    //댓글 조회
    @Override
    public List<BoardGalleryListVO> selectGalBoardComment(int galBoardNum) {
        return sqlSession.selectList("galleryMapper.selectGalBoardComment", galBoardNum);
    }
    //게시글 삭제
    @Override
    public int deleteGalBoard(int galBoardNum) {
        return sqlSession.delete("galleryMapper.deleteGalBoard", galBoardNum);
    }
    //게시글 수정
    @Override
    public int updateGalBoard(BoardGalleryListVO boardGalleryListVO) {
        return sqlSession.update("galleryMapper.updateGalBoard", boardGalleryListVO);
    }
    //댓글 등록
    @Override
    public int insertGalBoardComment(BoardCommentListVO boardCommentListVO) {
        return sqlSession.insert("galleryMapper.insertGalBoardComment", boardCommentListVO);
    }
    //댓글 삭제
    @Override
    public int deleteGalBoardComment(int commentId) {
        return sqlSession.delete("galleryMapper.deleteGalBoardComment", commentId);
    }
    //댓글 수정
    @Override
    public int updateGalBoardComment(BoardCommentListVO boardCommentListVO) {
        return sqlSession.update("galleryMapper.updateGalBoardComment", boardCommentListVO);
    }
}
