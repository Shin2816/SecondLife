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

  function calendarCheck(date){
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
    .then((data) => {//data -> controller에서 리턴되는 데이터!
      // data = rentalTimeList
        let inputTr = document.querySelector('#input-tr');
        let memberName = document.querySelector('#memberName').value;
        console.log(memberName);
        console.log(data.map(d => d.rentalCharge));

        let str ='';
        data.forEach(rentalTime => {
            if (rentalTime.rentalFacilityList.rentalDate != null) {
                str += '<tr>';
                str += '<td>';
                if (rentalTime.rentalFacilityList.rentalStatus == 0) {
                    str += '<input type="checkbox">';
                } else {
                    str += '<input type="checkbox" disabled>';
                }

                str += '</td>';
                str += '<td>' + rentalTime.rentalStartTime + ' ~ ' + rentalTime.rentalEndTime + '</td>';
                str += '<td>';

                if (rentalTime.rentalFacilityList.rentalStatus == 0) {
                    str += rentalTime.rentalFacilityList.rentalCharge;
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

function signBtn(memberName, rentalCharge){
    const checkBoxes = document.querySelectorAll('input[type=checkbox]');
    const facilityCodeSpan = document.querySelector('#facility-name-span');
    const rentalTimeSpan = document.querySelector('#rental-time-span');
    const rentalChargeSpan = document.querySelector('#rental-charge-span');
    const userNameSpan = document.querySelector('#user-name-span');


    // 체크박스의 개수
    let checkBoxCnt = 0;

    //체크한다면 checkBoxCnt ++;
    checkBoxes.forEach(function(checkBox){
        if(checkBox.checked == true){
            ++checkBoxCnt;
        }
    });

    if(memberName == 'null'){
        alert('로그인 후 이용 가능 합니다');
        location.href = '/member/loginForm';
    } else {
        if(checkBoxCnt == 0){
            alert('신청할 시간대를 체크해주세요.');
        } else{
            //데이터 조회
            //데이터를 모달에 세팅
            //모달창 띄우기
            rentalChargeSpan.textContent = (rentalCharge * checkBoxCnt).toLocaleString('ko-KR') + '원';
            userNameSpan.innerHTML = memberName;


            const myModal = new bootstrap.Modal('#signUpModal');
            myModal.show();

            // setTimeout(() => {
            //     myModal.hide();
            // }, 2000);
        }
    };
    
}