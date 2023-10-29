package com.green.SecondLife.community.vo;

import lombok.Data;

@Data
public class GalleryImgVO {
    private String galleryCode;
    private String galOriginFileName; // 실제 파일 이름
    private String galAttachedFileName; // 서버 파일 이름
    private int galBoardNum; // BOARD_GALLERY_LIST와 외래키 체결되어짐
}
