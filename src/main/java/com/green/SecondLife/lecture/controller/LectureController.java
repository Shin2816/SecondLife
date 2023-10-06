package com.green.SecondLife.lecture.controller;

import com.green.SecondLife.instructor.service.InstructorService;
import com.green.SecondLife.lecture.service.LectureService;
import com.green.SecondLife.lecture.vo.LectureVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {
    private final LectureService lectureService;
    private final InstructorService instructorService;

    //강좌등록 페이지로 이동
    @GetMapping("/insertLectureForm")
    public String insertLectureForm(Model model){
        //강좌과목 조회
        model.addAttribute("subjectList",instructorService.selectSubjectList());
        //강사목록 조회
        model.addAttribute("instructorList", instructorService.selectInstuctorList());
        return "admin/insert_lecture";
    }
    //강좌 등록 기능
    @PostMapping("/insertLecture")
    public String insertLecture(LectureVO lectureVO){
        System.out.println(lectureVO);
        lectureService.insertLecture(lectureVO);
        return "redirect:/lecture/selectLectureList";
    }
    //강좌 목록 조회
    @GetMapping("/selectLectureList")
    public String selectLectureList(Model model){
        model.addAttribute("lectureList", lectureService.selectLectureList());
        return "admin/manage_lecture";
    }
    //강좌 상세 정보 조회
    @GetMapping("/selectLectureDetail")
    public String selectLectureDetail(LectureVO lectureVO, Model model){
        model.addAttribute("lecture", lectureService.selectLectureDetail(lectureVO));
        return "admin/lecture_detail";
    }
    //강좌 삭제 기능
    @GetMapping("/deleteLecture")
    public String deleteLecture(LectureVO lectureVO){
        lectureService.deleteLecture(lectureVO);
        return "redirect:/lecture/selectLectureList";
    }
}
