package com.green.SecondLife.member.vo;

import com.green.SecondLife.rental.vo.RentalFacilityVO;
import lombok.Data;

@Data
public class MemberVO {
    //아이디
    private String memberId;
    //비밀번호
    private String memberPW;
    //이름
    private String memberName;
    //전화번호
    private String memberTel;
    //주소
    private String memberAddr;
    //상세주소
    private String addrDetail;
    //이메일
    private String memberEmail;
    //성별
    private String memberGender;
    //권한
    private String memberRoll;
}
