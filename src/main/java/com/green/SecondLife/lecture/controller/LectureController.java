package com.green.SecondLife.lecture.controller;

import com.green.SecondLife.instructor.service.InstructorService;
import com.green.SecondLife.lecture.service.LectureService;
import com.green.SecondLife.lecture.vo.LectureVO;
import com.green.SecondLife.lecture.vo.StudentVO;
import com.green.SecondLife.member.service.MemberService;
import com.green.SecondLife.member.vo.MemberVO;
import com.green.SecondLife.member.vo.SubMenuVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {
    private final LectureService lectureService;
    private final InstructorService instructorService;
    private final MemberService memberService;

    //강좌등록 페이지로 이동
    @GetMapping("/insertLectureForm")
    public String insertLectureForm(Model model, SubMenuVO subMenuVO){
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
    public String selectLectureList(Model model, String instructorCode){
        System.out.println("@@@@@@@@@@@@@" + lectureService.selectLectureList(instructorCode));
        model.addAttribute("lectureList", lectureService.selectLectureList(instructorCode));
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
    //수강 신청 페이지로 이동
    @GetMapping("/goLectureApplyForm")
    public String goLectureApplyForm(LectureVO lectureVO, Model model, HttpSession session){
        model.addAttribute("lectureInfo", lectureService.selectLectureDetail(lectureVO));
        MemberVO memberId = (MemberVO)session.getAttribute("loginInfo");
        model.addAttribute("memberInfo", memberService.selectMember(memberId));
        return "lecture/lecture_apply_form";
    }
    //수강생테이블로 수강생 인서트
    @RequestMapping("/insertStudent")
    public String insertStudent(StudentVO studentVO, RedirectAttributes redirect){
        lectureService.insertStudent(studentVO);
        redirect.addAttribute("lectureCode", studentVO.getLectureCode());
        return "redirect:/lecture/selectStudentList";
    }
    //수강생 목록 조회
    @RequestMapping("/selectStudentList")
    public String selectStudentList(Model model, @RequestParam String lectureCode, StudentVO studentVO, LectureVO lectureVO){
        Optional.ofNullable(lectureCode).ifPresent(lc -> studentVO.setLectureCode(lc));
        System.out.println(studentVO);
        model.addAttribute("lectureInfo", lectureService.selectLectureDetail(lectureVO));
        model.addAttribute("studentList", lectureService.selectStudentList(studentVO));
        return "lecture/student_list";
    }
    //수강생 삭제
    @RequestMapping("/deleteStudent")
    public String deleteStudent(StudentVO studentVO, RedirectAttributes redirect, LectureVO lectureVO){
        lectureService.deleteStudent(studentVO);
        System.out.println(lectureVO);
        redirect.addAttribute("lectureCode", lectureVO.getLectureCode());
        return "redirect:/lecture/selectStudentList";
    }
}
