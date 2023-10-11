package com.green.SecondLife.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final BoardInterceptor boardInterceptor;
    private final LectureServiceInterceptor lectureServiceInterceptor;

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
    }
}
