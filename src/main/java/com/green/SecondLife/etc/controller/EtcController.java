package com.green.SecondLife.etc.controller;

import com.green.SecondLife.etc.service.EtcService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/etc")
@RequiredArgsConstructor
public class EtcController {

    private final EtcService etcService;

    @GetMapping("/privacy")
    public String privacy(String menuCode, Model model){
        model.addAttribute("menuCode", menuCode);
        return "/user/etc/privacy";
    }

    @GetMapping("/sitemap")
    public String sitemap(String menuCode, Model model){
        model.addAttribute("menuCode", menuCode);
        return "/user/etc/sitemap";
    }

}
