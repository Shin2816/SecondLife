package com.green.SecondLife.member.service;


import com.green.SecondLife.member.vo.MenuVO;
import com.green.SecondLife.member.vo.SubMenuVO;

import java.util.List;

public interface MenuService {
    //메뉴 목록 조회
    public List<MenuVO> selectMenuList();
    //서브 메뉴 목록 조회
    public List<SubMenuVO> selectSubMenuList(String menuCode);
}
