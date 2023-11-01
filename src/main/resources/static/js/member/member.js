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
    const memberJoin = document.querySelector('#memberJoin');

    if(memberJoin.memberId.value == ''){
        inputInvalidate('.id-error-div', '아이디는 필수 입력입니다.');
    }
    else{
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
                document.querySelector('#join-btn').disabled = false;
                resetMessage();
            }else{
                inputInvalidate('.id-error-div', '불가능 아이디.');
            }
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    }
}

//회원가입 버튼 비활성화
function setDisabled(){
    document.querySelector('#join-btn').disabled = true;
}

//휴대폰 인증 번호 발송
function checkPhone(){
    let checkStatusInput = document.querySelector('#checkStatus');
    let checkStatus = checkStatusInput.value;
    fetch('/member/phoneFetch', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'memberTel' : document.querySelector('#memberTel').value
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
        alert("메세지가 전송되었습니다, 다시 보내고 싶으신 경우, 확인 버튼을 눌러주세요.");
        document.querySelector('.check-btn-phone').style.display = "none"
        document.querySelector('.check-btn').style.display = "block";
        checkStatus = 1;
        checkStatusInput.value = checkStatus;
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
        checkStatus = 0;
    });
}

//인증번호 확인 
function check(){
    let checkStatusInput = document.querySelector('#checkStatus');
    let checkStatus = checkStatusInput.value;

    fetch('/member/checkFetch', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'telCheck' : document.querySelector('#telCheck').value
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
            alert("인증되었습니다");
            document.querySelector('.check-btn').style.display = "none";
            checkStatus = 3;
            checkStatusInput.value = checkStatus;
        }else{
            alert("실패하셨습니다. 번호를 다시 확인해주세요.")
            checkStatus = 0;
            checkStatusInput.value = checkStatus;
            document.querySelector('.check-btn').style.display = "block";
            document.querySelector('.check-btn-phone').style.display = "block"
        }
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
        checkStatus = 0;
    });
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

    //휴대폰 정규식표현식
    let telRegex = /^01([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})$/;
    const tel = memberJoin.memberTel.value;
    let checkStatus = document.querySelector('#checkStatus').value;
    if(!telRegex.test(tel)){
        inputInvalidate('.tel-error-div', '연락처의 형식을 지켜주세요. ex) 01012345678');
        return;
    }else if(checkStatus != 3){
        inputInvalidate('.tel-error-div', '휴대폰 인증을 진행해 주세요.');
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


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////▽///▽////▽///▽///▽///               계정 정보 수정                ////▽///▽///▽///▽///▽///▽//////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//계정 정보 수정 데이터 유효성 검사.
function UpdateValidate(){

    //오류 메시지 리셋
    updateresetMessage();

    const updateJoin = document.querySelector('#updateJoin');


    let newMemberPW = document.querySelector('#newMemberPW').value
    let newMemberPW2 = document.querySelector('#newMemberPW2').value

    if(newMemberPW != '' && newMemberPW2 != ''){
        if(newMemberPW != newMemberPW2){
            inputInvalidate('.pw-error-div', '비밀번호가 맞지 않습니다. 확인해주세요.');
            return;
        }else if(newMemberPW.length < 3 || newMemberPW2.length < 3){
            inputInvalidate('.pw-error-div', '비밀번호는 3자리 이상으로 해주세요.');
            return;
        }
    }else{
        inputInvalidate('.pw-error-div', '비밀번호를 입력해주세요.');
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

//계정 정보 수정 오류 메시지 초기화.
function updateresetMessage(){
    document.querySelector('.tel-error-div').style.display = 'none';
    document.querySelector('.addr-error-div').style.display = 'none';
    document.querySelector('.email-error-div').style.display = 'none';
}
