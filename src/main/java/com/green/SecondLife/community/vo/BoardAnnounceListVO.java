package com.green.SecondLife.community.vo;

import lombok.Data;

import java.util.List;

@Data
public class BoardAnnounceListVO extends PageVO{
    private int anBoardNum;
    private String anBoardTitle;
    private String anBoardContent;
    private String anBoardWriter;
    private String anCreateDate;
    private int anBoardReadCnt;
    private String searchType;
    private String searchValue;
}
