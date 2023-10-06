package com.green.SecondLife.instructor.vo;

import lombok.Data;

@Data
public class InstructorVO {
    private String instructorCode;
    private String instructorMajor;
    private String instructorName;
    private int instructorAge;
    private String instructorGender;
    private String instructorPhone;
    private String[] instructorPhones;
    private String instructorAddr;
    private String instructorJoinDate;
    private InstructorImgVO instructorImgVO;
}
