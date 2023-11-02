package com.green.SecondLife.center.controller;

import com.green.SecondLife.center.service.CenterService;
import com.green.SecondLife.center.vo.CenterFacilityVO;
import com.green.SecondLife.center.vo.CenterPlaceCategoryVO;
import com.green.SecondLife.center.vo.FacilityImageVO;
import com.green.SecondLife.member.vo.SubMenuVO;
import com.green.SecondLife.util.ConstantVariable;
import com.green.SecondLife.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/center")
@RequiredArgsConstructor
public class CenterController {
    private final CenterService centerService;

    //관리자-시설 등록 페이지로 이동
    @GetMapping("/insertFacilityForm")
    public String insertFacilityForm(Model model, SubMenuVO subMenuVO){
        subMenuVO.setMenuCode("MENU_003");
        // 시설 카테고리 조회
        model.addAttribute("centerCategoryList", centerService.selectCenterCategory());

        return "admin/insert_facility_form";
    }

    //관리자-시설 등록 + 시설 이미지 등록
    @PostMapping("/insertFacility")
    public String insertFacility(CenterFacilityVO centerFacilityVO, MultipartFile facilityImg){
        // 시설 이미지 등록
        //1.다음에 들어가야할 FACILITY_CODE 조회
        String facilityCode = centerService.selectNextFacilityCode();

        //2.이미지 정보 하나가 들어갈 수 있는 통
        FacilityImageVO facilityImgVO = UploadUtil.uploadFile(facilityImg);

        centerFacilityVO.setFacilityImageVO(facilityImgVO);

        // 시설 등록 + 시설 이미지 등록 쿼리 (트랜젝션)
        centerFacilityVO.setFacilityCode(facilityCode);
        centerService.insertFacility(centerFacilityVO);

        return "redirect:/center/insertFacilityForm";
    }

    //관리자-전체 시설 목록 조회
    @GetMapping("/selectAllFacility")
    public String selectAllFacility(CenterFacilityVO centerFacilityVO, Model model, SubMenuVO subMenuVO){
        subMenuVO.setMenuCode("MENU_003");
        // 페이지 정보 세팅
        int totalDataCnt = centerService.selectFacilityListCnt();
        centerFacilityVO.setTotalDataCnt(totalDataCnt);
        centerFacilityVO.setPageInfo();

        // 시설 목록 조회
        model.addAttribute("facilityList", centerService.selectAllFacility(centerFacilityVO));
        return "admin/manage_facility";
    }

    //관리자-시설목록 - 대관가능유무 상태 변경

    @PostMapping("/updateRentalAvailable")
    public String updateRentalAvailable(CenterFacilityVO centerFacilityVO){
        centerService.updateRentalAvailable(centerFacilityVO);
        return "redirect:/center/selectAllFacility";
    }

    //관리자-시설목록 - 수정하기
    @PostMapping("/updateFacility")
    public String updateFacility(CenterFacilityVO centerFacilityVO, MultipartFile facilityImg){

        //이미지 정보 하나가 들어갈 수 있는 통
        FacilityImageVO facilityImgVO = UploadUtil.uploadFile(facilityImg);

        if(facilityImgVO != null){
            // 해당 게시물의 첨부파일 삭제
            String fileName = centerService.selectCenterImgFileName(centerFacilityVO.getFacilityCode());
            File file = new File(ConstantVariable.UPLOAD_PATH_CENTER + fileName);
            file.delete();

            //새로운 첨부파일 등록
            centerFacilityVO.setFacilityImageVO(facilityImgVO);
            centerService.insertFacilityImage(centerFacilityVO);
        }

        // 수정하기
        centerService.updateFacility(centerFacilityVO);
        return "redirect:/center/selectAllFacility";
    }

    //관리자-시설목록 - 삭제하기
    @GetMapping("/deleteFacility")
    public String deleteFacility(String facilityCode){
        // 해당 게시물의 첨부파일 삭제
        String fileName = centerService.selectCenterImgFileName(facilityCode);
        File file = new File(ConstantVariable.UPLOAD_PATH_CENTER + fileName);
        file.delete();

        // 게시글 삭제
        centerService.deleteFacility(facilityCode);

        return "redirect:/center/selectAllFacility";
    }

    //사용자-오시는 길 페이지
    @GetMapping("/centerLocation")
    public String centerLocation(SubMenuVO subMenuVO){
        return "/center/center_location";
    }

    //사용자-센터 소개 페이지
    @GetMapping("/centerGuide")
    public String centerGuide(Model model, SubMenuVO subMenuVO){
        //카테고리
        model.addAttribute("categoryList", centerService.selectCenterCategory());

        return "/center/center_facility_guide";
    }

    //사용자-센터 소개 페이지(탭클릭 비동기)
    @ResponseBody
    @PostMapping("/centerGuideFetch")
    public List<CenterFacilityVO> centerGuideFetch(int centerCateCode){
        List<CenterFacilityVO> facilityList = centerService.selectFacilityInfo(centerCateCode);
        return facilityList;
    }
}

