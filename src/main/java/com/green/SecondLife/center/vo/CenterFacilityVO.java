package com.green.SecondLife.center.vo;

import lombok.Data;

@Data
public class CenterFacilityVO {
    private String facilityCode;
    private String facilityName;
    private int maxClassCnt;
    private String rentalAvailable;
    private String facilityContent;
    private FacilityImageVO facilityImageVO;
}
