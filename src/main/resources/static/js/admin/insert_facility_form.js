// 시설 등록 시 데이터 유효성 검사(벨리데이트)
function insertFacilityValidate(facilityCode){
    // 오류메세지 초기화
    resetMessage();

    // 1. 데이터 유효성 검사
    const insertFacilityForm = document.querySelector('#insertFacilityForm');

    // 카테고리 선택 여부 체크
    if(insertFacilityForm.centerCateCode.value == '카테고리를 선택해주세요.'){   
        inputInvalidate('#category-error-div', '카테고리는 필수로 선택해주셔야 합니다.');
        return;  // 함수 실행 종료
    }

    // 시설명은 10자 이하로 작성
    if(insertFacilityForm.facilityName.value.length == 0){
        inputInvalidate('#name-error-div', '시설명을 입력해주세요.');
        return;
    } 
    else if(insertFacilityForm.facilityName.value.length > 10){
        inputInvalidate('#name-error-div', '10자 이하로 작성해주세요.');
        return;
    }

    // 첨부파일 필수
    if(insertFacilityForm.facilityImg.value == ''){
        inputInvalidate('#file-error-div', '이미지 파일을 첨부해주세요.');
        return;
    }

    // 2. 데이터 가져가기 - submit 실행
    // form태그 선택 -> submit()함수 실행
    insertFacilityForm.submit(facilityCode);
}

// 오류메세지 초기화
function resetMessage(){
    document.querySelector('#category-error-div').style.display = 'none';
    document.querySelector('#name-error-div').style.display = 'none';
    document.querySelector('#file-error-div').style.display = 'none';
}

// validate 실패 시 메세지 설정
function inputInvalidate(tagId, message){
    document.querySelector(tagId).style.display = 'block';
    document.querySelector(tagId).textContent = message;
}
