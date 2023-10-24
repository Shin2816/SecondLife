// admin_instructor_detail.js
function deleteButton(instructorCode){
    if(confirm('정말 삭제 하시겠습니까?')){
        location.href=`/instructor/adminDeleteInstructor?instructorCode=${instructorCode}`;
    }
}