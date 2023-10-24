package com.green.SecondLife.interceptor;

import com.green.SecondLife.member.service.MenuService;
import com.green.SecondLife.member.vo.MenuVO;
import com.green.SecondLife.member.vo.SubMenuVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMenuInterceptor implements HandlerInterceptor {

    private final MenuService menuService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        List<MenuVO> menuList = menuService.selectMainMenuList();
        List<SubMenuVO> subMenuList = menuService.selectMainSubMenuList();

        modelAndView.addObject("mainMenuList", menuList);
        modelAndView.addObject("mainSubMenuList", subMenuList);
    }
}
