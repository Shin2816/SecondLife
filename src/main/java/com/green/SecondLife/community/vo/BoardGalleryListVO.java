package com.green.SecondLife.community.vo;

import lombok.Data;

@Data
public class BoardGalleryListVO extends PageVO{
    private int galBoardNum;
    private String galBoardTitle;
    private String galBoardContent;
    private String galBoardWriter;
    private String galCreateDate;
    private int galBoardReadCnt;
    private String searchType;
    private String searchValue;
}
