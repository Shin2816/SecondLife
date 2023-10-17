document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var list = [{title : '테스트', start : '2023-10-16'}, {title : '테스트2', start : '2023-10-18', end : '2023-10-22'}, {title : '테스트3', start : '2023-10-22T12:35:00', allDay : false}];
    var calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth',
      locale: 'ko', //달력 한국어
      editable : true, //이벤트 위치 변경 가능 여부
      selectable: true, //달력 클릭 여부
      dateClick: function(info) { //달력을 클릭 했을 때, 함수 호출
        calendarCheck(info.dateStr); //비동기 통신, 매개변수는 클릭한 날짜
      },
      events: list
    });

    calendar.render();
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

        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
      // data = rentalTimeList
      alert('intelliJ 콘솔 확인.');
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}