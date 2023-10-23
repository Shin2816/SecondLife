////////////////////////////////자유게시판 글 등록 유효성 정규식//////////////////////////////////////
function qaRegValidate(){

    //오류 메세지 리셋
    resetMessage();

    //form태그 id값 가져오기
    const qaRegBoard = document.querySelector('#qaRegBoard');
    //제목 입력 여부 체크
    if(qaRegBoard.qaBoardTitle.value == ''){
        inputInvalidate('.title-error-div', '제목은 필수 입력입니다.');
        return;
    }
    //내용 입력 여부 체크
    if(qaRegBoard.qaBoardContent.value == ''){
        inputInvalidate('.text-error-div', '내용을 입력해주세요.');
        return;
    }
    //제목 정규식 체크
    let titleRegex = /^.{0,49}$/;        //모든글자 50글자 이하로
    const title = qaRegBoard.qaBoardTitle.value; //제목 input값 가져오기
    if(!titleRegex.test(title)){
        inputInvalidate('.title-error-div', '제목은 100글자 이내로 작성해주세요.');
        return;
    }
    //내용 정규식 체크
    let textRegex = /^.{0,299}$/;         //모든 글자 300글자 이하로
    const text = qaRegBoard.qaBoardContent.value;
    if(!textRegex.test(text)){
        inputInvalidate('.text-error-div', '내용은 300글자 이내로 작성해주세요.');
        return;
    }

    //submit 실행
    qaRegBoard.submit();
}