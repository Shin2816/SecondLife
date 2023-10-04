package com.green.SecondLife.community.service;

import com.green.SecondLife.community.vo.BoardFreeListVO;

import java.util.List;

public interface CommunityService {
    public List<BoardFreeListVO> selectFreeBoardList(BoardFreeListVO boardFreeListVO);
}
