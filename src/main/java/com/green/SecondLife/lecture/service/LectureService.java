package com.green.SecondLife.lecture.service;

import com.green.SecondLife.instructor.vo.InstructorVO;
import com.green.SecondLife.lecture.vo.LectureEventVO;
import com.green.SecondLife.lecture.vo.LectureReviewVO;
import com.green.SecondLife.lecture.vo.LectureVO;
import com.green.SecondLife.lecture.vo.StudentVO;

import java.util.List;

public interface LectureService {
    //관리자용 강좌 종목 개설 기능
    // 1. 우선 다음 강좌 종목의 코드를 조회
    public String adminSelectNextLectureEventCode();
    // 2. 강좌 종목 insert + 강좌 종목 이미지 insert
    public void adminInsertLectureEventAndImg(LectureEventVO lectureEventVO);

    //관리자용 강좌 종목 리스트 조회 기능
    public List<LectureEventVO> adminSelectLectureEventList();
    //관리자용 강좌 리뷰 리스트 조회 기능
    public List<LectureReviewVO> adminSelectLectureReviewList();


    //강좌 등록 기능
    public void insertLecture(LectureVO lectureVO);
    //강좌 목록 조회 기능
    public List<LectureVO> selectLectureList(String instructorCode);
    //강좌 상세 정보 조회 기능
    public LectureVO selectLectureDetail(LectureVO lectureVO);
    //강좌 삭제 기능
    public void deleteLecture(LectureVO lectureVO);
    //수강 신청 기능
    public void insertStudent(StudentVO studentVO);
    //수강생 목록 조회
    public List<StudentVO> selectStudentList(StudentVO studentVO);
    //수강생 삭제 기능
    public void deleteStudent(StudentVO studentVO);
    //강좌 목록 메인페이지 조회
    public List<LectureVO> selectMainLectureList();
    //강좌 리뷰 등록 기능
    public void insertLectureReview(LectureReviewVO lectureReviewVO);
    //리뷰 작성 하려는 수강생 정보 보회
    public StudentVO selectTheStudent(StudentVO studentVO);
    //강좌 리뷰 목록 조회
    public List<LectureReviewVO> selectLectureReviewList(InstructorVO instructorVO);
}
