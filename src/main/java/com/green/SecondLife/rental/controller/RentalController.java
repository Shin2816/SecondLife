package com.green.SecondLife.rental.controller;

import com.green.SecondLife.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;


    //테스트
    @GetMapping("/test")
    public String test(){
        return "/rental/test";
    }

    //테스트 비동기
    @ResponseBody
    @PostMapping("/calTest")
    public String calTest(String facilityCode, String rentalDate){
        System.out.println(facilityCode);
        System.out.println(rentalDate);
        return rentalDate;
    }
}
