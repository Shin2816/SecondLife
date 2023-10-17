package com.green.SecondLife.lecture.service;

import com.green.SecondLife.instructor.vo.InstructorVO;
import com.green.SecondLife.lecture.vo.LectureReviewVO;
import com.green.SecondLife.lecture.vo.LectureVO;
import com.green.SecondLife.lecture.vo.StudentVO;
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
    public List<LectureVO> selectLectureList(String instructorCode) {
        return sqlSession.selectList("lectureMapper.selectLectureList", instructorCode);
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
    //수강 신청 기능
    @Override
    public void insertStudent(StudentVO studentVO) {
        sqlSession.insert("lectureMapper.insertStudent", studentVO);
    }
    //수강생 목록 조회
    @Override
    public List<StudentVO> selectStudentList(StudentVO studentVO) {
        return sqlSession.selectList("lectureMapper.selectStudentList", studentVO);
    }
    //수강생 삭제 기능
    @Override
    public void deleteStudent(StudentVO studentVO) {
        sqlSession.delete("lectureMapper.deleteStudent", studentVO);
    }

    @Override
    public List<LectureVO> selectMainLectureList() {
        return sqlSession.selectList("lectureMapper.selectMainLectureList");
    }
    //강좌 리뷰 등록 기능
    @Override
    public void insertLectureReview(LectureReviewVO lectureReviewVO) {
        sqlSession.insert("lectureMapper.insertLectureReview", lectureReviewVO);
    }
    //리뷰 작성을 하려는 수강생 정보 조회
    @Override
    public StudentVO selectTheStudent(StudentVO studentVO) {
        return sqlSession.selectOne("lectureMapper.selectTheStudent", studentVO);
    }
    //강좌 리뷰 목록 조회
    @Override
    public List<LectureReviewVO> selectLectureReviewList(InstructorVO instructorVO) {
        return sqlSession.selectList("lectureMapper.selectLectureReviewList", instructorVO);
    }

}
