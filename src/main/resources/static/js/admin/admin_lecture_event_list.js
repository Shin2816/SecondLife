// admin_lecture_event_list.js

function editName(lectureEventCode, editBtn){
    let eventName = editBtn.closest('td').querySelector('input').value;
    console.log(eventName);
    fetch('/lecture/adminUpdateLectureEventName', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'lectureEventCode' : lectureEventCode
           , 'lectureEventName' : eventName
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
        alert('강좌 종목 이름이 변경되었습니다.')
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function editContent(lectureEventCode, editBtn){
    let content = editBtn.closest('td').querySelector('textarea').value;

    fetch('/lecture/adminUpdateLectureEventContent', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'lectureEventCode' : lectureEventCode
           , 'lectureEventContent' : content
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
        alert('강좌 종목 내용이 변경되었습니다.');
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function deleteLectureEvent(lectureEventCode){
    if(confirm('정말 삭제 하시겠습니까?')){
        location.href=`/lecture/adminDeleteLectureEvent?lectureEventCode=${lectureEventCode}`;
    }
}