// admin_lecture_event_detail.js
function deleteLectureEvent(lectureEventCode){
    if(confirm('정말 삭제 하시겠습니까?')){
        location.href=`/lecture/adminDeleteLectureEvent?lectureEventCode=${lectureEventCode}`;
    }
}