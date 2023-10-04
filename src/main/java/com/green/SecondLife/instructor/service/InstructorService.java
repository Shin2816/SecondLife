package com.green.SecondLife.instructor.service;

import com.green.SecondLife.instructor.vo.InstructorVO;
import com.green.SecondLife.instructor.vo.SubjectVO;

public interface InstructorService {
    //강사 등록화면에서 강좌 과목 카테고리 조회하는 기능
    public SubjectVO selectSubject();
    //강사 등록하는 기능
    public void insertInstructor(InstructorVO instructorVO);
}
