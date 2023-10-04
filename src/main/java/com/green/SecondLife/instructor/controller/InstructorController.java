package com.green.SecondLife.instructor.controller;

import com.green.SecondLife.instructor.service.InstructorService;
import com.green.SecondLife.instructor.vo.InstructorVO;
import com.green.SecondLife.instructor.vo.SubjectVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/instructor")
public class InstructorController {
    private final InstructorService instructorService;
    //강사등록 페이지로 이동
    @GetMapping("/insertInstructorForm")
    public String insertInstructorForm(Model model){
        //강사 전공 카테고리 조회
        System.out.println(instructorService.selectSubjectList());
        model.addAttribute("subjectList", instructorService.selectSubjectList());
        return "admin/insert_instructor";
    }
    //강사 등록 기능
    @PostMapping("/insertInstructor")
    public String insertInstructor(InstructorVO instructorVO){
        System.out.println(instructorVO);
        instructorService.insertInstructor(instructorVO);
        return "";
    }
}
