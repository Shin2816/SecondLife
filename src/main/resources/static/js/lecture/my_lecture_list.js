// my_lecture_list.js
function deleteStudent(studentCode){
    if(confirm('정말 삭제 하시겠습니까?')){
        location.href=`/lecture/deleteStudent?studentCode=${studentCode}`;
    }
}