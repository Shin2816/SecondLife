// instructor_list.js
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
        const myModal = new bootstrap.Modal('#simpleInfoModal');
        document.querySelector('#simple_profil_title').innerHTML=`${data.instructor.instructorName}강사님 프로필`;
        document.querySelector('#instructor_img').src=`/images/instructor/${data.instructor.instructorImgVO.instructorAttachedFileName}`;
        document.querySelector('#instructor_name').innerHTML=`이름 : ${data.instructor.instructorName}`;
        document.querySelector('#instructor_age').innerHTML=`나이 : ${data.instructor.instructorAge}`;
        document.querySelector('#instructor_gender').innerHTML=`성별 : ${data.instructor.instructorGender}`;
        
        let str = '';
        data.lectureList.forEach((lecture, index) => {
            let won = lecture.lecturePrice.toLocaleString();
            str += `<div>
                        <div class="index">${index + 1}</div>
                        <div>
                            <div class="top">
                                <span class="event">${lecture.lectureEventVO.lectureEventName}</span>
                                <span class="start-date">시작일</span>
                                <span class="finish-date">종강일</span>
                                <span class="price">가격</span>
                                <span class="student">정원</span>
                            </div>
                            <div class="bottom">
                                <span class="lecture-title" onclick="location.href='/lecture/lectureDetail?lectureCode=${lecture.lectureCode}';">${lecture.lectureTitle}</span>
                                <span class="lecture-period-date">${lecture.lecturePeriod}</span>
                                <span class="lecture-price">${won}원</span>
                                <span class="lecture-student">${lecture.lectureStudent}</span>
                            </div>
                        </div>
                    </div>`;
        });

        document.querySelector('#lecture-list-modal').textContent = '';
        document.querySelector('#lecture-list-modal').insertAdjacentHTML('afterbegin', str);

        // let str2 = '';
        // data.reviewList.forEach(review => {
        //     str2 += `<tr>`;
        //     str2 += `<td>${review.lectureStarPoint}</td>`;
        //     str2 += `<td>${review.lectureReviewContent}</td>`;
        //     str2 += `</td>`;
        // });

        // document.querySelector('#lecture-review-table tbody').textContent = '';
        // document.querySelector('#lecture-review-table tbody').insertAdjacentHTML('afterbegin', str2);
        
        myModal.show();

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}