// lecture_list.js

function lectureList(lectureEventCode){
    const eventCategory = document.querySelector('#lecture-event-category');
    const option = eventCategory.options[eventCategory.selectedIndex].value;
    fetch('/lecture/lectureListFetch', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'lectureEventCode' : option
           // 데이터명 : 데이터값
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
        let str = '';
        data.forEach(lecture => {
        document.querySelector('#lectureBlock').innerHTML = '';
        
        str += `<div class="lecture-box">`;
        str += `       <div class="title">`;
        str += `               <p>`;
        str += `                    <span class="lecture-event-name">${lecture.lectureEventVO.lectureEventName}</span>`;
        str += `                </p>`;
        str += `                <p>`;
        str += `                    <span class="lecture-title" onclick="lectureDetail('${lecture.lectureCode}');">${lecture.lectureTitle}</span>`;
        str += `                </p>`;
        str += `            </div>`;
        str += `            <div class="instructor">`;
        str += `                <p class="top"><span>강사</span></p>`;
        str += `                <p class="bottom"><span class="instructor-name">${lecture.instructorVO.instructorName}</span></p>`;
        str += `            </div>`;
        str += `            <div class="period">`;
        str += `                <p class="top"><span>기간</span></p>`;
        str += `                <p class="bottom"><span class="lecture-period">${lecture.lecturePeriod}</span></p>`;
        str += `            </div>`;
        str += `            <div class="price">`;
        str += `                <p class="top"><span>가격</span></p>`;
        str += `                <p class="bottom"><span class="lecture-price">${lecture.lecturePrice}</span>원</p>`;
        str += `            </div>`;
        str += `        </div>`;

        document.querySelector('#lectureBlock').innerHTML = str;
        });
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function lectureDetail(lectureCode){
    location.href=`/lecture/lectureDetail?lectureCode=${lectureCode}`;
}