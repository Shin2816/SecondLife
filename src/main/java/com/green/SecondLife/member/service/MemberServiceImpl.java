package com.green.SecondLife.member.service;

import com.green.SecondLife.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final SqlSessionTemplate sqlSession;

    //로그인
    @Override
    public MemberVO selectlogin(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.memberSelect", memberVO);
    }

    //회원가입
    @Override
    public void insertMember(MemberVO memberVO) {
        sqlSession.insert("memberMapper.memberInsert", memberVO);
    }
}
