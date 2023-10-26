package com.green.SecondLife.instructor.service;

import com.green.SecondLife.instructor.vo.InstructorImgVO;
import com.green.SecondLife.instructor.vo.InstructorVO;
import com.green.SecondLife.lecture.vo.LectureEventVO;

import java.util.List;

public interface InstructorService {
    //관리자용 강사 등록 기능
    // 1. 다음 강사 코드 조회
    public String adminSelectNextInstructorCode();
    // 2. 강사 등록을 위한 강좌 종목 리스트 조회 기능
    public List<LectureEventVO> adminSelectLectureEventListForInsertInstructor();
    // 3. 강사 등록 기능 + 강사 이미지 등록 기능
    public void adminInsertInstructor(InstructorVO instructorVO);

    //관리자용 강사 목록 조회 기능
    public List<InstructorVO> adminSelectInstuctorList();
    //관리자용 강사 상세 조회 기능
    public InstructorVO adminSelectInstructorDetail(InstructorVO instructorVO);
    //관리자용 강사 정보 수정 기능
    public void adminUpdateInstructorInfo(InstructorVO instructorVO);
    //관리자용 강사 삭제 기능
    public void adminDeleteInstructor(InstructorVO instructorVO);
}
