package com.green.SecondLife.center.service;

import com.green.SecondLife.center.vo.CenterFacilityVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public String selectNextFacilityCode() {
        return sqlSession.selectOne("centerMapper.selectNextFacilityCode");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertFacility(CenterFacilityVO centerFacilityVO) {
        sqlSession.insert("centerMapper.insertFacility", centerFacilityVO);
        sqlSession.insert("centerMapper.insertFacilityImage" , centerFacilityVO);
    }

    @Override
    public List<CenterFacilityVO> selectAllFacility() {
        return sqlSession.selectList("centerMapper.selectAllFacility");
    }
}
