package com.green.SecondLife.lecture.service;

import com.green.SecondLife.lecture.vo.LectureVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService{
    private final SqlSessionTemplate sqlSession;
    //강좌 등록 기능
    @Override
    public void insertLecture(LectureVO lectureVO) {
        sqlSession.insert("lectureMapper.insertLecture", lectureVO);
    }
    //강좌 목록 조회 기능
    @Override
    public List<LectureVO> selectLectureList() {
        return sqlSession.selectList("lectureMapper.selectLectureList");
    }
    //강좌 상세 정보 조회 기능
    @Override
    public LectureVO selectLectureDetail(LectureVO lectureVO) {
        return sqlSession.selectOne("lectureMapper.selectLectureDetail", lectureVO);
    }
    //강좌 삭제 기능
    @Override
    public void deleteLecture(LectureVO lectureVO) {
        sqlSession.delete("lectureMapper.deleteLecture", lectureVO);
    }
}
