package com.green.SecondLife.instructor.vo;

import com.green.SecondLife.lecture.vo.LectureEventVO;
import lombok.Data;

@Data
public class InstructorVO {
    private String instructorCode;
    private String lectureEventCode;
    private String instructorName;
    private int instructorAge;
    private String instructorGender;
    private String instructorPhone;
    private String[] instructorPhones;
    private String instructorAddr;
    private String[] instructorAddrs;
    private String instructorJoinDate;
    private InstructorImgVO instructorImgVO;
    private LectureEventVO lectureEventVO;
}
