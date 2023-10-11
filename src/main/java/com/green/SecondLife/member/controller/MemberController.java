package com.green.SecondLife.member.controller;

import com.green.SecondLife.community.service.CommunityService;
import com.green.SecondLife.community.vo.BoardFreeListVO;
import com.green.SecondLife.member.service.MemberService;
import com.green.SecondLife.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.lang.reflect.Member;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final CommunityService communityService;

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
    public String login(MemberVO memberVO, HttpSession session, BoardFreeListVO boardFreeListVO, Model model){

        List<BoardFreeListVO> freeList = communityService.selectFreeBoardList(boardFreeListVO);
        model.addAttribute("freeBoardList", freeList);

        MemberVO loginInfo = memberService.selectlogin(memberVO);
        if(loginInfo != null){
            session.setAttribute("loginInfo", loginInfo);
        }

        return "/main";
    }

    //로그아웃 처리 후, 메인페이지로 이동
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        return "/main";
    }

    //아이디 중복처리 비동기 통신
    @ResponseBody
    @PostMapping("/idFetch")
    public boolean idFetch(MemberVO memberVO){
        return memberService.selectId(memberVO);
    }

    //휴대폰 인증처리 비동기 통신
    @ResponseBody
    @PostMapping("/phoneFetch")
    public boolean phoneFetch(String tel){




        return true;
    }



    //회원 정보 수정폼으로 이동
    @GetMapping("/updateMemberForm")
    public String updateMemberForm(MemberVO memberVO, Model model){
        model.addAttribute("member", memberService.selectMember(memberVO));
        return "/member/updateMember";
    }

    //회원정보 수정
    @PostMapping("/updateMember")
    public String updateMember(MemberVO memberVO){
        memberService.memberUpdate(memberVO);
        return "/main";
    }
}
