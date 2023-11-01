package com.green.SecondLife.community.vo;

import lombok.Data;

@Data
public class QaImgVO {
    private String qaCode;
    private String qaOriginFileName;//실제 파일 이름
    private String qaAttachedFileName;//서버 파일 이름
    private int qaBoardNum;
}
