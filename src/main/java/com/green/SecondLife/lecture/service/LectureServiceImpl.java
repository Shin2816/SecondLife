package com.green.SecondLife.lecture.service;

import com.green.SecondLife.instructor.vo.InstructorVO;
import com.green.SecondLife.lecture.vo.LectureEventVO;
import com.green.SecondLife.lecture.vo.LectureReviewVO;
import com.green.SecondLife.lecture.vo.LectureVO;
import com.green.SecondLife.lecture.vo.StudentVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService{
    private final SqlSessionTemplate sqlSession;
    //관리자용 강좌 종목 개설 기능
    // 1. 우선 다음 강좌 종목의 코드를 조회
    @Override
    public String adminSelectNextLectureEventCode() {
        return sqlSession.selectOne("lectureMapper.adminSelectNextLectureEventCode");
    }
    // 2. 강좌 종목 insert + 강좌 종목 이미지 insert
    // 강좌 종목 정보 + 이미지 둘다 인서트 됐을때 커밋 하기 위해서
    // @Transactional을 사용해서 Exception.claa를 통해 모든 예외에 대해 롤백처리
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void adminInsertLectureEventAndImg(LectureEventVO lectureEventVO) {
        sqlSession.insert("lectureMapper.adminInsertLectureEvent", lectureEventVO);
        sqlSession.insert("lectureMapper.adminInsertLectureEventImg", lectureEventVO);
    }
    //관리자용 강좌 종목 리스트 조회 기능
    @Override
    public List<LectureEventVO> adminSelectLectureEventList() {
        return sqlSession.selectList("lectureMapper.adminSelectLectureEventList");
    }
    //관리자용 강좌 종목 상세 조회 기능
    @Override
    public LectureEventVO adminSelectLectureEventDetail(LectureEventVO lectureEventVO) {
        return sqlSession.selectOne("lectureMapper.adminSelectLectureEventDetail", lectureEventVO);
    }
    //관리자용 강좌 종목 정보 수정 기능
    @Override
    public void adminUpdateLectureEventInfo(LectureEventVO lectureEventVO) {
        sqlSession.update("lectureMapper.adminUpdateLectureEventInfo", lectureEventVO);
    }
    //관리자용 강좌 종목 삭제 기능
    @Override
    public void adminDeleteLectureEvent(LectureEventVO lectureEventVO) {
        sqlSession.delete("lectureMapper.adminDeleteLectureEvent", lectureEventVO);
    }

    //관리자용 강좌 리뷰 리스트 조회 기능
    @Override
    public List<LectureReviewVO> adminSelectLectureReviewList() {
        return sqlSession.selectList("lectureMapper.adminSelectLectureReviewList");
    }

    //관리자용 수업 생성 기능
    @Override
    public void adminInsertLecture(LectureVO lectureVO) {
        sqlSession.insert("lectureMapper.adminInsertLecture", lectureVO);
    }

    //관리자용 수업 목록 조회 기능
    @Override
    public List<LectureVO> adminSelectLectureList(String instructorCode) {
        return sqlSession.selectList("lectureMapper.adminSelectLectureList", instructorCode);
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
