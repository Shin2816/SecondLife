// admin_lecture_list.js
function editInstructor(lectureCode, editBtn){
    let selectTag = editBtn.closest('.instructor').querySelector('#instructor-box');
    fetch('/lecture/adminUpdateLectureInstructor', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'lectureCode' : lectureCode
           , 'instructorCode' : selectTag.value
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        alert('담당선생님이 변경되었습니다.')
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    
}

document.querySelectorAll
function editPeriod(lectureCode, editBtn){
    let inputTags = editBtn.closest('.period').querySelectorAll('.period-input');
    let totalPeriod = '';
    inputTags.forEach(inputTag => {
        totalPeriod += inputTag.value + '~';
    });
    fetch('/lecture/adminUpdateLecturePeriod', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'lectureCode' : lectureCode
           , 'lecturePeriod' : totalPeriod.substring(0, totalPeriod.length - 1)
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        alert('수업 기간이 변경되었습니다.')
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    
}

function editStudent(lectureCode, editBtn){
    let inputTag = editBtn.closest('.student').querySelector('.student-input')
    fetch('/lecture/adminUpdateLectureStudent', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'lectureCode' : lectureCode
           , 'lectureStudent' : inputTag.value
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        alert('수강 정원이 변경되었습니다.')
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function deleteLecture(lectureCode){
    if(confirm('정말 삭제 하시겠습니까?')){
        location.href=`/lecture/adminDeleteLecture?lectureCode=${lectureCode}`;
    }
}

function dateCompare(finishDateValue, thisInput){
    let startDateTag = thisInput.closest('.bottom').querySelector('.left');
    let finishDateTag = thisInput

    if(startDateTag.value == finishDateTag.value){
        alert('시작일과 종강일이 같을 수 없습니다')
        finishDateTag.value = finishDateValue;
    } else if(startDateTag.value > finishDateTag.value){
        alert('종강일이 시작일보다 빠를 수 없습니다')
        finishDateTag.value = finishDateValue;
    }
}
function todayCompare(thisInputTagValue, thisInputTag){
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth() + 1;
    const date = now.getDate();
    let today = year + "-" + ("00" + month.toString()).slice(-2) + "-" + ("00" + date.toString()).slice(-2);
    if(thisInputTag.value == today){
        alert('시작일을 오늘로 설정 할 수 없습니다.')
        thisInputTag.value = thisInputTagValue;
    } else if (thisInputTag.value < today){
        alert('시작일을 과거로 설정 할 수 없습니다.')
        thisInputTag.value = thisInputTagValue;
    }
};




