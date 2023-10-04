package com.green.SecondLife.community.vo;

import lombok.Data;

@Data
public class BoardEventListVO {
    private int eventBoardNum;
    private String eventBoardTitle;
    private String eventBoardContent;
    private String eventBoardWriter;
    private String eventCreateDate; //이벤트 게시글 작성일 기본값 SYSDATE
    private String startDate; // 이벤트 시작 날짜 기본값 SYSDATE
    private String endDate; // 이벤트 종료 날짜
    private int eventBoardReadCnt;
}