package com.green.SecondLife.lecture.vo;

import lombok.Data;

@Data
public class LectureVO {
    private String lectureCode;
    private String lectureTitle;
    private String lectureEventCode;
    private int lectureStudent;
    private String lecturePeriod;
    private int lecturePrice;
    private String instructorCode;
    private String instructorName;
}
