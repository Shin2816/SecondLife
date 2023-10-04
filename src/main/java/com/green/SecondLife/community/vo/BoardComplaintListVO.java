package com.green.SecondLife.community.vo;

import lombok.Data;

@Data
public class BoardComplaintListVO {
    private int comBoardNum;
    private String comBoardTitle;
    private String comBoardContent;
    private String comBoardWriter;
    private String comCreateDate;
    private int comBoardReadCnt;
    private String comBoardComplaintType; // 민원타입  예) 시설보수, 교육일정변경 등
    private String comBoardIsComplete; // 민원 처리상태
}
