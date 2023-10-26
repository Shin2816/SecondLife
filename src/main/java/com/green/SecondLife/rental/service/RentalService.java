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

    //(관리자)대관관리 상태변경(현상태-승인대기:2 / 반려: 0, 완료: 1, 승인->결제 대기: 3)
    public void updateRentalStatus0(RentalFacilityVO rentalFacilityVO);
    public void updateRentalStatus1(String rentalSignCode);
    public void updateRentalStatus3(String rentalSignCode);
}
