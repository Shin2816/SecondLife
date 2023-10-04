package com.green.SecondLife.lecture.controller;

import com.green.SecondLife.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {
    private final LectureService lectureService;

    //강좌등록 페이지로 이동
    @GetMapping("/insertLectureForm")
    public String insertLectureForm(){
        return "admin/insert_lecture";
    }
}
