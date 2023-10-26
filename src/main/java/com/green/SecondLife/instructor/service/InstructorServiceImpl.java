package com.green.SecondLife.instructor.service;

import com.green.SecondLife.instructor.vo.InstructorImgVO;
import com.green.SecondLife.instructor.vo.InstructorVO;
import com.green.SecondLife.lecture.vo.LectureEventVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService{
    private final SqlSessionTemplate sqlSession;
    //관리자용 강사 등록 기능
    // 1. 다음 강사 코드 조회
    @Override
    public String adminSelectNextInstructorCode() {
        return sqlSession.selectOne("instructorMapper.adminSelectNextInstructorCode");
    }
    // 2. 강사 등록을 위한 강좌 종목 리스트 조회 기능
    @Override
    public List<LectureEventVO> adminSelectLectureEventListForInsertInstructor() {
        return sqlSession.selectList("lectureMapper.adminSelectLectureEventListForInsertInstructor");
    }

    // 3. 강사 등록 기능 + 강사 이미지 등록 기능
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void adminInsertInstructor(InstructorVO instructorVO) {
        sqlSession.insert("instructorMapper.adminInsertInstructor", instructorVO);
        sqlSession.insert("instructorMapper.adminInsertInstructorImg", instructorVO);
    }
    //관리자용 강사 목록 조회 기능
    @Override
    public List<InstructorVO> adminSelectInstuctorList() {
        return sqlSession.selectList("instructorMapper.adminSelectInstructorList");
    }
    //관리자용 강사 상세 조회 기능
    @Override
    public InstructorVO adminSelectInstructorDetail(InstructorVO instructorVO) {
        return sqlSession.selectOne("instructorMapper.adminSelectInstructorDetail", instructorVO);
    }
    //관리자용 강사 정보 수정 기능
    @Override
    public void adminUpdateInstructorInfo(InstructorVO instructorVO) {
        sqlSession.update("instructorMapper.adminUpdateInstructorInfo", instructorVO);
    }
    //관리자용 강사 삭제 기능
    @Override
    public void adminDeleteInstructor(InstructorVO instructorVO) {
        sqlSession.delete("instructorMapper.adminDeleteInstructor", instructorVO);
    }
}
