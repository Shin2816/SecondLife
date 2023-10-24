// admin_update_lecture_event_form.js

function submitButton(){
    if(confirm('변경 사항을 저장 하시겠습니까?')){
        document.querySelector('#lectureEventForm').submit();
    }
}
