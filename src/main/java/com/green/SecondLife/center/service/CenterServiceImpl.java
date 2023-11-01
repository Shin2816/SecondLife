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

    // 시설 이미지 삭제 + 등록(트랜젝션)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertFacilityImage(CenterFacilityVO centerFacilityVO) {
        sqlSession.delete("centerMapper.deleteFacilityImg", centerFacilityVO);
        sqlSession.insert("centerMapper.insertFacilityImage", centerFacilityVO);
    }

    @Override
    public List<CenterFacilityVO> selectAllFacility(CenterFacilityVO centerFacilityVO) {
        return sqlSession.selectList("centerMapper.selectAllFacility", centerFacilityVO);
    }

    @Override
    public int selectFacilityListCnt() {
        return sqlSession.selectOne("centerMapper.selectFacilityListCnt");
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

    @Override
    public List<CenterFacilityVO> selectFacilityInfo(int centerCateCode) {
        return sqlSession.selectList("centerMapper.selectFacilityInfo", centerCateCode);
    }

}
