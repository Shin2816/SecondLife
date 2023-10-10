package com.green.SecondLife;

import com.green.SecondLife.community.service.CommunityService;
import com.green.SecondLife.community.vo.BoardFreeListVO;
import com.green.SecondLife.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final CommunityService communityService;
    private final LectureService lectureService;

    @GetMapping("/")
    public String main(Model model, BoardFreeListVO boardFreeListVO){
        List<BoardFreeListVO> freeList = communityService.selectFreeBoardList(boardFreeListVO);
        model.addAttribute("lectureList", lectureService.selectLectureList());
        model.addAttribute("freeBoardList", freeList);

        return "main";
    }
}
