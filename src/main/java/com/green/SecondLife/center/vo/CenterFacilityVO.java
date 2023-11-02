package com.green.SecondLife.center.vo;

import com.green.SecondLife.community.vo.PageVO;
import lombok.Data;

@Data
public class CenterFacilityVO extends PageVO {
    private String facilityCode;
    private String facilityName;
    private String facilityContent;
    private String rentalAvailable;
    private int facilityRentalCharge;
    private int centerCateCode;
    private String facilityPlaceInfo;
    private FacilityImageVO facilityImageVO;
    private CenterPlaceCategoryVO centerPlaceCategoryVO;
}
