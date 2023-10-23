package com.green.SecondLife.interceptor;

import com.green.SecondLife.community.service.CommunityService;
import com.green.SecondLife.community.service.QaService;
import com.green.SecondLife.community.vo.BoardFreeListVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BoardInterceptor implements HandlerInterceptor {
    private final CommunityService communityService;
    private final QaService qaService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null){
            modelAndView.addObject("freeBoardList", communityService.selectMainFreeBoardList());
            modelAndView.addObject("qaBoardList", qaService.selectMainQaBoardList());

        }

    }
}
