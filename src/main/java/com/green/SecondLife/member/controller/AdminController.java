package com.green.SecondLife.member.controller;

import com.green.SecondLife.member.vo.SubMenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/admin_main")
    public String adminMain(SubMenuVO subMenuVO){
        return "/admin/admin_main";
    }
}
