package com.green.SecondLife.rental.controller;

import com.green.SecondLife.member.service.MemberService;
import com.green.SecondLife.member.vo.MemberVO;
import com.green.SecondLife.member.vo.SubMenuVO;
import com.green.SecondLife.rental.service.RentalService;
import com.green.SecondLife.rental.vo.RentalFacilityVO;
import com.green.SecondLife.util.ConstantVariable;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.*;

@Controller
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;
    private final MemberService memberService;

    // 시설대관-이용안내 페이지
    @GetMapping("/rentalGuide")
    public String rentalGuide(SubMenuVO subMenuVO){
        return "/rental/rental_guide";
    }

    //시설대관 화면
    @GetMapping("/rentalFacility")
    public String rentalFacility(SubMenuVO subMenuVO){
        return "/rental/rental_facility";
    }

    //풀 캘린더
    @ResponseBody
    @PostMapping("/rentalCalendar")
    public List<RentalFacilityVO> rentalCalendar(RentalFacilityVO rentalFacilityVO){
        List<RentalFacilityVO> rentalTimeList = rentalService.selectRentalFacility(rentalFacilityVO);
        return rentalTimeList;
    }

    // 대관예약 신청하기
    @PostMapping("/signUpRentalFacility")
    public String insertRentalFacility(RentalFacilityVO rentalFacilityVO){
        // 빈 List<> 생성( 후에 setFacilityList() )
        List<RentalFacilityVO> rentalList = new ArrayList<>();
        
        // 2개이상 선택시 rentTimeCode = "time1, time2, ..." 로 받아와짐
        // split을 이용하여 나누기
        String rentTimeCode = rentalFacilityVO.getRentalTimeCode();
        String[] timeCodeArr = rentTimeCode.split(",");

        // 선택된 개수만큼 반복문을 돌려 빈 리스트에 담기
        for(int i = 0; i < timeCodeArr.length; i++){
            RentalFacilityVO rentVO = new RentalFacilityVO();

            rentVO.setFacilityCode(rentalFacilityVO.getFacilityCode());
            rentVO.setRentalDate(rentalFacilityVO.getRentalDate());
            rentVO.setRentalTimeCode(timeCodeArr[i]);
            rentVO.setRentalCharge(rentalFacilityVO.getRentalCharge());
            rentVO.setRentalUser(rentalFacilityVO.getRentalUser());
            rentVO.setRentalTeam(rentalFacilityVO.getRentalTeam());
            rentVO.setRentalPurpose(rentalFacilityVO.getRentalPurpose());

            rentalList.add(rentVO);
        }
        rentalFacilityVO.setFacilityList(rentalList);

        rentalService.insertRentalFacility(rentalFacilityVO);

        return "redirect:/rental/rentalFacility?menuCode="+ ConstantVariable.MENU_CODE_RENTAL_FACILITY;
    }

    //사용자(마이페이지)-대관신청 목록 조회
    @GetMapping("/myRentalHistory")
    public String myRentalHistory(RentalFacilityVO rentalFacilityVO, HttpSession session, Model model, SubMenuVO subMenuVO){
        //세션 사용자이름 불러오기
        MemberVO member = (MemberVO)session.getAttribute("loginInfo");
        rentalFacilityVO.setRentalUser(member.getMemberName());
        model.addAttribute("memberInfo", memberService.selectMember(member.getMemberId()));
        model.addAttribute("myRentalList", rentalService.selectMyRentalList(rentalFacilityVO));
        return "/rental/my_rental_history";
    }

    //사용자(마이페이지)-대관신청 취소
    @GetMapping("/deleteSignRental")
    public String deleteSignRental(String rentalSignCode){
        rentalService.deleteSignRental(rentalSignCode);
        return "redirect:/rental/myRentalHistory?menuCode="+ConstantVariable.MENU_CODE_MY_HISTORY;
    }

    //관리자(대관관리) - 목록조회
    @GetMapping("/rentalManageList")
    public String selectRentalList(Model model, SubMenuVO subMenuVO){
        subMenuVO.setMenuCode("MENU_003");
        model.addAttribute("rentalList", rentalService.selectRentalList());
        return "/admin/manage_rental";
    }

    //(관리자)대관관리 상태변경(현상태-승인대기:2 / 반려: 0, 완료: 1, 승인->결제 대기: 3)
    //반려하기
    @PostMapping("/updateStateReject")
    public String updateRentalStatus0(String rejectReason, RentalFacilityVO rentalFacilityVO){
        System.out.println(rejectReason);
        rentalFacilityVO.setRejectReason(rejectReason);
        rentalService.updateRentalStatus0(rentalFacilityVO);
        System.out.println(rentalFacilityVO);

        return "redirect:/rental/rentalManageList";
    }
    //승인하기 -> 결제 대기
    @GetMapping("/updateStatePay")
    public String updateRentalStatus3(String rentalSignCode){
        rentalService.updateRentalStatus3(rentalSignCode);

        return "redirect:/rental/rentalManageList";
    }
    
    //결제하기 -> 완료!
    @ResponseBody
    @PostMapping("/updateRentalStatus1")
    public void updateRentalStatus1(RentalFacilityVO rentalFacilityVO, MemberVO memberVO){
        Map<String, String> updateRental = new HashMap<>();
        updateRental.put("paymentRentalFacilityName", rentalFacilityVO.getFacilityName());
        updateRental.put("paymentName", memberVO.getMemberName());
        updateRental.put("paymentTel", memberVO.getMemberTel());
        updateRental.put("paymentAddr", memberVO.getMemberAddr());
        updateRental.put("rentalSignCode", rentalFacilityVO.getRentalSignCode());
        rentalService.updateRentalStatus1(updateRental);
    }
}
