package com.green.SecondLife.center.controller;

import com.green.SecondLife.center.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/center")
@RequiredArgsConstructor
public class CenterController {
    private final CenterService centerService;

    @GetMapping("/insertFacility")
    public String insertFacility(){
        return "admin/insert_facility";
    }
}
