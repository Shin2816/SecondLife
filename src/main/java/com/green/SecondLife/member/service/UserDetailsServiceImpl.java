package com.green.SecondLife.member.service;

import com.green.SecondLife.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        MemberVO loginInfo = memberService.selectlogin(memberId);

        UserDetails member = User.withUsername(loginInfo.getMemberId())
                                .password(loginInfo.getMemberPW())
                                .roles(loginInfo.getMemberRoll())
                                .build();
        return member;
    }
}
