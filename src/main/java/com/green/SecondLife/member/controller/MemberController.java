package com.green.SecondLife.member.controller;

import com.green.SecondLife.member.service.MemberService;
import com.green.SecondLife.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Member;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //로그인 폼 화면으로 이동
    @GetMapping("/loginForm")
    public String loginForm(){
        return "/member/login";
    }

    //회원가입 폼 화면으로 이동
    @GetMapping("/insertMemberForm")
    public String insertMemberForm() { return "/member/insertMember"; }

    //회원가입 처리 후, 메인페이지로 이동
    @PostMapping("/insertMember")
    public String insertMember(MemberVO memberVO){
        memberService.insertMember(memberVO);
        return "/main";
    }

    //로그인 처리 후, 메인페이지로 이동.
    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session){

        MemberVO loginInfo = memberService.selectlogin(memberVO);
        if(loginInfo != null){
            session.setAttribute("loginInfo", loginInfo);
        }

        return "/main";
    }


}
