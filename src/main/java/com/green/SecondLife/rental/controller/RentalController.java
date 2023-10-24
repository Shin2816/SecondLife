package com.green.SecondLife.rental.controller;

import com.green.SecondLife.member.vo.MemberVO;
import com.green.SecondLife.rental.service.RentalService;
import com.green.SecondLife.rental.vo.RentalFacilityVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;


    //화면
    @GetMapping("/test")
    public String test(){
        return "/rental/rental_facility";
    }

    //풀 캘린더
    @ResponseBody
    @PostMapping("/calTest")
    public List<RentalFacilityVO> calTest(RentalFacilityVO rentalFacilityVO, HttpSession session){
        System.out.println(rentalFacilityVO.getFacilityCode());
        System.out.println(rentalFacilityVO.getRentalDate());
        System.out.println(rentalFacilityVO);

        //세션 사용자이름 불러오기
        MemberVO member = (MemberVO)session.getAttribute("loginInfo");

        List<RentalFacilityVO> rentalTimeList = rentalService.selectRentalFacility(rentalFacilityVO);
        return rentalTimeList;
    }

    // 대관예약 신청하기
    @PostMapping("/signUpRentalFacility")
    public String insertRentalFacility(RentalFacilityVO rentalFacilityVO){
        System.out.println(rentalFacilityVO);
        List<RentalFacilityVO> rentalList = new ArrayList<>();

        String rentTimeCode = rentalFacilityVO.getRentalTimeCode();
        String[] timeCodeArr = rentTimeCode.split(",");

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
        System.out.println(rentalFacilityVO.getFacilityList());


        rentalService.insertRentalFacility(rentalFacilityVO);
        return "redirect:/rental/test";
    }
}
