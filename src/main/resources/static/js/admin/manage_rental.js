

//모달창-반려하기 버튼 클릭시 실행 함수
// function rejectBtn(signCode){
//     console.log(signCode);
//     const InputRejectReason = document.querySelector('input[name=rejectReason]');
//     if(confirm('해당 신청건을 반려하시겠습니까?')){
//         location.href= '/rental/updateStateReject?rentalSignCode='+signCode+'&rejectReason='+ InputRejectReason.value;
//         alert('반려가 완료되었습니다.');
//     }   
// }

//승인 버튼 클릭시 실행되는 함수
function acceptBtn(signCode){
    if(confirm('해당 신청건을 승인하시겠습니까?')){
        location.href= '/rental/updateStatePay?rentalSignCode='+signCode;
        alert('승인이 완료되었습니다.');
    }
}

//모달창-반려하기 클릭시 데이터 유효성 검사(벨리데이트)
function rejectValidate(idx){
    // 오류메세지 초기화
    //resetMessage();
    document.querySelector(`#reject-error-div${idx}`).style.display = 'none';

    // 1. 데이터 유효성 검사
    const rejectForm = document.querySelector(`#rejectForm${idx}`);
    console.log(rejectForm);

    let rejectInput = document.querySelector(`#rejectReason${idx}`);

    //반려 내용 필수 입력/ 15자 이하로 작성
    if(rejectInput.value.length == 0){
        inputInvalidate(`#reject-error-div${idx}`, '반려 내용을 입력해주세요.');
        return;
    } 
    else if(rejectInput.value.length > 15){
        inputInvalidate(`#reject-error-div${idx}`, '15자 이하로 작성해주세요.');
        return;
    }

    // 2. 데이터 가져가기 - submit 실행
    // form태그 선택 -> submit()함수 실행
    if(confirm('해당 신청건을 반려하시겠습니까?')){
        rejectForm.submit();
        alert('반려가 완료되었습니다.');
    }       
    
}

// 오류메세지 초기화
// function resetMessage(){
//     document.querySelector('#reject-error-div').style.display = 'none';
// }

// validate 실패 시 메세지 설정
function inputInvalidate(tagId, message){
    document.querySelector(tagId).style.display = 'block';
    document.querySelector(tagId).textContent = message;
}