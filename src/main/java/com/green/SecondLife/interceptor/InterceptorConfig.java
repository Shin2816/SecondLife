package com.green.SecondLife.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final BoardInterceptor boardInterceptor;
    private final LectureInterceptor lectureServiceInterceptor;
    private final AdminMenuInterceptor menuInterceptor;
    private final UserMenuInterceptor userMenuInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //게시판 페이지 정보 출력하는 인터셉터의 실행 설정
        registry.addInterceptor(boardInterceptor)
                .addPathPatterns("/")
                .addPathPatterns("/main", "/member/login", "/member/logout", "/member/insertMember", "/member/updateMember")
                .excludePathPatterns("/**/**Fetch")
                .excludePathPatterns("/images/**", "/js/**", "/css/**");

        //강좌 페이지 정보 출력하는 인터셉터의 실행 설정
        registry.addInterceptor(lectureServiceInterceptor)
                .addPathPatterns("/")
                .addPathPatterns("/main", "/member/login", "/member/logout", "/member/insertMember", "/member/updateMember")
                .excludePathPatterns("/**/**Fetch")
                .excludePathPatterns("/images/**", "/js/**", "/css/**");

        //메뉴 정보 출력 인터셉터 실행 설정
        registry.addInterceptor(menuInterceptor)
                .addPathPatterns("/admin/**","/instructor/adminInsertInstructorForm"
                                            ,"/instructor/adminInstructorList"
                                            ,"/instructor/adminInstructorDetail"
                                            ,"/instructor/adminUpdateInstructorInfoForm"
                                            ,"/lecture/insertLectureForm"
                                            ,"/lecture/adminInsertLectureEventForm"
                                            ,"/lecture/adminLectureEventList"
                                            ,"/lecture/adminLectureEventDetail"
                                            ,"/lecture/adminUpdateLectureEventInfoForm"
                                            ,"/lecture/adminLectureReviewList"
                                            ,"/lecture/adminLectureList"
                                            ,"/lecture/adminSelectLectureDetail"
                                            ,"/lecture/adminInsertLectureForm"
                                            ,"/lecture/adminSelectLectureList"
                                            ,"/center/insertFacilityForm"
                                            ,"/center/selectAllFacility"
                                            ,"/rental/rentalManageList"
                                                                                    )
                .excludePathPatterns("/**/**Fetch")
                .excludePathPatterns("/images/**", "/js/**", "/css/**");

        //유저 메뉴 정보 출력 인터셉터 실행
        registry.addInterceptor(userMenuInterceptor)
                .addPathPatterns("/", "/etc/privacy", "/etc/sitemap"
                                ,"/member/logout", "/member/updateMemberForm"
                                ,"/board/freeBoardList", "/board/regBoardForm", "/board/boardDetail"
                                ,"/qa/qaBoardList"
                                ,"/rental/rentalFacility", "/rental/myRentalHistory")
                .excludePathPatterns("/**/**Fetch")
                .excludePathPatterns("/images/**", "/js/**", "/css/**");
    }
}
