package com.green.SecondLife.rental.service;

import com.green.SecondLife.rental.vo.RentalFacilityVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService{
    private final SqlSessionTemplate sqlSession;

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
}
