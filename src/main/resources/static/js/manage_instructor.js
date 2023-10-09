//manage_instructor.js
function deleteInstructor(instructorCode){
    if(confirm('정말 삭제 하시겠습니까?')){
        location.href=`/instructor/deleteInstructor?instructorCode=${instructorCode}`;
    }
}