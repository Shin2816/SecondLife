package com.green.SecondLife.lecture.vo;

import lombok.Data;

@Data
public class LectureReviewVO {
    private String lectureReviewCode;
    private String lectureCode;
    private String instructorCode;
    private String studentCode;
    private int lectureStarPoint;
    private String lectureReviewContent;
    private String lectureReviewDate;
}
