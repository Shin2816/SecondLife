//반려 버튼 클릭시 실행되는 함수
function rejectBtn(signCode){
    if(confirm('해당 신청건을 반려하시겠습니까?')){
        const reasonInput = document.querySelector('input[name=rejectReason]');
        location.href= '/rental/updateStateReject?rentalSignCode='+signCode+'&rejectReason='+reasonInput.value;
        alert('반려가 완료되었습니다.');
    }
}

//승인 버튼 클릭시 실행되는 함수
function acceptBtn(signCode){
    if(confirm('해당 신청건을 승인하시겠습니까?')){
        location.href= '/rental/updateStatePay?rentalSignCode='+signCode;
        alert('승인이 완료되었습니다.');
    }
}