package com.green.SecondLife.member.controller;

import com.green.SecondLife.community.service.CommunityService;
import com.green.SecondLife.community.vo.BoardFreeListVO;
import com.green.SecondLife.lecture.service.LectureService;
import com.green.SecondLife.member.service.MemberService;
import com.green.SecondLife.member.vo.MemberVO;
import com.green.SecondLife.util.ConstantVariable;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
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

    //로그인 폼 화면으로 이동
    @GetMapping("/loginForm")
    public String loginForm(){
        return "/member/login";
    }

    //회원가입 폼 화면으로 이동
    @GetMapping("/insertMemberForm")
    public String insertMemberForm() {

        return "/member/insertMember";
    }

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

    //휴대폰 인증번호 발신 비동기 통신
    @ResponseBody
    @PostMapping("/phoneFetch")
    public void phoneFetch(String memberTel, HttpSession session){

        int random = (int)(Math.random()*9000+1000);
        DefaultMessageService messageService =  NurigoApp.INSTANCE.initialize(ConstantVariable.PHONE_CHECK_KEY, ConstantVariable.PHONE_CHECK_SECRET, "https://api.coolsms.co.kr");
        Message message = new Message();
        message.setFrom("01029532816");
        message.setTo(memberTel);
        message.setText("발송된 숫자는 ["+random+"] 입니다. 입력칸에 입력해주세요.");

        try {
            messageService.send(message);
            session.setAttribute("checkNum", random);
        } catch (NurigoMessageNotReceivedException exception) {
            // 발송에 실패한 메시지 목록을 확인할 수 있습니다!
            System.out.println(exception.getFailedMessageList());
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    //인증번호 확인 비동기 통신
    @ResponseBody
    @PostMapping("/checkFetch")
    public boolean checkFetch(String telCheck, HttpSession session){

        if(session.getAttribute("checkNum").toString().equals(telCheck)){
            session.removeAttribute("checkNum");
            return true;
        }
        else {
            return false;
        }

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
