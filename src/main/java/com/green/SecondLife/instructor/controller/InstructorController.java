package com.green.SecondLife.instructor.controller;

import com.green.SecondLife.instructor.service.InstructorService;
import com.green.SecondLife.instructor.vo.InstructorImgVO;
import com.green.SecondLife.instructor.vo.InstructorVO;
import com.green.SecondLife.instructor.vo.SubjectVO;
import com.green.SecondLife.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/instructor")
public class InstructorController {
    private final InstructorService instructorService;
    //강사등록 페이지로 이동
    @GetMapping("/insertInstructorForm")
    public String insertInstructorForm(Model model){
        //강사 전공 카테고리 조회
        model.addAttribute("subjectList", instructorService.selectSubjectList());
        return "admin/insert_instructor";
    }
    //강사 등록 기능 + 이미지
    @PostMapping("/insertInstructor")
    public String insertInstructor(InstructorVO instructorVO, Model model, MultipartFile instructorImg){
        //다음 강사 코드 조회 + 그걸 instructorVO에 넣기
        instructorVO.setInstructorCode(instructorService.selectNextInstructorCode());
        //강사 이미지가 들어갈 통 하나
        InstructorImgVO instructorImgVO = UploadUtil.uploadInstructorFile(instructorImg);
        //강사VO에 강사이미지 넣기
        instructorVO.setInstructorImgVO(instructorImgVO);
        instructorService.insertInstructor(instructorVO);
        return "redirect:/instructor/selectInstructorList";
    }
    //강사 목록 페이지
    @GetMapping("/selectInstructorList")
    public String selectInstructorList(Model model){
        model.addAttribute("instructorList", instructorService.selectInstuctorList());
        return "admin/manage_instructor";
    }
    //강사 상세 보기
    @GetMapping("/selectInstructorDetail")
    public String selectInstructorDetail(InstructorVO instructorVO, Model model){
        System.out.println(instructorVO);
        model.addAttribute("instructor", instructorService.selectInstructorDetail(instructorVO));
        return "admin/instructor_detail";
    }
    //강사 삭제 기능
    @GetMapping("/deleteInstructor")
    public String deleteInstructor(InstructorVO instructorVO){
        instructorService.deleteInstructor(instructorVO);
        return "redirect:/instructor/selectInstructorList";
    }
}
