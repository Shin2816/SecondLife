/////////////////////////////////////////////////////////////////////////////QA게시판 글 삭제
function deleteboard(qaBoardNum){
    if(confirm('삭제하시면 되돌릴 수 없습니다 \n삭제 하시겠습니까?')){
        location.href=`/qa/deleteQaBoard?qaBoardNum=${qaBoardNum}`;
    }
}
/////////////////////////////////////////////////////////////////////////////QA게시판 글 수정 유효성 검사
function qaUpdateValidate(){
    //오류 메세지 리셋
    resetBoardMessage();
    
    //form태그 id값 가져오기
    const qaUpdateBoardForm = document.querySelector('#qaUpdateBoardForm');
    //제목 입력 여부 체크
    if(qaUpdateBoardForm.qaBoardTitle.value == ''){
        boardInvalidate('.title-error-div', '제목은 필수 입력입니다.');
        return;
    }
    //내용 입력 여부 체크
    if(qaUpdateBoardForm.qaBoardContent.value == ''){
        boardInvalidate('.text-error-div', '내용을 입력해주세요.');
        return;
    }
    //제목 정규식 체크
    let titleRegex = /^.{0,49}$/;        //모든글자 50글자 이하로
    const title = qaUpdateBoardForm.qaBoardTitle.value; //제목 input값 가져오기
    if(!titleRegex.test(title)){
        boardInvalidate('.title-error-div', '제목은 100글자 이내로 작성해주세요.');
        return;
    }
    //내용 정규식 체크
    let textRegex = /^.{0,299}$/;         //모든 글자 300글자 이하로
    const text = qaUpdateBoardForm.qaBoardContent.value;
    if(!textRegex.test(text)){
        boardInvalidate('.text-error-div', '내용은 300글자 이내로 작성해주세요.');
        return;
    }

    //submit 실행
    qaUpdateBoardForm.submit();
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
///////////////////////////////////////////////////댓글작성
function qaRegComment(selectedTag, name){
    const commentContent = selectedTag.closest('div').querySelector('input[type="text"]').value;

    //댓글 입력 여부 체크
    if(name == 'anonymousUser'){//로그인을 하지 않았다면
        if(confirm('로그인이 필요한 서비스입니다.\n로그인 페이지로 이동하시겠습니까?')){//알람 띄운 후 확인버튼 누르면
            location.href='/member/loginForm';//로그인 페이지로 이동
        }
        return;
    }
    else{//로그인을 했다면
        if(commentContent == ''){
            alert('내용을 입력해주십시요.');
            return;
        }
    
        //댓글 정규식 체크
        let textRegex = /^.{0,99}$/;         //모든 글자 99글자 이하로
        const text = commentContent;
        if(!textRegex.test(text)){
            alert('내용은 100글자 이내로 작성해주세요.');
            return;
        }
    }
    alert('댓글 작성이 완료되었습니다.');
    document.querySelector('#commentForm').submit();

}
////////////////////////////////////////////////////////댓글 삭제(비동기)
function qaDeleteComment(){
    if(confirm('삭제하시면 되돌릴 수 없습니다.\n삭제 하시겠습니까?')){

        alert('댓글 삭제가 완료되었습니다.');
        document.querySelector('#commentDeleteForm').submit();//컨트롤러로 이동
    }
}
///////////////////////////////////////////////////////////////////댓글 수정
function qaUpdateModal(qaUpdateCommentContent){//실제 데이터value
    const qaCommentInput = document.querySelector('#qaCommentInput'); //input 지정해서 변수로
    qaCommentInput.value = qaUpdateCommentContent;//input안에 내용 넣기
}
///////////////////////////////////////////////////////////////////댓글 수정(비동기)
function qaUpdateComment(commentId, qaBoardNum){//수정버튼을 누르면 도착, div id : qaCommentInput 안에 데이터 넣기
    const qaCommentInput = document.querySelector('#qaCommentInput').value;

    if(qaCommentInput == ''){
        alert('내용을 입력해주십시요.');
        return;
    }

    //댓글 정규식 체크
    let textRegex = /^.{0,99}$/;         //모든 글자 99글자 이하로
    const text = qaCommentInput;
    if(!textRegex.test(text)){
        alert('내용은 100글자 이내로 작성해주세요.');
        return;
    }

    alert('댓글 수정이 완료되었습니다.');
    document.querySelector('#commentUpdateForm').submit();
}
////////////////////////////////QA게시판 글 등록 썸머노트//////////////////////////////////////////
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
function shareKakao(qaBoardNum) { //카카오톡
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
           webUrl: "http://localhost:8081/qa/boardDetail?qaBoardNum=" + qaBoardNum
        }
      }
    });
}