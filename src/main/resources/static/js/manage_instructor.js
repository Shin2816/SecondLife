//manage_instructor.js
function deleteInstructor(instructorCode){
    if(confirm('정말 삭제 하시겠습니까?')){
        location.href=`/instructor/deleteInstructor?instructorCode=${instructorCode}`;
    }
}

function showInstructorSimpleInfo(instructorCode){
    fetch('/instructor/showInstructorSimpleInfo', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           'instructorCode' : instructorCode
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        document.querySelector('#simple_profil_title').innerHTML=`${data.instructor.instructorName}강사 프로필`;
        document.getElementById('instructor_img').src=`/images/instructor/${data.instructor.instructorImgVO.instructorAttachedFileName}`;
        document.querySelector('#instructor_name').innerHTML=`이름 : ${data.instructor.instructorName}`;
        document.querySelector('#instructor_age').innerHTML=`나이 : ${data.instructor.instructorAge}`;
        document.querySelector('#instructor_gender').innerHTML=`성별 : ${data.instructor.instructorGender}`;
        document.querySelector('#instructor_major').innerHTML=`수업과목 : ${data.instructor.instructorMajor}`;
        

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}
