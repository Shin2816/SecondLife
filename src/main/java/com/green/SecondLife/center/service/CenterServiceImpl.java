package com.green.SecondLife.center.service;

import com.green.SecondLife.center.vo.CenterFacilityVO;
import com.green.SecondLife.center.vo.CenterPlaceCategoryVO;
import com.green.SecondLife.center.vo.FacilityImageVO;
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
    public List<CenterPlaceCategoryVO> selectCenterCategory() {
        return sqlSession.selectList("centerMapper.selectCenterCategory");
    }

    @Override
    public String selectNextFacilityCode() {
        return sqlSession.selectOne("centerMapper.selectNextFacilityCode");
    }

    // 시설 등록 + 시설 이미지 등록(트랜젝션)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertFacility(CenterFacilityVO centerFacilityVO) {
        sqlSession.insert("centerMapper.insertFacility", centerFacilityVO);
        sqlSession.insert("centerMapper.insertFacilityImage" , centerFacilityVO);
    }

    // 시설 이미지 등록
    @Override
    public void insertFacilityImage(FacilityImageVO facilityImageVO) {
        sqlSession.insert("centerMapper.insertFacilityImage", facilityImageVO);
    }

    @Override
    public List<CenterFacilityVO> selectAllFacility() {
        return sqlSession.selectList("centerMapper.selectAllFacility");
    }

    @Override
    public void updateRentalAvailable(CenterFacilityVO centerFacilityVO) {
        sqlSession.update("centerMapper.updateRentalAvailable", centerFacilityVO);
    }

    @Override
    public void updateFacility(CenterFacilityVO centerFacilityVO) {
        sqlSession.update("centerMapper.updateFacility", centerFacilityVO);
    }

    @Override
    public void deleteFacility(String facilityCode) {
        sqlSession.delete("centerMapper.deleteFacility", facilityCode);
    }

    @Override
    public String selectCenterImgFileName(String facilityCode) {
        return sqlSession.selectOne("centerMapper.selectCenterImgFileName", facilityCode);
    }

}
