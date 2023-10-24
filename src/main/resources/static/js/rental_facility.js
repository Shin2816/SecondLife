// 풀캘린더 화면 구현
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var list = [{title : '테스트', start : '2023-10-16'}, {title : '테스트2', start : '2023-10-18', end : '2023-10-22'}, {title : '테스트3', start : '2023-10-22T12:35:00', allDay : false}];
    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev',
        center: 'title',
        right: 'next'
      },
      initialView: 'dayGridMonth',
      initailDate: 'default', // 달력 처음 로드될때 표시되는 날짜. default는 현재 날짜
      locale: 'ko', //달력 한국어
      editable : true, //이벤트 위치 변경 가능 여부
      selectable: true, //달력 클릭 여부
      height: 500,
      dateClick: function(info) { //달력을 클릭 했을 때, 함수 호출
        calendarCheck(info.dateStr); //비동기 통신, 매개변수는 클릭한 날짜
      },
      events: list
    });

    calendar.render();

    $(".fc-daygrid-day-number").each(function(){ // 캘린더 랜더 후 '일'자 없애기.
        var day = $(this).text();
        day = day.replace("일","");
        $(this).text(day);
    });
  });

//풀캘린더 날짜 선택 시 실행되는 함수
function calendarCheck(date){
    let inputTitle = document.querySelector('#rental-table-title'); // h2날짜 표시
    inputTitle.innerHTML = date;

    fetch('/rental/calTest', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'facilityCode' : 'FACILITY_001',
            'rentalDate' : date
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
    .then((data) => {//data -> controller에서 리턴되는 데이터(rentalTimeList)
        let inputTr = document.querySelector('#input-tr');
        let memberName = document.querySelector('#memberName').value;

        let str ='';
        data.forEach(rentalTime => {
            if (rentalTime.rentalFacilityList.rentalDate != null) {
                str += '<tr>';
                str += '<td>';
                if (rentalTime.rentalFacilityList.rentalStatus == 0) {
                    str += `<input type="checkbox" 
                            data-rental-charge='${rentalTime.rentalFacilityList.rentalCharge}'
                            data-facility-name='${rentalTime.rentalFacilityList.facilityName}'
                            data-start-time='${rentalTime.rentalStartTime}'
                            data-end-time='${rentalTime.rentalEndTime}'
                            data-time-code='${rentalTime.rentalTimeCode}'
                            data-rental-date='${rentalTime.rentalFacilityList.rentalDate}'>`;
                } else {
                    str += '<input type="checkbox" disabled>';
                }

                str += '</td>';
                str += '<td>' + rentalTime.rentalStartTime + ' ~ ' + rentalTime.rentalEndTime + '</td>';
                str += `<td>`;

                if (rentalTime.rentalFacilityList.rentalStatus == 0) {
                    str += rentalTime.rentalFacilityList.rentalCharge.toLocaleString('ko-KR');
                } else if (rentalTime.rentalFacilityList.rentalStatus == 1) {
                    str += '<span style="color: blue;">승인 대기중</span>';
                } else if (rentalTime.rentalFacilityList.rentalStatus == 2) {
                    str += '<span style="color: red;">예약 불가</span>';
                }

                str += '</td>';
                str += '<td>' + rentalTime.rentalFacilityList.rentalTeam + '</td>';
                str += '<td>' + rentalTime.rentalFacilityList.rentalUser + '</td>';
                str += '</tr>';
            }
        });

        str += `<button id=sign-btn type="button" class="btn btn-primary" onclick="signBtn('${memberName}')">신청하기</button>`;     
       
        inputTr.innerHTML = str;
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

//신청하기 버튼 클릭 시 실행되는 함수(모달창 열리기)
function signBtn(memberName){
    const facilityNameTag = document.querySelector('#facility-name-tag');
    const rentalDateTag = document.querySelector('#rental-date-tag');
    const rentalTimeTag = document.querySelector('#rental-time-tag');
    const rentalChargeTag = document.querySelector('#rental-charge-tag');
    const totalRentalChargeTag = document.querySelector('#total-rental-charge-tag');
    const userNameTag = document.querySelector('#user-name-tag');
    const insertTimeCodeTag = rentalChargeTag.closest('.row');
    
    let checkBoxes = document.querySelectorAll('input[type=checkbox]');
   
    let checkBoxCnt = 0;  //체크박스 개수
    let rentCharge = 0;   
    let facilityName = '';
    let rentalDate = '';
    let rentTimeCodes = [];
    let rentTimes = [];
    rentalTimeTag.innerHTML = '';

    checkBoxes.forEach(function(checkBox){
        if(checkBox.checked == true){
            ++checkBoxCnt; //체크된 체크박스 개수 증가
            rentCharge = checkBox.dataset.rentalCharge; //요금 dataset들고오기
            facilityName = checkBox.dataset.facilityName; //시설이름 들고오기
            rentalDate = checkBox.dataset.rentalDate; //대관날짜 들고오기
            let rentTimeCode = checkBox.dataset.timeCode; //타임코드 들고오기
            rentTimeCodes.push(rentTimeCode);
            let renTimeObject = {'startTime' : checkBox.dataset.startTime, 'endTime' : checkBox.dataset.endTime};
            rentTimes.push(renTimeObject);
        }
    });

    //로그인체크
    if(memberName == 'null'){
        alert('로그인 후 이용 가능 합니다');
        location.href = '/member/loginForm';
    } else {
        if(checkBoxCnt == 0){
            alert('신청할 시간대를 체크해주세요.');
        } else{
            facilityNameTag.value = facilityName;
            rentalDateTag.value = rentalDate;
            rentalChargeTag.value = rentCharge;
            totalRentalChargeTag.innerHTML = (rentCharge*checkBoxCnt).toLocaleString('ko-KR') + '원';
            userNameTag.value = memberName;
            rentTimeCodes.forEach((rentTimeCode) => {
                insertTimeCodeTag.insertAdjacentHTML('afterbegin', `<input type="hidden" name="rentalTimeCode" value="${rentTimeCode}" class="rental-time-code-tag"></input>`);
            });
            rentTimes.forEach(rentTime => {
                let rent = '<div>'+rentTime.startTime +' ~ '+rentTime.endTime+'</div>';
                rentalTimeTag.innerHTML += rent;
            });
    
            const myModal = new bootstrap.Modal('#signUpModal');
            myModal.show();
    
            // setTimeout(() => {
            //     myModal.hide(); //모달창 닫기
            // }, 2000);
        }
    };
}