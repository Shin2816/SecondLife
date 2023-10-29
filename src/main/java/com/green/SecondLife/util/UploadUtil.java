package com.green.SecondLife.util;

import com.green.SecondLife.center.vo.FacilityImageVO;
import com.green.SecondLife.community.vo.GalleryImgVO;
import com.green.SecondLife.community.vo.QaImgVO;
import com.green.SecondLife.instructor.vo.InstructorImgVO;
import com.green.SecondLife.lecture.vo.LectureEventImgVO;
import com.sun.tools.javac.Main;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

            //첨부할 파일의 확장자 추출
            int dotIndex = lectureEventOriginFileName.lastIndexOf('.');
            String extension = lectureEventOriginFileName.substring(dotIndex);
            //첨부할 파일의 첨부파일명 만들기
            String lectureEventAttachedFileName = uuid + extension;

            //파일 첨부
            //간단하게 기능만 구현하려고 하지만
            //파일 첨부 기능 자체가 예외처리를 하지않으면 구현할 수 없기때문에
            //try catch문으로 예외처리를 해주고
            //예외처리는 에러가 나는걸 막아주려고하는거지만
            //기능을 구현하기위한 목적으로만 썼기때문에 catch문으로 넘어갔을떄에는 의도적으로 에러를 내준다
            //catch문으로 넘어가서 코드가 계속 진행되기때문에
            try {
                //파일 첨부 하기
                File file = new File(ConstantVariable.UPLOAD_PATH_LECTURE_EVENT + lectureEventAttachedFileName);
                multipartImgFile.transferTo(file);
                //imgVO 에 원본파일명, 첨부파일명값 넣어주기
                lectureEventImgVO.setLectureEventOriginFileName(lectureEventOriginFileName);
                lectureEventImgVO.setLectureEventAttachedFileName(lectureEventAttachedFileName);
            } catch (IOException e){
                //예외발생시 그냥 에러 내버리기
                throw new RuntimeException(e);
            }
        }
        // 원본파일명, 첨부파일명이 들어간 VO 리턴 해주기
        return lectureEventImgVO;
    }

    //------------qa 게시판 업로드 메소드
    //파일첨부 기능(단일 파일 업로드)
    public static QaImgVO qaUploadFile(MultipartFile Img){
        QaImgVO qaImgVO = null;

        //첨부파일을 선택한다면...
        if (!Img.isEmpty()){
            qaImgVO = new QaImgVO();
            //첨부파일의 이름을 변수에 저장
            String originFileName = Img.getOriginalFilename();
            //첨부될 파일명을 랜덤한 문자열로 설정
            String uuid = UUID.randomUUID().toString();

            //확장자명 설정
            //가장 마지막위치 .의 index를 저장
            int dotIndex = originFileName.lastIndexOf(".");
            String extention = originFileName.substring(dotIndex);//substring으로 .jpg를 분리
            //랜덤한 문자열 + 확장자 asdf.jpg
            String attachedFileName = uuid + extention;

            try {
                //지정한 형태로 파일 첨부(경로 + 파일명)
                File file = new File(ConstantVariable.UPLOAD_PATH_COMMUNITY_QA + attachedFileName);
                Img.transferTo(file);

                qaImgVO.setQaOriginFileName(originFileName);
                qaImgVO.setQaAttachedFileName(attachedFileName);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //첨부파일이 만약 없다면 null을 리턴
        return qaImgVO;
    }

    //다중 파일 업로드
    public static List<QaImgVO> qaMultiFileUpload(MultipartFile[] imgs){
        List<QaImgVO> imgList = new ArrayList<>();

        for (MultipartFile img : imgs){
            QaImgVO vo = qaUploadFile(img);

            if (vo != null){
                imgList.add(vo);
            }
        }
        return imgList;
    }

    //------------Galley 게시판 업로드 메소드
    //파일첨부 기능(단일 파일 업로드)
    public static GalleryImgVO galUploadFile(MultipartFile Img){
        GalleryImgVO galleryImgVO = null;

        //첨부파일을 선택한다면...
        if (!Img.isEmpty()){
            galleryImgVO = new GalleryImgVO();
            //첨부파일의 이름을 변수에 저장
            String originFileName = Img.getOriginalFilename();
            //첨부될 파일명을 랜덤한 문자열로 설정
            String uuid = UUID.randomUUID().toString();

            //확장자명 설정
            //가장 마지막위치 .의 index를 저장
            int dotIndex = originFileName.lastIndexOf(".");
            String extention = originFileName.substring(dotIndex);//substring으로 .jpg를 분리
            //랜덤한 문자열 + 확장자 asdf.jpg
            String attachedFileName = uuid + extention;

            try {
                //지정한 형태로 파일 첨부(경로 + 파일명)
                File file = new File(ConstantVariable.UPLOAD_PATH_COMMUNITY_GALLERY + attachedFileName);
                Img.transferTo(file);

                galleryImgVO.setGalOriginFileName(originFileName);
                galleryImgVO.setGalAttachedFileName(attachedFileName);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //첨부파일이 만약 없다면 null을 리턴
        return galleryImgVO;
    }

    //다중 파일 업로드
    public static List<GalleryImgVO> galMultiFileUpload(MultipartFile[] imgs){
        List<GalleryImgVO> imgList = new ArrayList<>();

        for (MultipartFile img : imgs){
            GalleryImgVO vo = galUploadFile(img);

            if (vo != null){
                imgList.add(vo);
            }
        }
        return imgList;
    }
}
