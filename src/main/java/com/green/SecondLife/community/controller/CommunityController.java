package com.green.SecondLife.community.controller;

import com.green.SecondLife.community.service.CommunityService;
import com.green.SecondLife.community.vo.BoardFreeListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ListableBeanFactoryExtensionsKt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import java.util.*;

@Controller
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    //게시판 출력
    @GetMapping("/freeBoardList")
    public String freeBoardList(Model model){

        return "";
    }

}
