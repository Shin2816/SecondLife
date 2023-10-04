package com.green.SecondLife.instructor.controller;

import com.green.SecondLife.instructor.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    //강사등록 페이지로 이동
    @GetMapping("/insertInstructorForm")
    public String insertInstructorForm(){
        return "admin/insert_instructor";
    }
}
