package com.green.SecondLife.lecture.vo;

import com.green.SecondLife.member.vo.SubMenuVO;
import lombok.Data;

@Data
public class LectureEventVO {
    private String lectureEventCode;
    private String lectureEventName;
    private String lectureEventContent;
    private LectureEventImgVO lectureEventImgVO;
}
