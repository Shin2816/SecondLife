//반려 버튼 클릭시 실행(모달창)되는 함수
// function rejectModal(){
//     const myModal = new bootstrap.Modal('#rejectModal');
//     myModal.show();
// }

//승인 버튼 클릭시 실행되는 함수
function acceptBtn(signCode){
    if(confirm('해당 신청건을 승인하시겠습니까?')){
        location.href= '/rental/updateStatePay?rentalSignCode='+signCode;
        alert('승인이 완료되었습니다.');
    }
}

//모달창-반려하기 클릭시 데이터 유효성 검사(벨리데이트)
function rejectValidate(signCode, idx){
    // 오류메세지 초기화
    resetMessage();

    // 1. 데이터 유효성 검사
    const InputRejectReason = document.querySelector('input[name=rejectReason]');

    //반려 내용 필수 입력/ 15자 이하로 작성
    if(InputRejectReason.value.length == 0){
        inputInvalidate('.reject-error-div', '반려 내용을 입력해주세요.');
        return;
    } 
    else if(InputRejectReason.value.length > 15){
        inputInvalidate('.reject-error-div', '15자 이하로 작성해주세요.');
        return;
    }

    // 2. 데이터 가져가기 
    if(confirm('해당 신청건을 반려하시겠습니까?')){
        location.href= '/rental/updateStateReject?rentalSignCode='+signCode+'&rejectReason='+ InputRejectReason.value;
        alert('반려가 완료되었습니다.');
    }
}

// 오류메세지 초기화
function resetMessage(){
    document.querySelector('.reject-error-div').style.display = 'none';
}

// validate 실패 시 메세지 설정
function inputInvalidate(tagId, message){
    document.querySelector(tagId).style.display = 'block';
    document.querySelector(tagId).textContent = message;
}