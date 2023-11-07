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
    private List<BoardCommentListVO> qaBoardComment;// QnA게시판 클래스는 댓글 클래스를 여러개 갖는다.
    private String qaBoardPassword; // 관리자는 바로 확인 유저는 비밀번호 체크
    private List<QaImgVO> qaImgList;//board클래스는 img클래스를 여러개 가진다.
}
