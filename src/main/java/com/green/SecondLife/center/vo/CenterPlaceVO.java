package com.green.SecondLife.center.vo;

import lombok.Data;

@Data
public class CenterPlaceVO {
    private String placeCode;
    private String placeName;
    private int maxClassCnt;
    private int rentalAlltimeCharge;
    private int rentalDaytimeCharge;
    private int rentalNighttimeCharge;
    private String rentalAvailable;
}
