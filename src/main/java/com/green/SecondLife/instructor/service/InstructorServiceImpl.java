package com.green.SecondLife.instructor.service;

import com.green.SecondLife.instructor.vo.InstructorVO;
import com.green.SecondLife.instructor.vo.SubjectVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService{
    private final SqlSessionTemplate sqlSession;
    //강사 등록화면에서 강좌 과목 카테고리 조회하는 기능
    @Override
    public List<SubjectVO> selectSubjectList() {
        return sqlSession.selectList("instructorMapper.selectSubjectList");
    }
    //강사 등록 기능
    @Override
    public void insertInstructor(InstructorVO instructorVO) {
        sqlSession.insert("instructorMapper.insertInstructor", instructorVO);
    }
}
