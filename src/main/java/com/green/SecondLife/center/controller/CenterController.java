package com.green.SecondLife.center.controller;

import com.green.SecondLife.center.service.CenterService;
import com.green.SecondLife.center.vo.CenterFacilityVO;
import com.green.SecondLife.center.vo.FacilityImageVO;
import com.green.SecondLife.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/center")
@RequiredArgsConstructor
public class CenterController {
    private final CenterService centerService;

    // 시설 등록 페이지로 이동
    @GetMapping("/insertFacilityForm")
    public String insertFacilityForm(){
        return "admin/insert_facility_form";
    }

    // 시설 등록 + 시설 이미지 등록
    @PostMapping("/insertFacility")
    public String insertFacility(CenterFacilityVO centerFacilityVO, MultipartFile facilityImg){
        //시설 이미지 등록
        //이미지 정보 하나가 들어갈 수 있는 통
        FacilityImageVO facilityImgVO = UploadUtil.uploadFile(facilityImg);

        centerFacilityVO.setFacilityImageVO(facilityImgVO);
        System.out.println(centerFacilityVO);
        // 시설 등록 + 시설 이미지 등록 쿼리 (트랜젝션)
        centerService.insertFacility(centerFacilityVO);

        return "redirect:/center/insertFacilityForm";
    }

    // 시설 조회


}

