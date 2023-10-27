package com.green.SecondLife.rental.service;

import com.green.SecondLife.member.vo.MemberVO;
import com.green.SecondLife.rental.vo.RentalFacilityVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<RentalFacilityVO> selectFacility() {
        return sqlSession.selectList("rentalMapper.selectFacility");
    }

    @Override
    public List<RentalFacilityVO> selectRentalFacility(RentalFacilityVO rentalFacilityVO) {
        return sqlSession.selectList("rentalMapper.selectRentalFacility", rentalFacilityVO);
    }

    @Override
    public void insertRentalFacility(RentalFacilityVO rentalFacilityVO) {
        sqlSession.insert("rentalMapper.insertRentalFacility", rentalFacilityVO);
    }

    @Override
    public List<RentalFacilityVO> selectMyRentalList(RentalFacilityVO rentalFacilityVO) {
        return sqlSession.selectList("rentalMapper.selectMyRentalList", rentalFacilityVO);
    }

    @Override
    public void deleteSignRental(String rentalSignCode) {
        sqlSession.delete("rentalMapper.deleteSignRental", rentalSignCode);
    }

    @Override
    public List<RentalFacilityVO> selectRentalList() {
        return sqlSession.selectList("rentalMapper.selectRentalList");
    }

    @Override
    public void updateRentalStatus0(RentalFacilityVO rentalFacilityVO) {
        sqlSession.update("rentalMapper.updateRentalStatus0", rentalFacilityVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRentalStatus1(Map<String, String> updateRental) {
        sqlSession.insert("paymentMapper.insertPayment", updateRental);
        sqlSession.update("rentalMapper.updateRentalStatus1", updateRental);
    }

    @Override
    public void updateRentalStatus3(String rentalSignCode) {
        sqlSession.update("rentalMapper.updateRentalStatus3", rentalSignCode);
    }
}
