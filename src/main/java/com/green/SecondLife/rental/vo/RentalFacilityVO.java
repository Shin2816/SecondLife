package com.green.SecondLife.rental.vo;

import com.green.SecondLife.community.vo.PageVO;
import lombok.Data;

import java.util.List;

@Data
public class RentalFacilityVO extends PageVO {
    private String rentalCode;
    private String facilityCode;
    private String facilityName;
    private String rentalDate;
    private String rentalTimeCode; //"100,200"
    private String rentalTime;
    private int rentalCharge;
    private String rentalUser;
    private String rentalTeam;
    private String rentalPurpose;
    private String rentalStatus;
    private String rentalSignCode;
    private String rejectReason;
    private RentalTimeVO rentalTimeVO;
    private List<RentalFacilityVO> facilityList;
}
