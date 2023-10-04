package com.green.SecondLife.community.vo;

import lombok.Data;

@Data
public class BoardQaListVO {
    private int qaBoardNum;
    private String qaBoardTitle;
    private String qaBoardContent;
    private String qaBoardWriter;
    private String qaCreateDate;
    private int qaBoardReadCnt;
    private String qaBoardIsOpen; // Q&A 공개 여부, 기본값 'OPEN'
}
