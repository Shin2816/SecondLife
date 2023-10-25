package com.green.SecondLife;

import com.green.SecondLife.community.vo.BoardFreeListVO;
import com.green.SecondLife.member.vo.SubMenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/")
    public String main(BoardFreeListVO boardFreeListVO, SubMenuVO subMenuVO){

        return "main";
    }
}
