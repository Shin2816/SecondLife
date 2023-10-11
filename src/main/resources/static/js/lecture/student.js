// student.js
function deleteStudent(lectureCode, studentCode){
    if(confirm('정말 삭제 하시겠습니까?')){
        location.href=`/lecture/deleteStudent?lectureCode=${lectureCode}&studentCode=${studentCode}`;
    }
}