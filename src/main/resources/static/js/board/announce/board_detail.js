/////////////////////////////////////////////////////////////////////////////announce게시판 글 삭제
function deleteboard(anBoardNum){
    if(confirm('삭제하시면 되돌릴 수 없습니다 \n삭제 하시겠습니까?')){
        location.href=`/announce/deleteAnBoard?anBoardNum=${anBoardNum}`;
    }
}
/////////////////////////////////////////////////////////////////////////////announce게시판 글 수정 유효성 검사
function anUpdateValidate(){
    //오류 메세지 리셋
    resetBoardMessage();
    
    //form태그 id값 가져오기
    const anUpdateBoardForm = document.querySelector('#anUpdateBoardForm');
    //제목 입력 여부 체크
    if(anUpdateBoardForm.anBoardTitle.value == ''){
        boardInvalidate('.title-error-div', '제목은 필수 입력입니다.');
        return;
    }
    //내용 입력 여부 체크
    if(anUpdateBoardForm.anBoardContent.value == ''){
        boardInvalidate('.text-error-div', '내용을 입력해주세요.');
        return;
    }
    //제목 정규식 체크
    let titleRegex = /^.{0,49}$/;        //모든글자 50글자 이하로
    const title = anUpdateBoardForm.anBoardTitle.value; //제목 input값 가져오기
    if(!titleRegex.test(title)){
        boardInvalidate('.title-error-div', '제목은 100글자 이내로 작성해주세요.');
        return;
    }
    //내용 정규식 체크
    let textRegex = /^.{0,299}$/;         //모든 글자 300글자 이하로
    const text = anUpdateBoardForm.anBoardContent.value;
    if(!textRegex.test(text)){
        boardInvalidate('.text-error-div', '내용은 300글자 이내로 작성해주세요.');
        return;
    }

    //submit 실행
    anUpdateBoardForm.submit();
}

//validate 실패 시 메세지 설정
function boardInvalidate(tagid, content){
    document.querySelector(tagid).style.display = 'block';
    document.querySelector(tagid).textContent = content;
}

//글 등록 오류 메세지 초기화
function resetBoardMessage(){
    document.querySelector('.title-error-div').style.display = 'none';
    document.querySelector('.text-error-div').style.display = 'none';
}
///////////////////////////////announce게시판 글 등록 썸머노트//////////////////////////////////////////
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

///////////////////////////////////////////////// 공유하기 ////////////////////////////////////////////
function shareTwitter() {
    var sendText = "개발 재미있다"; // 전달할 텍스트
    var sendUrl = "devpad.tistory.com/"; // 전달할 URL
    window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl);
}
function shareFacebook() {
    var sendUrl = "devpad.tistory.com/"; // 전달할 URL
    window.open("http://www.facebook.com/sharer/sharer.php?u=" + sendUrl);
}
function shareKakao(anBoardNum) { //카카오톡
    // 사용할 앱의 JavaScript 키 설정
    Kakao.init('d9f19096fc6d6af46e68d7f05e372fa6');
  
    // 카카오링크 버튼 생성
    Kakao.Link.createDefaultButton({
      container: '#btnKakao', // 카카오공유버튼ID
      objectType: 'feed',
      content: {
        title: "커뮤니티", // 보여질 제목
        description: "게시판 공유합니다", // 보여질 설명
        imageUrl: "devpad.tistory.com/", // 콘텐츠 URL
        link: {
           webUrl: "http://localhost:8081/announce/boardDetail?anBoardNum=" + anBoardNum
        }
      }
    });
}