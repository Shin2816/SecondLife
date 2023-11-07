package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardAnnounceListVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnounceServiceImpl implements AnnounceService{
    private final SqlSessionTemplate sqlsession;
    //공지사항 조회
    @Override
    public List<BoardAnnounceListVO> selectAnBoardList(BoardAnnounceListVO boardAnnounceListVO) {
        return sqlsession.selectList("anMapper.selectAnBoardList", boardAnnounceListVO);
    }
    //공지사항 등록
    @Override
    public int insertAnBoard(BoardAnnounceListVO boardAnnounceListVO) {
        return sqlsession.insert("anMapper.insertAnBoard", boardAnnounceListVO);
    }
    //공지사항 상세정보 출력
    @Override
    public BoardAnnounceListVO selectAnBoardDetail(int anBoardNum) {
        return sqlsession.selectOne("anMapper.selectAnBoardDetail", anBoardNum);
    }
    //조회수
    @Override
    public int updateAnBoardCnt(int anBoardNum) {
        return sqlsession.update("anMapper.updateAnBoardCnt", anBoardNum);
    }
    //공지사항 삭제
    @Override
    public int deleteAnBoard(int anBoardNum) {
        return sqlsession.delete("anMapper.deleteAnBoard", anBoardNum);
    }
    //공지사항 수정
    @Override
    public int updateAnBoard(BoardAnnounceListVO boardAnnounceListVO) {
        return sqlsession.update("anMapper.updateAnBoard", boardAnnounceListVO);
    }
    //게시글 갯수 조회(페이징)
    @Override
    public int selectBoardCnt(BoardAnnounceListVO boardAnnounceListVO) {
        return sqlsession.selectOne("anMapper.selectBoardCnt", boardAnnounceListVO);
    }
}
