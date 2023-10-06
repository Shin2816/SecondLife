// manage_lecture.js
function deleteLecture(lectureCode){
    if(confirm('정말 삭제 하시겠습니까?')){
        location.href=`/lecture/deleteLecture?lectureCode=${lectureCode}`;
    }
}