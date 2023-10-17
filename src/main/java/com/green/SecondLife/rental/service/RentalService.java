package com.green.SecondLife.rental.service;

import com.green.SecondLife.rental.vo.RentalFacilityVO;

import java.util.List;

public interface RentalService {
    // 대관 가능 정보 조회
    public List<RentalFacilityVO> selectRentalFacility(RentalFacilityVO rentalFacilityVO);
}
