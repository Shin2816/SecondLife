package com.green.SecondLife.member.service;

import com.green.SecondLife.member.vo.MenuVO;
import com.green.SecondLife.member.vo.SubMenuVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{

    private final SqlSessionTemplate sqlSession;

    @Override
    public List<MenuVO> selectMenuList() {
        return sqlSession.selectList("menuMapper.selectMenuList");
    }

    @Override
    public List<SubMenuVO> selectSubMenuList(String menuCode) {
        return sqlSession.selectList("menuMapper.selectSubMenuList", menuCode);
    }

    @Override
    public List<MenuVO> selectMainMenuList() {
        return sqlSession.selectList("menuMapper.selectMainMenuList");
    }

    @Override
    public List<SubMenuVO> selectMainSubMenuList() {
        return sqlSession.selectList("menuMapper.selectMainSubMenuList");
    }

    @Override
    public List<MenuVO> selectMainMenuAllList() {
        return sqlSession.selectList("menuMapper.selectMainMenuAllList");
    }

    @Override
    public List<SubMenuVO> selectMainSubMenuAllList() {
        return sqlSession.selectList("menuMapper.selectMainSubMenuAllList");
    }


}
