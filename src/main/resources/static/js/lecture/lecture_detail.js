// lecture_detail.js
function lectureApply(lectureCode, menuCode){
    if(confirm('강좌 구매 페이지로 이동 하시겠습니까?')){
        location.href=`/lecture/goLectureApplyForm?lectureCode=${lectureCode}&menuCode=${menuCode}`;
    }
}