package com.green.SecondLife.member.service;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{

    private final SqlSessionTemplate sqlSession;

}
