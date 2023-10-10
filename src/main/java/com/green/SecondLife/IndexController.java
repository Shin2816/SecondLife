package com.green.SecondLife;

import com.green.SecondLife.community.service.CommunityService;
import com.green.SecondLife.community.vo.BoardFreeListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final CommunityService communityService;

    @GetMapping("/")
    public String main(Model model, BoardFreeListVO boardFreeListVO){
        List<BoardFreeListVO> freeList = communityService.selectFreeBoardList(boardFreeListVO);
        model.addAttribute("freeBoardList", freeList);

        return "main";
    }
}
