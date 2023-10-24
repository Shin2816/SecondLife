package com.green.SecondLife.center.vo;

import lombok.Data;

@Data
public class CenterFacilityVO {
    private String facilityCode;
    private String facilityName;
    private String facilityContent;
    private String rentalAvailable;
    private int facilityRentalCharge;
    private int centerCateCode;
    private String facilityPlaceInfo;
    private FacilityImageVO facilityImageVO;
}
