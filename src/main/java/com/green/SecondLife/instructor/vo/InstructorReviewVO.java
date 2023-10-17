package com.green.SecondLife.instructor.vo;

import lombok.Data;

@Data
public class InstructorReviewVO {
    private String instructorReviewCode;
    private String instructorCode;
    private String memberId;
    private int instructorStarPoint;
    private String instructorReviewContent;
    private String instructorReviewDate;
}
