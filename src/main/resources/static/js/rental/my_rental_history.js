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

function kakaopay(facilityName, rentalCharge, rentalUser){
	
	var IMP = window.IMP;
	IMP.init('imp22886415');
	IMP.request_pay({		
		pg : 'kakaopay.TC0ONETIME',
		pay_method : 'card',
		merchant_uid : 'rental_' + new Date().getTime(),   //주문번호
		name : facilityName,                                  
		amount : rentalCharge, 
		buyer_name : rentalUser                          
	},function(data){
		if(data.success){
			var msg = "결제 완료";
            msg += '// 결제 금액 : ' + data.paid_amount;
            msg += '// 카드 승인번호 : ' + data.apply_num;
            
            $.ajax({
            	type : 'post',
            	url : '/paySuccess',
            	data : {"ID" : data.buyer_name, "amount" : data.paid_amount},
            });
        }else{
        	var msg = "결제 실패"
        	msg += "에러 내용" + rsp.error_msg;
        }
		alert(msg);
		document.location.href="/rental/myRentalHistory";
	});
}