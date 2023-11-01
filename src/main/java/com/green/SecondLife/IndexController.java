package com.green.SecondLife;

import com.green.SecondLife.community.service.QaService;
import com.green.SecondLife.community.vo.BoardCommentListVO;
import com.green.SecondLife.community.vo.BoardFreeListVO;
import com.green.SecondLife.community.vo.BoardQaListVO;
import com.green.SecondLife.member.vo.SubMenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class IndexController {
    private final QaService qaService;

    @GetMapping("/")
    public String main(BoardFreeListVO boardFreeListVO, BoardCommentListVO boardCommentListVO, Model model, SubMenuVO subMenuVO, BoardQaListVO boardQaListVO, Authentication authentication){

        model.addAttribute("authentication", authentication);

        //게시글 목록 조회
        List<BoardQaListVO> qaBoardList = qaService.selectQaBoardList(boardQaListVO);

//        BoardQaListVO board = qaService.selectQaBoardDetail(boardCommentListVO.getCommentNum());//해당글 상세정보 조회하고
//        board.setQaBoardPassword(qaService.selectQaPw(boardCommentListVO.getCommentNum()));//세터로 데이터베이스의 비밀번호를 저장

        model.addAttribute("qaBoardList", qaBoardList);

        return "main";
    }
}
