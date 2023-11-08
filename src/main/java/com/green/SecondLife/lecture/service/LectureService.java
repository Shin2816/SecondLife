package com.green.SecondLife.lecture.service;

import com.green.SecondLife.instructor.vo.InstructorVO;
import com.green.SecondLife.lecture.vo.LectureEventVO;
import com.green.SecondLife.lecture.vo.LectureVO;
import com.green.SecondLife.lecture.vo.StudentVO;
import com.green.SecondLife.member.vo.SubMenuVO;

import java.util.List;

public interface LectureService {
    //관리자용 강좌 종목 개설 기능
    // 1. 우선 다음 강좌 종목의 코드를 조회
    public String adminSelectNextLectureEventCode();

    // 2. 강좌 종목 insert + 강좌 종목 이미지 insert
    public void adminInsertLectureEventAndImg(LectureEventVO lectureEventVO);

    //관리자용 강좌 종목 리스트 조회 기능
    public List<LectureEventVO> adminSelectLectureEventList();

    //관리자용 강좌 종목 상세 조회 기능
    public LectureEventVO adminSelectLectureEventDetail(LectureEventVO lectureEventVO);

    //관리자용 강좌 종목 이름 수정 기능
    public void adminUpdateLectureEventName(LectureEventVO lectureEventVO);

    //관리자용 강좌 종목 내용 수정 기능
    public void adminUpdateLectureEventContent(LectureEventVO lectureEventVO);

    //관리자용 강좌 종목 삭제 기능
    public void adminDeleteLectureEvent(LectureEventVO lectureEventVO);

    //관리자용 수업 생성 기능
    public void adminInsertLecture(LectureVO lectureVO);

    //관리자용 수업 목록 조회 기능
    public List<LectureVO> adminSelectLectureList(String instructorCode);

    //관리자용 수업 상세 조회 기능
    public LectureVO adminSelectLectureDetail(LectureVO lectureVO);

    //관리자용 수업 강사 수정 기능
    public void adminUpdateLectureInstructor(LectureVO lectureVO);

    //관리자용 수업 기간 수정 기능
    public void adminUpdateLecturePeriod(LectureVO lectureVO);

    //관리자용 수업 정원 수정 기능
    public void adminUpdateLectureStudent(LectureVO lectureVO);

    //관리자용 수업 삭제 기능
    public void adminDeleteLecture(LectureVO lectureVO);

    //수업 목록 조회
    public List<LectureVO> selectLectureList(String lectureEventCode);

    //수강 신청 기능
    public void insertStudent(StudentVO studentVO);

    //내 수강 목록 조회
    public List<LectureVO> selectMyLectureList(StudentVO studentVO);

    //수강생 중복 체크
    public boolean studentOverLapCheck(StudentVO studentVO);

    //수강생 삭제 기능
    public void deleteStudent(StudentVO studentVO);

    //강좌 목록 메인페이지 조회
    public List<LectureVO> selectMainLectureList();

    //수강생 목록 조회
    public List<StudentVO> selectStudentList(LectureVO lectureVO);
}