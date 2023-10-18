package com.green.SecondLife.rental.vo;

import lombok.Data;

@Data
public class RentalFacilityVO {
    private String rentalCode;
    private String facilityCode;
    private String rentalDate;
    private String rentalTimeCode;
    private int rentalCharge;
    private String rentalUser;
    private String rentalTeam;
    private String rentalPurpose;
    private String rentalStatus;
}
