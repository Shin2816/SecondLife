package com.green.SecondLife.interceptor;

import com.green.SecondLife.lecture.service.LectureService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequiredArgsConstructor
public class LectureInterceptor implements HandlerInterceptor {
    private final LectureService lectureService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        modelAndView.addObject("lectureList", lectureService.selectMainLectureList());

    }
}
