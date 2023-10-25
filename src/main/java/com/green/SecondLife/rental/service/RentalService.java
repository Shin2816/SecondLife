package com.green.SecondLife.rental.service;

import com.green.SecondLife.rental.vo.RentalFacilityVO;

import java.util.List;

public interface RentalService {
    // (사용자)대관 가능 정보 조회
    public List<RentalFacilityVO> selectRentalFacility(RentalFacilityVO rentalFacilityVO);

    // (사용자)대관 신청하기
    public void insertRentalFacility(RentalFacilityVO rentalFacilityVO);

    // (사용자)마이페이지-시설예약현황
    public List<RentalFacilityVO> selectMyRentalList(RentalFacilityVO rentalFacilityVO);

    // (사용자)대관신청 취소하기
    public void deleteSignRental(String rentalSignCode);

    // (관리자)대관신청현황관리 목록 조회
    public List<RentalFacilityVO> selectRentalList();
}
