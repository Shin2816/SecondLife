package com.green.SecondLife.instructor.service;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService{
    private final SqlSessionTemplate sqlSession;

}
