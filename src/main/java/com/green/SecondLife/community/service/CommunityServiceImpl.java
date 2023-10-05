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
    public List<BoardFreeListVO> selectFreeBoardList() {
        return sqlSession.selectList("communityMapper.selectFreeBoardList");
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

    @Override
    public int updateFreeBoardCnt(int freeBoardNum) {
        return sqlSession.update("communityMapper.updateFreeBoardCnt", freeBoardNum);
    }
}
