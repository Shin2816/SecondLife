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

//회원가입 시, 데이터 유효성 검사
function joinValidate(){

    //오류 메시지 리셋
    resetMessage();

    //id 입력 여부 체크
    const memberJoin = document.querySelector('#memberJoin');

    if(memberJoin.memberId.value == ''){
        inputInvalidate('.id-error-div', '아이디는 필수 입력입니다.');
        return;
    }
    else if(memberJoin.memberId.value.length < 3){
        inputInvalidate('.id-error-div', '아이디는 3자리 이상입니다.');
        return;
    }
    
    //pw 입력 여부 체크
    if(memberJoin.memberPW.value==''){
        inputInvalidate('.pw-error-div', '비밀번호는 필수 입력입니다.');
        return;
    }

    //name 입력 여부 체크
    if(memberJoin.memberName.value==''){
        inputInvalidate('.name-error-div', '이름은 필수 입력입니다.');
        return;
    }

    //휴대폰 정규식표현식
    let telRegex = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    const tel = memberJoin.memberTel.value;
    if(!telRegex.test(tel)){
        inputInvalidate('.tel-error-div', '연락처의 형식을 지켜주세요. ex) 010-0000-0000');
        return;
    }

    //addr 입력 여부 체크
    if(memberJoin.memberAddr.value==''){
        inputInvalidate('.addr-error-div', '주소는 필수 입력입니다.')
        return;
    }

    //이메일 정규식표현식
    let emailRegex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
    const email = memberJoin.memberEmail.value;
    if(!emailRegex.test(email)){
        inputInvalidate('.email-error-div', '이메일의 형식을 지켜주세요 ex)aaa@aaa.com');
        return;
    }

    //submit 실행
    memberJoin.submit();
}

//validate 실패 시 메세지 설정
function inputInvalidate(tagid, content){
    document.querySelector(tagid).style.display = 'block';
    document.querySelector(tagid).textContent = content;
}

//validate 오류 메세지 초기화
function resetMessage(){
    document.querySelector('.id-error-div').style.display = 'none';
    document.querySelector('.pw-error-div').style.display = 'none';
    document.querySelector('.name-error-div').style.display = 'none';
    document.querySelector('.tel-error-div').style.display = 'none';
    document.querySelector('.addr-error-div').style.display = 'none';
    document.querySelector('.email-error-div').style.display = 'none';
}



function UpdateValidate(){

    //오류 메시지 리셋
    updateresetMessage();


    const updateJoin = document.querySelector('#updateJoin');

    //pw 입력 여부 체크
    if(updateJoin.memberPW.value == updateJoin.memberPW2){
        inputInvalidate('.pw-error-div', '비밀번호가 같지 않습니다.');
        return;
    }


    //휴대폰 정규식표현식
    let telRegex = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    const tel = updateJoin.memberTel.value;
    if(!telRegex.test(tel)){
        inputInvalidate('.tel-error-div', '연락처의 형식을 지켜주세요. ex) 010-0000-0000');
        return;
    }

    //addr 입력 여부 체크
    if(updateJoin.memberAddr.value==''){
        inputInvalidate('.addr-error-div', '주소는 필수 입력입니다.')
        return;
    }

    //이메일 정규식표현식
    let emailRegex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
    const email = updateJoin.memberEmail.value;
    if(!emailRegex.test(email)){
        inputInvalidate('.email-error-div', '이메일의 형식을 지켜주세요 ex)aaa@aaa.com');
        return;
    }

    //submit 실행
    updateJoin.submit();
}

function updateresetMessage(){
    document.querySelector('.tel-error-div').style.display = 'none';
    document.querySelector('.addr-error-div').style.display = 'none';
    document.querySelector('.email-error-div').style.display = 'none';
}