package com.green.SecondLife.community.vo;

import lombok.Data;

@Data
public class BoardCommentListVO {
    private int commentId;
    private String commentContent;
    private String commentWriter;
    private String commentCreateDate;
    private int commentNum;
    private String qaCheckPwInput;
}
