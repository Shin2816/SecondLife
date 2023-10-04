package com.green.SecondLife.member.service;

import com.green.SecondLife.member.vo.MemberVO;

import java.util.List;

public interface MemberService {

    //로그인
    public MemberVO selectlogin(MemberVO memberVO);
    //회원가입
    public void insertMember(MemberVO memberVO);
    //아이디 중복 체크
    public boolean selectId(MemberVO memberVO);

}
