package com.green.SecondLife.lecture.controller;

import com.green.SecondLife.instructor.service.InstructorService;
import com.green.SecondLife.lecture.service.LectureService;
import com.green.SecondLife.lecture.vo.*;
import com.green.SecondLife.member.service.MemberService;
import com.green.SecondLife.member.vo.MemberVO;
import com.green.SecondLife.member.vo.SubMenuVO;
import com.green.SecondLife.util.UploadUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {
    private final LectureService lectureService;
    private final InstructorService instructorService;
    private final MemberService memberService;

    //관리자용 강좌 종목 개설 페이지
    @GetMapping("/adminInsertLectureEventForm")
    public String adminInsertLectureEventForm(SubMenuVO subMenuVO){
        subMenuVO.setMenuCode("MENU_002");
        return "admin/admin_insert_lecture_event_form";
    }
    //관리자용 강좌 종목 개설 기능 (이미지 등록 기능도 포함)
    @PostMapping("/adminInsertLectureEvent")
    public String adminInsertLectureEvent(LectureEventVO lectureEventVO, MultipartFile multipartImgFile, SubMenuVO subMenuVO){
        System.out.println(subMenuVO);
        //인서트할 다음 강좌 종목 코드 조회
        System.out.println(multipartImgFile);
        String lectureEventCode = lectureService.adminSelectNextLectureEventCode();
        // lectureEventImgVO에 원본파일명 + 첨부파일명 set하기
        LectureEventImgVO lectureEventImgVO = UploadUtil.uploadLectureEventImgFile(multipartImgFile);
        // lectureEventVO에 위 데이터들 set 하기 (다음 강좌 종목 코드 + ImgVO)
        // 나머지는 input 태그를 통해 넘어올 예정
        lectureEventVO.setLectureEventCode(lectureEventCode);
        lectureEventVO.setLectureEventImgVO(lectureEventImgVO);
        lectureService.adminInsertLectureEventAndImg(lectureEventVO);
        return "redirect:/admin/adminLectureEventList";
    }
    //관리자용 강좌 종목 리스트 페이지
    @GetMapping("/adminLectureEventList")
    public String adminLectureEventList(Model model, SubMenuVO subMenuVO){
        subMenuVO.setMenuCode("MENU_002");
        //관리자용 강좌 종목 리스트 조회 + 보내기 기능
        model.addAttribute("lectureEventList", lectureService.adminSelectLectureEventList());
        return "admin/admin_lecture_event_list";
    }

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

    //↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ //
    //관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//
    //↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ //

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
    //강좌 리뷰 폼으로 이동
    @GetMapping("/goLectureReviewForm")
    public String goLectureReviewForm(Model model, StudentVO studentVO, HttpSession session, LectureVO lectureVO){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        studentVO.setMemberId(loginInfo.getMemberId());
        System.out.println(studentVO);
        model.addAttribute("lecture", lectureService.selectLectureDetail(lectureVO));
        model.addAttribute("student", lectureService.selectTheStudent(studentVO));
        return "lecture/lecture_review_form";
    }
    //강좌 리뷰 등록
    @PostMapping("/insertLectureReview")
    public String insertLectureReview(LectureReviewVO lectureReviewVO){
        lectureService.insertLectureReview(lectureReviewVO);
        return "redirect:/lecture/selectLectureList";
    }
}
