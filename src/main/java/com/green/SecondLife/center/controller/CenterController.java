package com.green.SecondLife.center.controller;

import com.green.SecondLife.center.service.CenterService;
import com.green.SecondLife.center.vo.CenterFacilityVO;
import com.green.SecondLife.center.vo.CenterPlaceCategoryVO;
import com.green.SecondLife.center.vo.FacilityImageVO;
import com.green.SecondLife.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/center")
@RequiredArgsConstructor
public class CenterController {
    private final CenterService centerService;

    // 시설 등록 페이지로 이동
    @GetMapping("/insertFacilityForm")
    public String insertFacilityForm(Model model){
        // 시설 카테고리 조회
        model.addAttribute("centerCategoryList", centerService.selectCenterCategory());

        return "admin/insert_facility_form";
    }

    // 시설 등록 + 시설 이미지 등록
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

    // 전체 시설 목록 조회
    @GetMapping("/selectAllFacility")
    public String selectAllFacility(Model model){
        model.addAttribute("facilityList", centerService.selectAllFacility());
        return "admin/manage_facility";
    }

    // 시설관리 - 대관가능유무 상태 변경
    @ResponseBody
    @PostMapping("/updateRentalAvailable")
    public void updateRentalAvailable(CenterFacilityVO centerFacilityVO){
        centerService.updateRentalAvailable(centerFacilityVO);
    }

    // 시설관리 - 수정하기 페이지로 이동
    @GetMapping("/updateFacilityForm")
    public String updateFacilityForm(String facilityCode){
        return "admin/update_facility_form";
    }

    // 시설관리 - 삭제하기
    @GetMapping("/deleteFacility")
    public String deleteFacility(String facilityCode){
        centerService.deleteFacility(facilityCode);
        return "redirect:/center/selectAllFacility";
    }
}

