package com.green.SecondLife.community.controller;

import com.green.SecondLife.community.service.GalleryService;
import com.green.SecondLife.community.vo.BoardGalleryListVO;
import com.green.SecondLife.community.vo.BoardQaListVO;
import com.green.SecondLife.community.vo.GalleryImgVO;
import com.green.SecondLife.community.vo.QaImgVO;
import com.green.SecondLife.member.vo.MemberVO;
import com.green.SecondLife.util.UploadUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/gallery")
public class GalleryController {
    private final GalleryService galleryService;

    @RequestMapping("/galleryBoardList")
    public String selectGalBoardList(Model model, BoardGalleryListVO boardGalleryListVO){
        //페이지 정보 세팅
        int totalDataCnt = galleryService.selectBoardCnt(); //전체 게시글 갯수 조회해서
        boardGalleryListVO.setTotalPageCnt(totalDataCnt);//세터 호출해서 전체 게시글 갯수 전달
        boardGalleryListVO.setPageInfo();//변수값 설정한 메소드 호출(상속관계라 사용가능)

        //게시글 목록 조회
        model.addAttribute("galleryBoardList", galleryService.selectGalBoardList(boardGalleryListVO));

        //테스트
        List<BoardGalleryListVO> test = galleryService.selectGalBoardList(boardGalleryListVO);
        System.out.println(test);
        return "board/gallery/gallery_board";
    }
    //등록버튼 누르면 등록 페이지로 이동
    @GetMapping("/regGalBoardForm")
    public String regQaBoardForm(){
        return "board/gallery/reg_board";
    }
    //글 등록 페이지에서 등록하기 누르면 글 등록 쿼리 실행
    @PostMapping("/regBoard")
    public String regBoard(BoardGalleryListVO boardGalleryListVO, HttpSession session, MultipartFile[] galImgSub, MultipartFile galImgMain){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        int nextBoardNum = galleryService.selectNextGalBoardNum();//빈 값 채울 boardNum 조회해서 저장

        //파일 업로드 메소드를 변수에 저장(단일 파일)
        GalleryImgVO galImg = UploadUtil.galUploadFile(galImgMain);
        //파일 업로드 메소드를 변수에 저장(다중 파일)
        List<GalleryImgVO> galImgList = UploadUtil.galMultiFileUpload(galImgSub);
        //리스트 안에 단일 파일 기능도 추가
        galImgList.add(galImg);
        //하나씩 돌면서 vo boardNum안에 다음 boardNum 전달
        for(GalleryImgVO e : galImgList){
            e.setGalBoardNum(nextBoardNum);
        }
        boardGalleryListVO.setGalBoardWriter(loginInfo.getMemberId());//vo안에 writer를 세션에 저장된 id로 갖고옴
        boardGalleryListVO.setGalBoardNum(nextBoardNum);//다음 들어갈 글 번호 조회된 것을 빈값으로 채움
        boardGalleryListVO.setGalImgList(galImgList); //변수안에 파일 업로드 기능의 메소드 넣기
        
        //글 등록 쿼리 실행
        galleryService.insertGalBoard(boardGalleryListVO);

        return "redirect:/gallery/galleryBoardList";
    }
    //글 tr태그를 클릭했을때 해당글의 상세페이지 이동
    @RequestMapping("/boardDetail")
    public String boardDetail(int qaBoardNum, String qaCheckPwInput, Model model, BoardQaListVO boardQaListVO, HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

//        //board이름으로 디테일정보 던지기
//        model.addAttribute("board", galleryService.selectQaBoardDetail(qaBoardNum));//아우터조인
//
//        //조회수 증가
//        galleryService.updateQaBoardCnt(qaBoardNum);
//
//        //댓글 조회해서 html로 던지기
//        model.addAttribute("comment", galleryService.selectQaBoardComment(qaBoardNum));
        return "board/qa/board_detail";

    }
}
