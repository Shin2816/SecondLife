package com.green.SecondLife.community.controller;

import com.green.SecondLife.community.service.GalleryService;
import com.green.SecondLife.community.vo.*;
import com.green.SecondLife.member.vo.SubMenuVO;
import com.green.SecondLife.util.ConstantVariable;
import com.green.SecondLife.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/gallery")
public class GalleryController {
    private final GalleryService galleryService;

    @RequestMapping("/galleryBoardList")
    public String selectGalBoardList(Model model, BoardGalleryListVO boardGalleryListVO, SubMenuVO subMenuVO){
        //페이지 정보 세팅
        int totalDataCnt = galleryService.selectBoardCnt(boardGalleryListVO); //전체 게시글 갯수 조회해서
        boardGalleryListVO.setTotalPageCnt(totalDataCnt);//세터 호출해서 전체 게시글 갯수 전달
        boardGalleryListVO.setPageInfo();//변수값 설정한 메소드 호출(상속관계라 사용가능)

        //게시글 목록 조회
        model.addAttribute("galleryBoardList", galleryService.selectGalBoardList(boardGalleryListVO));

        return "board/gallery/gallery_board";
    }
    //등록버튼 누르면 등록 페이지로 이동
    @GetMapping("/regGalBoardForm")
    public String regGalBoardForm(SubMenuVO subMenuVO){
        return "board/gallery/reg_board";
    }
    //글 등록 페이지에서 등록하기 누르면 글 등록 쿼리 실행
    @PostMapping("/regBoard")
    public String regBoard(BoardGalleryListVO boardGalleryListVO, Authentication authentication, MultipartFile[] galImgSub, MultipartFile galImgMain){
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
        boardGalleryListVO.setGalBoardWriter(authentication.getName());//vo안에 writer를 세션에 저장된 id로 갖고옴
        boardGalleryListVO.setGalBoardNum(nextBoardNum);//다음 들어갈 글 번호 조회된 것을 빈값으로 채움
        boardGalleryListVO.setGalImgList(galImgList); //변수안에 파일 업로드 기능의 메소드 넣기
        
        //글 등록 쿼리 실행
        galleryService.insertGalBoard(boardGalleryListVO);

        return "redirect:/gallery/galleryBoardList?menuCode="+ ConstantVariable.MENU_CODE_BOARD;
    }
    //글 tr태그를 클릭했을때 해당글의 상세페이지 이동
    @RequestMapping("/boardDetail")
    public String boardDetail(Model model, SubMenuVO subMenuVO, BoardCommentListVO boardCommentListVO){
        //board이름으로 디테일정보 던지기
        model.addAttribute("board", galleryService.selectGalBoardDetail(boardCommentListVO.getCommentNum()));//아우터조인

        //조회수 증가
        galleryService.updateGalBoardCnt(boardCommentListVO.getCommentNum());

        //댓글 조회해서 html로 던지기
        model.addAttribute("comment", galleryService.selectGalBoardComment(boardCommentListVO.getCommentNum()));
        return "board/gallery/board_detail";
    }
    //글 상세페이지에서 삭제버튼 클릭하였을 때
    @GetMapping("/deleteGalBoard")
    public String deleteGalBoard(int galBoardNum){
        galleryService.deleteGalBoard(galBoardNum);
        return "redirect:/gallery/galleryBoardList?menuCode="+ConstantVariable.MENU_CODE_BOARD;
    }
    //수정 모달에서 수정 버튼을 눌렀을 때 수정 쿼리 실행
    @RequestMapping("/updateGalBoard")
    public String updateGalBoard(BoardGalleryListVO boardGalleryListVO){
        //수정 쿼리
        galleryService.updateGalBoard(boardGalleryListVO);
        //수정이 완료되면 해당 게시글 상세페이지로 BoardNum=숫자 데이터를 던질 수 있다.
        return "redirect:/gallery/boardDetail?galBoardNum=" + boardGalleryListVO.getGalBoardNum()+"&menuCode="+ConstantVariable.MENU_CODE_BOARD;
    }
    //상세 페이지에서 댓글 작성버튼 클릭하면 비동기로 insert 쿼리 실행
    @PostMapping("/galBoardComment")
    public String galBoardComment(BoardCommentListVO boardCommentListVO, Authentication authentication){

        galleryService.insertGalBoardComment(boardCommentListVO);
        return "redirect:/gallery/boardDetail?menuCode="+ConstantVariable.MENU_CODE_BOARD+ "&commentNum=" + boardCommentListVO.getCommentNum(); //디테일 컨트롤러로 다시 이동할 때 값 가져가기
    }
    //상세 페이지에서 댓글 삭제버튼 클릭하면 delete 쿼리 실행
    @PostMapping("/galDeleteComment")
    public String galBoardComment(BoardCommentListVO boardCommentListVO, RedirectAttributes redirectAttributes){
        galleryService.deleteGalBoardComment(boardCommentListVO.getCommentId());
        return "redirect:/gallery/boardDetail?menuCode="+ConstantVariable.MENU_CODE_BOARD + "&commentNum=" + boardCommentListVO.getCommentNum();
    }

    //상세 페이지에서 댓글 수정 버튼 클릭하면 update 쿼리 실행
    @PostMapping("/galUpdateComment")
    public String galUpdateComment(BoardCommentListVO boardCommentListVO, RedirectAttributes redirectAttributes){
        galleryService.updateGalBoardComment(boardCommentListVO);
        return "redirect:/gallery/boardDetail?menuCode="+ConstantVariable.MENU_CODE_BOARD + "&commentNum=" + boardCommentListVO.getCommentNum();
    }
}
