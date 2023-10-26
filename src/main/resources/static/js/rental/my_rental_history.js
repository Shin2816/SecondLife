function cancelRental(rentalCode){
    if(confirm('대관신청을 취소하시겠습니까?')){
        location.href= '/rental/deleteSignRental?rentalSignCode=' + rentalCode;
        alert('취소가 완료되었습니다.');
    }
}