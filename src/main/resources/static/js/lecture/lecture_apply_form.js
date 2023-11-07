// lecture_apply_form.js
function cancel(){
    if(confirm('정말 취소 하시겠습니까?')){
        location.href='/';
    }
}

function pay(lectureCode){
    if(confirm('정말 결제 하시겠습니까?')){
        document.querySelector('#payForm').submit();
    }
}