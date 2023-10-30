package com.green.SecondLife.lecture.controller;

import com.green.SecondLife.instructor.service.InstructorService;
import com.green.SecondLife.lecture.service.LectureService;
import com.green.SecondLife.lecture.vo.*;
import com.green.SecondLife.member.service.MemberService;
import com.green.SecondLife.member.vo.MemberVO;
import com.green.SecondLife.member.vo.SubMenuVO;
import com.green.SecondLife.util.UploadUtil;
import jakarta.servlet.http.HttpSession;
import kotlin.contracts.ReturnsNotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import retrofit2.http.GET;

import javax.sound.sampled.SourceDataLine;
import java.awt.*;
import java.lang.reflect.Member;
import java.util.List;
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
        return "admin/admin_insert_lecture_event_form";
    }
    //관리자용 강좌 종목 개설 기능 (이미지 등록 기능도 포함)
    @PostMapping("/adminInsertLectureEvent")
    public String adminInsertLectureEvent(LectureEventVO lectureEventVO, MultipartFile lectureEventImg, RedirectAttributes redirectAttributes){
        // 인서트할 다음 강좌 종목 코드 조회
        String lectureEventCode = lectureService.adminSelectNextLectureEventCode();
        // lectureEventImgVO에 원본파일명 + 첨부파일명 set하기
        LectureEventImgVO lectureEventImgVO = UploadUtil.uploadLectureEventImgFile(lectureEventImg);
        // lectureEventVO에 위 데이터들 set 하기 (다음 강좌 종목 코드 + ImgVO)
        // 나머지는 input 태그를 통해 넘어올 예정
        lectureEventVO.setLectureEventCode(lectureEventCode);
        lectureEventVO.setLectureEventImgVO(lectureEventImgVO);
        // 인서트
        lectureService.adminInsertLectureEventAndImg(lectureEventVO);
        redirectAttributes.addAttribute("menuCode", "MENU_002");
        return "redirect:/lecture/adminLectureEventList";
    }
    //관리자용 강좌 종목 리스트 페이지
    @GetMapping("/adminLectureEventList")
    public String adminLectureEventList(Model model,SubMenuVO subMenuVO){
        //관리자용 강좌 종목 리스트 조회 + 보내기 기능
        model.addAttribute("lectureEventList", lectureService.adminSelectLectureEventList());
        return "admin/admin_lecture_event_list";
    }
    //관리자용 강좌 종목 상세 페이지
    @GetMapping("/adminLectureEventDetail")
    public String adminLectureEventDetail(LectureEventVO lectureEventVO, Model model, SubMenuVO subMenuVO){
        model.addAttribute("lectureEvent", lectureService.adminSelectLectureEventDetail(lectureEventVO));
        return "admin/admin_lecture_event_detail";
    }
    //관리자용 강좌 종목 정보 수정 폶
    @GetMapping("/adminUpdateLectureEventInfoForm")
    public String adminUpdateLectureEventForm(LectureEventVO lectureEventVO, Model model, SubMenuVO subMenuVO){
        model.addAttribute("lectureEvent", lectureService.adminSelectLectureEventDetail(lectureEventVO));
        return "admin/admin_update_lecture_event_info_form";
    }
    //관리자용 강좌 종목 정보 수정 기능
    @PostMapping("/adminUpdateLectureEventInfo")
    public String adminUpdateLectureEventInfo(LectureEventVO lectureEventVO, RedirectAttributes redirectAttributes){
        lectureService.adminUpdateLectureEventInfo(lectureEventVO);
        redirectAttributes.addAttribute("lectureEventCode", lectureEventVO.getLectureEventCode());
        redirectAttributes.addAttribute("menuCode", "MENU_002");
        return "redirect:/lecture/adminLectureEventDetail";
    }
    //관리자용 강좌 종목 삭제 기능
    @GetMapping("/adminDeleteLectureEvent")
    public String adminDeleteLectureEvent(LectureEventVO lectureEventVO, RedirectAttributes redirectAttributes){
        lectureService.adminDeleteLectureEvent(lectureEventVO);
        redirectAttributes.addAttribute("menuCode", "MENU_002");
        return "redirect:/lecture/adminLectureEventList";
    }
    //관리자용 강좌 리뷰 리스트 페이지
    @GetMapping("/adminLectureReviewList")
    public String adminLectureReviewList(Model model, SubMenuVO subMenuVO){
        model.addAttribute("lectureReviewList", lectureService.adminSelectLectureReviewList());
        return "admin/admin_lecture_review_list";
    }
    //관리자용 수업 등록 페이지로 이동
    @GetMapping("/adminInsertLectureForm")
    public String adminInsertLectureForm(Model model, SubMenuVO subMenuVO){
        //강사 목록 조회
        model.addAttribute("instructorList", instructorService.adminSelectInstuctorList());
        //강좌 종목 조회
        model.addAttribute("lectureEventList", lectureService.adminSelectLectureEventList());
        return "admin/admin_insert_lecture_form";
    }
    //관리자용 수업 등록 기능 후 -> adminLectureList로 리다이렉트
    @PostMapping("/adminInsertLecture")
    public String adminInsertLecture(LectureVO lectureVO, RedirectAttributes redirectAttributes){
        System.out.println(lectureVO);
        lectureService.adminInsertLecture(lectureVO);
        redirectAttributes.addAttribute("menuCode", "MENU_002");
        return "redirect:/lecture/adminLectureList";
    }
    //관리자용 수업 목록 페이지
    @GetMapping("/adminLectureList")
    public String adminLectureList(Model model, SubMenuVO subMenuVO, String instructorCode){
        model.addAttribute("lectureList", lectureService.adminSelectLectureList(instructorCode));
        return "admin/admin_lecture_list";
    }
    //관리자용 수업 상세 조회
    @GetMapping("/adminSelectLectureDetail")
    public String selectLectureDetail(LectureVO lectureVO, Model model, SubMenuVO subMenuVO){
        model.addAttribute("lecture", lectureService.adminSelectLectureDetail(lectureVO));
        return "admin/admin_lecture_detail";
    }
    //관리자용 수업 삭제 기능
    @GetMapping("/adminDeleteLecture")
    public String deleteLecture(LectureVO lectureVO, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("menuCode", "MENU_002");
        lectureService.adminDeleteLecture(lectureVO);
        return "redirect:/lecture/adminLectureList";
    }

    //↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ //
    //관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//관리자//
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
    //유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//유저//
    //↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ //
    // 수업 목록 페이지
    @GetMapping("/lectureList")
    public String lectureList(Model model, String lectureEventCode){
        model.addAttribute("lectureEventList", lectureService.adminSelectLectureEventList());
        model.addAttribute("lectureList", lectureService.selectLectureList(lectureEventCode));
        return "/lecture/lecture_list";
    }
    // 수업 목록 페이지 fetch ( 강좌 종목별 )
    @ResponseBody
    @PostMapping("/lectureListFetch")
    public List<LectureVO> lectureListFetch(String lectureEventCode){
        System.out.println(lectureEventCode);
        return lectureService.selectLectureList(lectureEventCode);
    }
    // 수업 상세 페이지
    @GetMapping("/lectureDetail")
    public String lectureDetail(LectureVO lectureVO, Model model, SubMenuVO subMenuVO, LectureEventVO lectureEventVO){
        System.out.println(lectureVO);
        System.out.println(lectureEventVO);
        String lectureEventCode = lectureService.adminSelectLectureDetail(lectureVO).getLectureEventCode();
        lectureEventVO.setLectureEventCode(lectureEventCode);
        model.addAttribute("lectureEvent", lectureService.adminSelectLectureEventDetail(lectureEventVO));
        model.addAttribute("lecture", lectureService.adminSelectLectureDetail(lectureVO));
        return "/lecture/lecture_detail";
    }
    // 강좌 구매 페이지
    @GetMapping("/goLectureApplyForm")
    public String goLectureApplyForm(LectureVO lectureVO, Model model, HttpSession session, LectureEventVO lectureEventVO){
        lectureEventVO.setLectureEventCode(lectureService.adminSelectLectureDetail(lectureVO).getLectureEventCode());
        model.addAttribute("lectureEventInfo", lectureService.adminSelectLectureEventDetail(lectureEventVO));
        model.addAttribute("lectureInfo", lectureService.adminSelectLectureDetail(lectureVO));
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        model.addAttribute("memberInfo", memberService.selectMember(loginInfo.getMemberId()));
        return "lecture/lecture_apply_form";
    }
    //수강생테이블로 수강생 인서트
    @RequestMapping("/insertStudent")
    public String insertStudent(StudentVO studentVO, RedirectAttributes redirect, HttpSession session){
        studentVO.setMemberId(((MemberVO) session.getAttribute("loginInfo")).getMemberId());
        lectureService.insertStudent(studentVO);
        redirect.addAttribute("lectureCode", studentVO.getLectureCode());
        return "redirect:/lecture/selectStudentList";
    }
    //수강생 목록 조회
    @RequestMapping("/selectStudentList")
    public String selectStudentList(Model model, @RequestParam String lectureCode, StudentVO studentVO, LectureVO lectureVO){
        Optional.ofNullable(lectureCode).ifPresent(lc -> studentVO.setLectureCode(lc));
        System.out.println(studentVO);
        model.addAttribute("lectureInfo", lectureService.adminSelectLectureDetail(lectureVO));
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
        model.addAttribute("lecture", lectureService.adminSelectLectureDetail(lectureVO));
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
