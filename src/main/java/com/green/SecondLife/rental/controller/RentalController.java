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


import java.util.List;

@Controller
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;


    //테스트
    @GetMapping("/test")
    public String test(){
        return "/rental/rental_facility";
    }

    //테스트 비동기
    @ResponseBody
    @PostMapping("/calTest")
    public List<RentalFacilityVO> calTest(RentalFacilityVO rentalFacilityVO, HttpSession session){
        System.out.println(rentalFacilityVO.getFacilityCode());
        System.out.println(rentalFacilityVO.getRentalDate());

        //세션 사용자이름 불러오기
        MemberVO member = (MemberVO)session.getAttribute("loginInfo");
        rentalFacilityVO.setRentalUser(member.getMemberName());

        List<RentalFacilityVO> rentalTimelList = rentalService.selectRentalFacility(rentalFacilityVO);
        return rentalTimelList;
    }
}
