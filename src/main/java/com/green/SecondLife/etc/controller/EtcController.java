package com.green.SecondLife.etc.controller;

import com.green.SecondLife.etc.service.EtcService;
import com.green.SecondLife.member.vo.SubMenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/etc")
@RequiredArgsConstructor
public class EtcController {

    private final EtcService etcService;

    @GetMapping("/privacy")
    public String privacy(SubMenuVO subMenuVO){
        return "/user/etc/privacy";
    }

    @GetMapping("/sitemap")
    public String sitemap(SubMenuVO subMenuVO){
        return "/user/etc/sitemap";
    }

}
