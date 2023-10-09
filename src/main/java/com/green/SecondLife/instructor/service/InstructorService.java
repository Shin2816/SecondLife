package com.green.SecondLife.instructor.service;

import com.green.SecondLife.instructor.vo.InstructorVO;
import com.green.SecondLife.instructor.vo.SubjectVO;

import java.util.List;

public interface InstructorService {
    //강사 등록화면에서 강좌 과목 카테고리 조회하는 기능
    public List<SubjectVO> selectSubjectList();
    //강사 등록하는 기능 + 이미지까지
    public void insertInstructor(InstructorVO instructorVO);
    //강사 목록 조회 기능
    public List<InstructorVO> selectInstuctorList();
    //강사 정보 상세 조회 기능
    public InstructorVO selectInstructorDetail(InstructorVO instructorVO);
    //강사 삭제 기능
    public void deleteInstructor(InstructorVO instructorVO);
}
