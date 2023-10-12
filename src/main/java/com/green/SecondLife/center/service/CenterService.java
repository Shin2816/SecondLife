package com.green.SecondLife.center.service;

import com.green.SecondLife.center.vo.CenterFacilityVO;
import com.green.SecondLife.center.vo.CenterPlaceCategoryVO;

import java.util.List;

public interface CenterService {
    // 시설 카테고리 조회
    public List<CenterPlaceCategoryVO> selectCenterCategory();

    // 다음 시설 코드 조회
    public String selectNextFacilityCode();

    // 시설 등록 + 시설 이미지 등록(트랜젝션)
    public void insertFacility(CenterFacilityVO centerFacilityVO);

    // 전체 시설 목록 조회
    public List<CenterFacilityVO> selectAllFacility();

    // 시설관리 - 대관가능유무 변경 (비동기통신)
    public void updateRentalAvailable(CenterFacilityVO centerFacilityVO);

    // 시설관리 - 수정하기
    public void updateFacility(CenterFacilityVO centerFacilityVO);

    // 시설관리 - 삭제하기
    public void deleteFacility(String facilityCode);

    // 첨부파일 이름 조회
    public String selectCenterImgFileName(String facilityCode);
}
