// 시설 등록 시 데이터 유효성 검사(벨리데이트)
function insertFacilityValidate(){
    // 1. 데이터 유효성 검사
    // 카테고리 선택 여부 체크

    // form태그 선택
    // form태그 안의 요소는 name속성으로 접근 가능
    const insertFacilityForm = document.querySelector('#insertFacilityForm');

    // form 태그 안 name속성이 memberId인 태그의 value
    if(insertFacilityForm.centerCateCode.value == ''){   
        inputInvalidate('#id-error-div', '시설 카테고리는 필수로 선택해주셔야 합니다.');
        return;  // 함수 실행 종료
    }

    // 시설명은 __자 이하로 작성
    if(insertFacilityForm.facilityName.value.length < 10){
        inputInvalidate('#id-error-div', '10자 이하로 작성해주셔야 합니다.');
    }

    // 2. 데이터 가져가기 - submit 실행
    // form태그 선택 -> submit()함수 실행
    document.querySelector('#insertFacilityForm').submit();
}