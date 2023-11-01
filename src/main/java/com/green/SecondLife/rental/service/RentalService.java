package com.green.SecondLife.rental.service;

import com.green.SecondLife.member.vo.MemberVO;
import com.green.SecondLife.rental.vo.RentalFacilityVO;

import java.util.List;
import java.util.Map;

public interface RentalService {
    // 대관 코드 조회
    public List<RentalFacilityVO> selectFacility();

    // (사용자)대관 가능 정보 조회
    public List<RentalFacilityVO> selectRentalFacility(RentalFacilityVO rentalFacilityVO);

    // (사용자)대관 신청하기
    public void insertRentalFacility(RentalFacilityVO rentalFacilityVO);

    // (사용자)마이페이지-시설예약현황 조회
    public List<RentalFacilityVO> selectMyRentalList(RentalFacilityVO rentalFacilityVO);

    // (사용자)마이페이지-시설예약현황 데이터 수 조회
    public int selectMyRentalListCnt(String rentalUser);

    // (사용자)대관신청 취소하기
    public void deleteSignRental(String rentalSignCode);

    // (관리자)대관신청관리 목록 조회
    public List<RentalFacilityVO> selectRentalList(RentalFacilityVO rentalFacilityVO);

    // (관리자)대관신청관리 전체 데이터 수 조회
    public int selectRentalListCnt();

    //(관리자)대관관리 상태변경(현상태-승인대기:2 / 반려: 0, 완료: 1, 승인->결제 대기: 3)
    public void updateRentalStatus0(RentalFacilityVO rentalFacilityVO);
    public void updateRentalStatus1(Map<String, String> updateRental);
    public void updateRentalStatus3(String rentalSignCode);
}
