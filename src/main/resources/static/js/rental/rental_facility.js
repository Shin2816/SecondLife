//시설 탭 고를때마다 시설코드값 변경
function selectFacility(name, code){
    let facilityName = name.innerText;
    let nameTag = document.querySelector('#facility-name');
    nameTag.innerHTML = facilityName;
    nameTag.dataset.facilityCode = code;

}

// 풀캘린더 화면 구현
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    
    var calendar = new FullCalendar.Calendar(calendarEl, {
        headerToolbar: {
            left: '',
            center: 'title',
            right: 'prev,next'
        },
        initialView: 'dayGridMonth',
        initailDate: 'default', // 달력 처음 로드될때 표시되는 날짜. default는 현재 날짜
        locale: 'ko', //달력 한국어
        editable : false, //이벤트 위치 변경 가능 여부
        selectable: true, //달력 클릭 여부
        height: 600,
        validRange: {
            start: new Date(),  // 현재 날짜 이후의 날짜만 활성화
        },
        dayCellContent: function(info) {    //달력 '일' 삭제
            var dayNum = document.createElement('a');
            dayNum.classList.add('fc-daygrid-day-number');
            dayNum.innerHTML = info.dayNumberText.replace('일', '');
            if(info.view.type == 'dayGridMonth') {
                return {
                    html: dayNum.outerHTML
                };
            } 
            return {
                domNodes: []
            };
            
        },
        dateClick: function(info) { //달력을 클릭 했을 때, 함수 호출
        if(info.date.getDay() === 0 || info.date.getDay() === 6){ //토(6),일(0)만 클릭 가능
            calendarCheck(info.dateStr); //비동기 통신, 매개변수는 클릭한 날짜
        }
        }
    });

    calendar.render();

    //풀캘린더 평일-예약불가 / 주말-예약가능.불가(데이터따라) / 지난날짜-예약마감
    // var days = document.querySelectorAll('.fc-daygrid-day');
    
    // console.log(days);

    // days.forEach(day => {
    //     var cellContent = document.createElement('div');
    //     cellContent.setAttribute('class', 'cell-content');
    //     if(day.classList.contains('fc-day-past') == true){
    //         document.querySelector('.cell-content').style.setProperty("--before-content", "'예약마감'");
    //     } else if(day.classList.contains('fc-day-past') == true){

    //     }
    //     day.appendChild(cellContent);
    // });

    

});





//풀캘린더 날짜 선택 시 실행되는 함수
function calendarCheck(date){
    let inputTitle = document.querySelector('#rental-table-title'); // h2날짜 표시
    inputTitle.innerHTML = date;

    const facilityCode = document.querySelector('#facility-name').dataset.facilityCode;
    console.log(facilityCode);
    
    if(facilityCode == undefined){
        alert('먼저 사용할 시설을 선택해주세요');
        return ;
    }

    fetch('/rental/rentalCalendar', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'facilityCode' : facilityCode,
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
        let memberId = document.querySelector('#memberId').value;

        let str ='';
        data.forEach(rentalTime => {
            if (rentalTime.rentalDate != null) {
                str += '<tr>';
                str += '<td>';
                if (rentalTime.rentalStatus == 0) {
                    str += `<input type="checkbox" 
                            data-rental-charge='${rentalTime.rentalCharge}'
                            data-facility-code='${rentalTime.facilityCode}'
                            data-facility-name='${rentalTime.facilityName}'
                            data-start-time='${rentalTime.rentalTimeVO.rentalStartTime}'
                            data-end-time='${rentalTime.rentalTimeVO.rentalEndTime}'
                            data-time-code='${rentalTime.rentalTimeCode}'
                            data-rental-date='${rentalTime.rentalDate}'>`;
                } else {
                    str += '<input type="checkbox" disabled>';
                }

                str += '</td>';
                str += '<td>' + rentalTime.rentalTimeVO.rentalStartTime + ' ~ ' + rentalTime.rentalTimeVO.rentalEndTime + '</td>';
                str += `<td>`;

                if (rentalTime.rentalStatus == 0) {
                    str += rentalTime.rentalCharge.toLocaleString('ko-KR');
                } else if (rentalTime.rentalStatus > 1) {
                    str += '<span style="color: blue;">승인 대기</span>';
                } else if (rentalTime.rentalStatus == 1) {
                    str += '<span style="color: red;">예약 불가</span>';
                }

                str += '</td>';
                str += '<td>' + rentalTime.rentalTeam + '</td>';
                str += '<td>' + rentalTime.rentalUser + '</td>';
                str += '</tr>';
            }
        });

        str += `<button id=sign-btn type="button" class="btn btn-primary" onclick="signBtn('${memberId}')">신청하기</button>`;     
       
        inputTr.innerHTML = str;
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

//신청하기 버튼 클릭 시 실행되는 함수(모달창 열리기)
function signBtn(memberId){
    const facilityCodeTag = document.querySelector('#facility-code-tag');
    const facilityNameTag = document.querySelector('#facility-name-tag');
    const rentalDateTag = document.querySelector('#rental-date-tag');
    const rentalTimeTag = document.querySelector('#rental-time-tag');
    const rentalChargeTag = document.querySelector('#rental-charge-tag');
    const totalRentalChargeTag = document.querySelector('#total-rental-charge-tag');
    const userNameTag = document.querySelector('#user-name-tag');
    const insertTimeCodeTag = rentalChargeTag.closest('.row');
    
    let checkBoxes = document.querySelectorAll('input[type=checkbox]');
   
    let checkBoxCnt = 0;  //체크박스 개수
    let facilityCode = '';
    let facilityName = '';
    let rentCharge = 0;   
    let rentalDate = '';
    let rentTimeCodes = [];
    let rentTimes = [];
    rentalTimeTag.innerHTML = '';

    checkBoxes.forEach(function(checkBox){
        if(checkBox.checked == true){
            ++checkBoxCnt; //체크된 체크박스 개수 증가
            rentCharge = checkBox.dataset.rentalCharge; //요금 dataset들고오기
            facilityCode = checkBox.dataset.facilityCode; //시설코드 들고오기
            facilityName = checkBox.dataset.facilityName; //시설이름 들고오기
            rentalDate = checkBox.dataset.rentalDate; //대관날짜 들고오기
            let rentTimeCode = checkBox.dataset.timeCode; //타임코드 들고오기
            rentTimeCodes.push(rentTimeCode);
            let renTimeObject = {'startTime' : checkBox.dataset.startTime, 'endTime' : checkBox.dataset.endTime};
            rentTimes.push(renTimeObject);
        }
    });

    //로그인체크
    if(memberId == 'null'){
        alert('로그인 후 이용 가능 합니다');
        location.href = '/member/loginForm';
    } else {
        if(checkBoxCnt == 0){
            alert('신청할 시간대를 체크해주세요.');
        } else{
            facilityNameTag.value = facilityName;
            facilityCodeTag.value = facilityCode;
            rentalDateTag.value = rentalDate;
            rentalChargeTag.value = rentCharge;
            totalRentalChargeTag.innerHTML = (rentCharge*checkBoxCnt).toLocaleString('ko-KR') + '원';
            userNameTag.value = memberId;
            rentTimeCodes.forEach((rentTimeCode) => {
                insertTimeCodeTag.insertAdjacentHTML('afterbegin', `<input type="hidden" name="rentalTimeCode" value="${rentTimeCode}" class="rental-time-code-tag"></input>`);
            });
            rentTimes.forEach(rentTime => {
                let rent = '<div>'+rentTime.startTime +' ~ '+rentTime.endTime+'</div>';
                rentalTimeTag.innerHTML += rent;
            });
    
            const myModal = new bootstrap.Modal('#signUpModal');
            myModal.show();
        }
    };
}


//모달창-대관신청 시 데이터 유효성 검사(벨리데이트)
function rentalSignUpValidate(){
    // 오류메세지 초기화
    resetMessage();

    // 1. 데이터 유효성 검사
    const signRentalForm = document.querySelector('#signRentalForm');


    //단체명 필수 입력/ 8자 이하로 작성
    if(signRentalForm.rentalTeam.value.length == 0){
        inputInvalidate('#team-error-div', '단체명을 입력해주세요.');
        return;
    } 
    else if(signRentalForm.rentalTeam.value.length > 8){
        inputInvalidate('#team-error-div', '8자 이하로 작성해주세요.');
        return;
    }

    // 사용목적 필수 입력/ 10자 이하로 작성 
    if(signRentalForm.rentalPurpose.value.length == 0){
        inputInvalidate('#purpose-error-div', '대관 목적을 입력해주세요.');
        return;
    } 
    else if(signRentalForm.rentalPurpose.value.length > 10){
        inputInvalidate('#purpose-error-div', '10자 이하로 작성해주세요.');
        return;
    }


    // 2. 데이터 가져가기 - submit 실행
    // form태그 선택 -> submit()함수 실행
    if(confirm('대관을 신청하시겠습니까?')){
        signRentalForm.submit();
        alert('신청이 완료되었습니다.');
    }
}

// 오류메세지 초기화
function resetMessage(){
    document.querySelector('#team-error-div').style.display = 'none';
    document.querySelector('#purpose-error-div').style.display = 'none';
}

// validate 실패 시 메세지 설정
function inputInvalidate(tagId, message){
    document.querySelector(tagId).style.display = 'block';
    document.querySelector(tagId).textContent = message;
}