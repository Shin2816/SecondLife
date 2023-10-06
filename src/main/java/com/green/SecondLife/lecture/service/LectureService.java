package com.green.SecondLife.lecture.service;

import com.green.SecondLife.lecture.vo.LectureVO;

import java.util.List;

public interface LectureService {
    //강좌 등록 기능
    public void insertLecture(LectureVO lectureVO);
    //강좌 목록 조회 기능
    public List<LectureVO> selectLectureList();
    //강좌 상세 정보 조회 기능
    public LectureVO selectLectureDetail(LectureVO lectureVO);
    //강좌 삭제 기능
    public void deleteLecture(LectureVO lectureVO);
}
