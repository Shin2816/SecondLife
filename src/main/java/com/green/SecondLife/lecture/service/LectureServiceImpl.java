package com.green.SecondLife.lecture.service;

import com.green.SecondLife.instructor.vo.InstructorVO;
import com.green.SecondLife.lecture.vo.LectureEventVO;
import com.green.SecondLife.lecture.vo.LectureVO;
import com.green.SecondLife.lecture.vo.StudentVO;
import com.green.SecondLife.member.vo.SubMenuVO;
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

    //관리자용 강좌 종목 이름 수정 기능
    @Override
    public void adminUpdateLectureEventName(LectureEventVO lectureEventVO) {
        sqlSession.update("lectureMapper.adminUpdateLectureEventName", lectureEventVO);
    }
    //관리자용 강좌 종목 내용 수정 기능
    @Override
    public void adminUpdateLectureEventContent(LectureEventVO lectureEventVO) {
        sqlSession.update("lectureMapper.adminUpdateLectureEventContent", lectureEventVO);
    }

    //관리자용 강좌 종목 삭제 기능
    @Override
    public void adminDeleteLectureEvent(LectureEventVO lectureEventVO) {
        sqlSession.delete("lectureMapper.adminDeleteLectureEvent", lectureEventVO);
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
    //관리자용 수업 상세 조회 기능
    @Override
    public LectureVO adminSelectLectureDetail(LectureVO lectureVO) {
        return sqlSession.selectOne("lectureMapper.adminSelectLectureDetail", lectureVO);
    }

    //관리자용 수업 강사 수정 기능
    @Override
    public void adminUpdateLectureInstructor(LectureVO lectureVO) {
        sqlSession.update("lectureMapper.adminUpdateLectureInstructor", lectureVO);
    }
    //관리자용 수업 강사 수정 기능
    @Override
    public void adminUpdateLecturePeriod(LectureVO lectureVO) {
        sqlSession.update("lectureMapper.adminUpdateLecturePeriod", lectureVO);
    }
    //관리자용 수업 강사 수정 기능
    @Override
    public void adminUpdateLectureStudent(LectureVO lectureVO) {
        sqlSession.update("lectureMapper.adminUpdateLectureStudent", lectureVO);
    }

    //관리자용 수업 삭제 기능
    @Override
    public void adminDeleteLecture(LectureVO lectureVO) {
        sqlSession.delete("lectureMapper.adminDeleteLecture", lectureVO);
    }

    //수업 목록 조회 기능
    @Override
    public List<LectureVO> selectLectureList(String lectureEventCode) {
        return sqlSession.selectList("lectureMapper.selectLectureList", lectureEventCode);
    }
    //수강 신청 기능
    @Override
    public void insertStudent(StudentVO studentVO) {
        sqlSession.insert("lectureMapper.insertStudent", studentVO);
    }
    //내 수업 목록 조회 기능
    @Override
    public List<LectureVO> selectMyLectureList(StudentVO studentVO) {
        return sqlSession.selectList("lectureMapper.selectMyLectureList", studentVO);
    }
    //수강생 중복 체크 ,  가능 -> true
    @Override
    public boolean studentOverLapCheck(StudentVO studentVO) {
        String result = sqlSession.selectOne("lectureMapper.studentOverLapCheck", studentVO);
        return result == null;
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

    //수강생 목록 조회
    @Override
    public List<StudentVO> selectStudentList(LectureVO lectureVO) {
        return sqlSession.selectList("lectureMapper.selectStudentList", lectureVO);
    }
}
