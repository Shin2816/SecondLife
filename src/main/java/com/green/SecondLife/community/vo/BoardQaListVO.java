package com.green.SecondLife.community.vo;

import lombok.Data;

import java.util.List;

@Data
public class BoardQaListVO extends PageVO{
    private int qaBoardNum;
    private String qaBoardTitle;
    private String qaBoardContent;
    private String qaBoardWriter;
    private String qaCreateDate;
    private int qaBoardReadCnt;
    private String searchType;
    private String searchValue;
    private List<BoardCommentListVO> qaBoardComment;// 자유게시판 클래스는 댓글 클래스를 여러개 갖는다.
    private String qaBoardIsClose; // Q&A 공개 여부, 기본값 'CLOSED'
    private String qaBoardPassword; // 관리자는 바로 확인 유저는 비밀번호 체크

}
