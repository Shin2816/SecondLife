// 대관 취소 버튼 클릭시 실행 함수
function cancelRental(rentalCode){
    if(confirm('대관신청을 취소하시겠습니까?')){
        location.href= '/rental/deleteSignRental?rentalSignCode=' + rentalCode;
        alert('취소가 완료되었습니다.');
    }
}

//반려내용 버튼 클릭시 실행 함수
function rejectReasonBtn(idx){
    const myModal = new bootstrap.Modal(`#rejectReasonModal${idx}`);
    myModal.show();
}

//결제 api 연동
function kakaopay(facilityName, rentalCharge, rentalUser, menuCode){
	
	let memberTel = document.querySelector('#memberTel').value;
	let memberAddr = document.querySelector('#memberAddr').value;
	let addrDetail = document.querySelector('#addrDetail').value;
	let rentalSignCode = document.querySelector('#rentalSignCode').value;

	let IMP = window.IMP;
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
			var msg = "결제가 완료되었습니다. \n";
			msg += '취소/환불 문의는 센터로 문의바랍니다. \n';
			msg += '\n';
			msg += '주문번호 : ' + data.merchant_uid + '\n';
			msg += '예약 시설 이름 : ' + data.name + '\n';
			msg += '예약자 : ' + data.buyer_name + '\n';
            msg += '예약자 전화번호 : ' + memberTel + '\n';
			msg += '예약자 주소 : ' + memberAddr + ' ' + addrDetail + '\n';      

            fetch('/rental/updateRentalStatus1', { //요청경로
				method: 'POST',
				cache: 'no-cache',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
				},
				//컨트롤러로 전달할 데이터
				body: new URLSearchParams({
					facilityName : facilityName,
					memberName : data.buyer_name,
					memberTel : memberTel,
					memberAddr : memberAddr + ' ' + addrDetail,
					rentalSignCode : rentalSignCode
				})
			})
			.then((response) => {
				if(!response.ok){
					alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
					return ;
				}
		
				return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
				//return response.json(); //나머지 경우에 사용
			})
			//fetch 통신 후 실행 영역
			.then((data) => {//data -> controller에서 리턴되는 데이터!

			})
			//fetch 통신 실패 시 실행 영역
			.catch(err=>{
				var msg = "통신 실패"
				console.log(msg +'\n'+ err);
			});

        }else{
        	var msg = "결제 실패"
        	msg += "에러 내용" + rsp.error_msg;
        }
		alert(msg);
		document.location.href="/rental/myRentalHistory?menuCode="+menuCode;
	});
}