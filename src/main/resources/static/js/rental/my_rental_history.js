// 대관 취소 버튼 클릭시 실행 함수
function cancelRental(rentalCode){
    if(confirm('대관신청을 취소하시겠습니까?')){
        location.href= '/rental/deleteSignRental?rentalSignCode=' + rentalCode;
        alert('취소가 완료되었습니다.');
    }
}

//반려내용 버튼 클릭시 실행 함수
function rejectReasonBtn(){
    const myModal = new bootstrap.Modal('#rejectReasonModal');
    myModal.show();
}
