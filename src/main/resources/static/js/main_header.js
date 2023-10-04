//회원가입에서 주소검색 버튼 클릭 시, 실행.
function openPost(){
    new daum.Postcode({
        oncomplete: function(data) {
            document.querySelector("#memberAddr").value = data.roadAddress;
            document.querySelector("#addrDetail").value = data.buildingName;
        }
    }).open();
}

//아이디 중복 확인
function checkId(){

    fetch('/member/idFetch', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'memberId' : document.querySelector('#memberId').value
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
        if(data){
            alert('사용 가능한 아이디');
            document.querySelector('#join-btn').disabled = false;
        }else{
            alert('불가능 아이디');
        }
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

//회원가입 버튼 비활성화
function setDisabled(){
    document.querySelector('#join-btn').disabled = true;
}