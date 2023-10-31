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
    public MemberVO selectlogin(String memberId) {
        return sqlSession.selectOne("memberMapper.memberSelect", memberId);
    }

    //회원가입
    @Override
    public void insertMember(MemberVO memberVO) {
        sqlSession.insert("memberMapper.memberInsert", memberVO);
    }

    //중복 아이디 체크
    @Override
    public boolean selectId(MemberVO memberVO) {
        String selectId = sqlSession.selectOne("memberMapper.selectId", memberVO);
        return selectId == null;
    }
    //회원 정보 수정
    @Override
    public void memberUpdate(MemberVO memberVO) {
        sqlSession.update("memberMapper.memberUpdate", memberVO);
    }
    //회원 정보 검색
    @Override
    public MemberVO selectMember(String memberId) {
        return sqlSession.selectOne("memberMapper.memberSelectOne", memberId);
    }

}
