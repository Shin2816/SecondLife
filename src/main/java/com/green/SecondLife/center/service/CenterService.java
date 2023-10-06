package com.green.SecondLife.center.service;

import com.green.SecondLife.center.vo.CenterFacilityVO;

public interface CenterService {
    // 다음 시설 코드 조회
    public String selectNextFacilityCode();

    // 시설 등록 + 시설 이미지 등록(트랜젝션)
    public void insertFacility(CenterFacilityVO centerFacilityVO);
}
