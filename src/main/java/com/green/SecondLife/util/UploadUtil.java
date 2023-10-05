package com.green.SecondLife.util;

import com.green.SecondLife.center.vo.FacilityImageVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUtil {
    // 파일 첨부 메소드 - center
    public static FacilityImageVO uploadFile(MultipartFile img){
        FacilityImageVO facilityImageVO = null;

        // 첨부파일을 선택했다면(비어있지않다면. 파일을 첨부했다면)
        if(!img.isEmpty()){
            facilityImageVO = new FacilityImageVO();

            // 이미지(첨부파일) 등록 시 매개변수로 MultipartFile 인터페이스 사용해줘야함
            // 첨부파일
            String facilityOriginFileName = img.getOriginalFilename();  //원본 파일명

            // 첨부될 파일명
            // 첨부파일은 원본파일이 또한번 저장 될 때 덮어써짐. => 파일명을 바꿔주면 저장됨.
            // 랜덤한 값으로 파일이름으로 만들어주기
            String uuid = UUID.randomUUID().toString();

            // 확장자명. 가장 빨리 만나는 자바.jpg
            // originFileName에서 "."이 가장 마지막에 위치하는 index값
            int dotIndex = facilityOriginFileName.lastIndexOf(".");
            String extension = facilityOriginFileName.substring(dotIndex);
            String facilityAttachedFileName = uuid + extension;

            // 파일 첨부
            // mainImg을 실제 파일이름으로 변환시켜줌.
            try {
                File file = new File(ConstantVariable.UPLOAD_PATH_CENTER + facilityAttachedFileName);
                img.transferTo(file);

                facilityImageVO.setFacilityOriginFileName(facilityOriginFileName);
                facilityImageVO.setFacilityAttachedFileName(facilityAttachedFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return facilityImageVO;

    }
}
