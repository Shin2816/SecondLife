//---------------- 전역변수 영역-------------------//
//비밀번호 확인 모달
const qaModal = new bootstrap.Modal('#qaPasswordBoard');
const menuCode = document.querySelector('#menuCode').value;


//---------------- 함수 영역-------------------//
function openCheckPwModal(qaBoardNum, loginInfo){
    //로그인을 했다면
    if(loginInfo != null){
        //관리자라면 모달창 띄우지 않고 바로 boardDetail로 이동
        if(loginInfo.authorities[0].authority == 'ROLE_ADMIN'){
            //프리패스
            location.href=`/qa/boardDetail?commentNum=${qaBoardNum}&menuCode=${menuCode}`;
        } else{
            //해당하는 글의 정보를 input태그의 value로 전달
            document.querySelector('#modal_qaBoardNum').value = qaBoardNum;
            qaModal.show();
        }
    } else{
        document.querySelector('#modal_qaBoardNum').value = qaBoardNum;
        qaModal.show();
    }
}

///////////////////////////////////QA 게시판 비밀번호 유효성 검사////////////////////////////////
function qaPasswordValidate(){
    alert(6);
    //오류 메세지 리셋
    resetPasswordMessage();

    //form태그 id값 가져오기
    const qaPasswordBoardForm = document.querySelector('#qaPasswordBoardForm');

    //비밀번호 입력 여부 체크
    if(qaPasswordBoardForm.qaCheckPwInput.value == ''){
        boardInvalidate('.password-error-div', '비밀번호를 작성해주세요.');
        return;
    }
    //비밀번호 정규식 체크
    let pwRegex = /^.{0,49}$/;        //모든글자 50글자 이하로
    const checkPw = qaPasswordBoardForm.qaCheckPwInput.value; //제목 input값 가져오기
    if(!pwRegex.test(checkPw)){
        boardInvalidate('.password-error-div', '비밀번호는 25자 이내로 작성해주세요.');
        return;
    }

    //submit 실행
    qaPasswordBoardForm.submit();
}
//글 등록 오류 메세지 초기화
function resetPasswordMessage(){
document.querySelector('.password-error-div').style.display = 'none';
}
