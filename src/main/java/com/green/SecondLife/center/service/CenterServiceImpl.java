package com.green.SecondLife.center.service;

import com.green.SecondLife.center.vo.CenterFacilityVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public void insertFacility(CenterFacilityVO centerFacilityVO) {
        sqlSession.insert("centerMapper.insertFacility", centerFacilityVO);
    }
}
