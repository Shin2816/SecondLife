////////////////////////////////announce게시판 글 등록 유효성 정규식//////////////////////////////////////
function anRegValidate(){

    //오류 메세지 리셋
    resetAnMessage();

    //form태그 id값 가져오기
    const anRegBoard = document.querySelector('#anRegBoard');
    //제목 입력 여부 체크
    if(anRegBoard.anBoardTitle.value == ''){
        inputInvalidate('.title-error-div', '제목은 필수 입력입니다.');
        return;
    }
    //내용 입력 여부 체크
    if(anRegBoard.anBoardContent.value == ''){
        inputInvalidate('.text-error-div', '내용을 입력해주세요.');
        return;
    }
    //제목 정규식 체크
    let titleRegex = /^.{0,49}$/;        //모든글자 50글자 이하로
    const title = anRegBoard.anBoardTitle.value; //제목 input값 가져오기
    if(!titleRegex.test(title)){
        inputInvalidate('.title-error-div', '제목은 100글자 이내로 작성해주세요.');
        return;
    }
    //내용 정규식 체크
    let textRegex = /^.{0,299}$/;         //모든 글자 300글자 이하로
    const text = anRegBoard.anBoardContent.value;
    if(!textRegex.test(text)){
        inputInvalidate('.text-error-div', '내용은 300글자 이내로 작성해주세요.');
        return;
    }

    //submit 실행
    anRegBoard.submit();
}
//글 등록 오류 메세지 초기화
function resetAnMessage(){
    document.querySelector('.title-error-div').style.display = 'none';
    document.querySelector('.text-error-div').style.display = 'none';
}
////////////////////////////////announce게시판 글 등록 썸머노트//////////////////////////////////////////
//썸머노트 활성화
$(document).ready(function () {
    $('.summernote').summernote({
       placeholder: '내용을 작성하세요',
       height: 400,
       maxHeight: 400,
       lang: 'ko-KR',
       focus: true
   }); 
});