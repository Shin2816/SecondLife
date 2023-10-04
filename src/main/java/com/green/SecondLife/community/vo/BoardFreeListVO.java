package com.green.SecondLife.community.vo;

import lombok.Data;

@Data
public class BoardFreeListVO {
    private int freeBoardNum;
    private String freeBoardTitle;
    private String freeBoardContent;
    private String freeBoardWriter;
    private String createDate;
    private int freeBoardReadCnt;
    private String searchType;
    private String searchValue;
}
