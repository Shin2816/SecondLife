//반려 버튼 클릭시 실행되는 함수
function rejectBtn(signCode){
    if(confirm('해당 신청건을 반려하시겠습니까?')){
        const reasonInput = document.querySelector('input[name=rejectReason]');
        location.href= '/rental/updateReject?rentalSignCode='+signCode+'&rejectReason='+reasonInput.value;
        alert('반려가 완료되었습니다.');
    }
}

//승인 버튼 클릭시 실행되는 함수
function acceptBtn(){

}