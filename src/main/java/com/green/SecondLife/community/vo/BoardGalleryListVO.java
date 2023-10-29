package com.green.SecondLife.community.vo;

import lombok.Data;

import java.util.List;

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
    private List<GalleryImgVO> galImgList;//board클래스는 img클래스를 여러개 가진다.
    private List<BoardCommentListVO> galBoardComment;// 사진 게시판 클래스는 댓글 클래스를 여러개 갖는다.
}
