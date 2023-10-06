package com.green.SecondLife.community.vo;

import lombok.Data;

import java.util.List;

@Data
public class BoardFreeListVO {
    private int freeBoardNum;
    private String freeBoardTitle;
    private String freeBoardContent;
    private String freeBoardWriter;
    private String freeCreateDate;
    private int freeBoardReadCnt;
    private String searchType;
    private String searchValue;
    private List<BoardCommentListVO> freeBoardComment;// 자유게시판 클래스는 댓글 클래스를 여러개 갖는다.
}
