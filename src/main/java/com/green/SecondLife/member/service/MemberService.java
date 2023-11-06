package com.green.SecondLife.member.service;

import com.green.SecondLife.member.vo.MemberVO;

import java.util.List;

public interface MemberService {

    //로그인
    public MemberVO selectlogin(String memberId);
    //회원가입
    public void insertMember(MemberVO memberVO);
    //아이디 중복 체크
    public boolean selectId(MemberVO memberVO);
    //회원 정보 수정
    public void memberUpdate(MemberVO memberVO);
    //회원 정보 검색
    public MemberVO selectMember(String memberId);
    //모든 회원 검색
    public List<MemberVO> selectAllMember();
    //관리자 회원 수정
    public void manageMemberUpdate(MemberVO memberVO);
    //관리자 회원 삭제
    public void manageMemberDelete(String memberId);
}
