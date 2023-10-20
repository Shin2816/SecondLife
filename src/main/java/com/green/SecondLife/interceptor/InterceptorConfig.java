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
                //페이지 메뉴 인터셉터
                .addPathPatterns("/admin/**")
                //강좌 메뉴 인터셉터
                .addPathPatterns("/lecture/insertLectureForm", "/lecture/adminCreateLectureEventForm")
                //강사 메뉴 인터셉터
                .addPathPatterns("/instructor/insertInstructorForm","/instructor/adminInstructorList")
                //시설, 대관 메뉴 인터셉터
                .addPathPatterns("/center/insertFacilityForm","/center/selectAllFacility")
                //추 후 게시판 메뉴 인터셉터 추가 요망
                .excludePathPatterns("/**/**Fetch")
                .excludePathPatterns("/images/**", "/js/**", "/css/**");
    }
}
