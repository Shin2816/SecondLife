package com.green.SecondLife.util;

import com.green.SecondLife.center.vo.FacilityImageVO;
import com.green.SecondLife.instructor.vo.InstructorImgVO;
import com.green.SecondLife.lecture.vo.LectureEventImgVO;
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

    // 파일 첨부 메소드 - instructor
    public static InstructorImgVO uploadInstructorFile(MultipartFile img){
        InstructorImgVO instructorImgVO = null;

        // 첨부파일을 선택했다면(비어있지않다면. 파일을 첨부했다면)
        if(!img.isEmpty()){
            instructorImgVO = new InstructorImgVO();

            // 이미지(첨부파일) 등록 시 매개변수로 MultipartFile 인터페이스 사용해줘야함
            // 첨부파일
            String instructorOriginFileName = img.getOriginalFilename();  //원본 파일명

            // 첨부될 파일명
            // 첨부파일은 원본파일이 또한번 저장 될 때 덮어써짐. => 파일명을 바꿔주면 저장됨.
            // 랜덤한 값으로 파일이름으로 만들어주기
            String uuid = UUID.randomUUID().toString();

            // 확장자명. 가장 빨리 만나는 자바.jpg
            // originFileName에서 "."이 가장 마지막에 위치하는 index값
            int dotIndex = instructorOriginFileName.lastIndexOf(".");
            String extension = instructorOriginFileName.substring(dotIndex);
            String instructorAttachedFileName = uuid + extension;

            // 파일 첨부
            // mainImg을 실제 파일이름으로 변환시켜줌.
            try {
                File file = new File(ConstantVariable.UPLOAD_PATH_INSTRUCTOR + instructorAttachedFileName);
                img.transferTo(file);

                instructorImgVO.setInstructorOriginFileName(instructorOriginFileName);
                instructorImgVO.setInstructorAttachedFileName(instructorAttachedFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return instructorImgVO;

    }
    // 파일 첨부 메소드 - 강좌 종목 이미지
    // 첨부파일 등록시 매개변수로 MultipartFile 인터페이스에 대한 객체 를 줘야함
    public static LectureEventImgVO uploadLectureEventImgFile(MultipartFile multipartImgFile){
        LectureEventImgVO lectureEventImgVO = null;

        if(!multipartImgFile.isEmpty()){

            //등록할 이미지테이블에 해당하는 VO클래스에 대한 객체 생성
            lectureEventImgVO = new LectureEventImgVO();

            //첨부파일 MultipartFile이 갖고 있는 getOriginalFileName(); 메소드 사용
            String lectureEventOriginFileName = multipartImgFile.getOriginalFilename(); //원본파일명
            //첨부될 파일명
            //첨부파일은 원본파일이 또 한번 저장될 때 덮어써짐 => 파일명을 바꿔주면 저장됨
            //랜덤한 파일이름으로 만들어주기
            String uuid = UUID.randomUUID().toString();
        }
        return null;
    }



}
