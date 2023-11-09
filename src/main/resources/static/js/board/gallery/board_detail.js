/////////////////////////////////////////////////////////////////////////////사진 게시판 글 삭제
function deleteboard(galBoardNum){
    const menuCode = document.querySelector('#menuCode').value;
    if(confirm('삭제하시면 되돌릴 수 없습니다 \n삭제 하시겠습니까?')){
        location.href=`/gallery/deleteGalBoard?galBoardNum=${galBoardNum}&menuCode=${menuCode}`;
    }
}
/////////////////////////////////////////////////////////////////////////////사진 게시판 글 수정 유효성 검사
function galUpdateValidate(){
    //오류 메세지 리셋
    resetBoardMessage();
    
    //form태그 id값 가져오기
    const galUpdateBoardForm = document.querySelector('#galUpdateBoardForm');
    //제목 입력 여부 체크
    if(galUpdateBoardForm.galBoardTitle.value == ''){
        boardInvalidate('.title-error-div', '제목은 필수 입력입니다.');
        return;
    }
    //내용 입력 여부 체크
    if(galUpdateBoardForm.galBoardContent.value == ''){
        boardInvalidate('.text-error-div', '내용을 입력해주세요.');
        return;
    }
    //제목 정규식 체크
    let titleRegex = /^.{0,49}$/;        //모든글자 50글자 이하로
    const title = galUpdateBoardForm.galBoardTitle.value; //제목 input값 가져오기
    if(!titleRegex.test(title)){
        boardInvalidate('.title-error-div', '제목은 100글자 이내로 작성해주세요.');
        return;
    }
    //내용 정규식 체크
    let textRegex = /^.{0,299}$/;         //모든 글자 300글자 이하로
    const text = galUpdateBoardForm.galBoardContent.value;
    if(!textRegex.test(text)){
        boardInvalidate('.text-error-div', '내용은 300글자 이내로 작성해주세요.');
        return;
    }

    //submit 실행
    galUpdateBoardForm.submit();
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
///////////////////////////////////////////////////댓글작성(비동기)
function galRegComment(selectedTag, galBoardNum, name){
    const menuCode = document.querySelector('#menuCode').value;
    const commentContent = selectedTag.closest('div').querySelector('input[type="text"]').value;

    //댓글 입력 여부 체크
    if(name == 'anonymousUser'){//로그인을 하지 않았다면
        if(confirm('로그인이 필요한 서비스입니다.\n로그인 페이지로 이동하시겠습니까?')){//알람 띄운 후 확인버튼 누르면
            location.href=`/member/loginForm?menuCode=${menuCode}`;//로그인 페이지로 이동
        }
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

    fetch('/gallery/galBoardComment', { //요청경로

        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            // 데이터명 : 데이터값
            'commentContent' : commentContent,
            'commentWriter' : name,
            'commentNum' : galBoardNum
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
        if(data == true){//로그인 정보가 있다면
            alert('등록이 완료 되었습니다.');
            location.href=`/gallery/boardDetail?galBoardNum=${galBoardNum}&menuCode=${menuCode}`;//등록이 완료되고 해당 게시글 상세페이지로 이동
        }
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}
////////////////////////////////////////////////////////댓글 삭제(비동기)
function galDeleteComment(deleteBtn){
    if(confirm('삭제하시면 되돌릴 수 없습니다.\n삭제 하시겠습니까?')){
        alert('댓글 삭제가 완료되었습니다.');
        deleteBtn.closest('li').querySelector('form').submit(); //가장 가까운 form태그 찾아서 submit
    }
}
///////////////////////////////////////////////////////////////////댓글 수정
function galUpdateModal(CommentContent){//실제 데이터value
    const galCommentInput = document.querySelector('#galCommentInput'); //input 지정해서 변수로
    galCommentInput.value = CommentContent;//input안에 내용 넣기
}
///////////////////////////////////////////////////////////////////댓글 수정(비동기)
function galUpdateComment(updateBtn){//수정버튼을 누르면 도착, div id : galCommentInput 안에 데이터 넣기
    const galCommentInput = document.querySelector('#galCommentInput').value;

    if(galCommentInput == ''){
        alert('내용을 입력해주십시요.');
        return;
    }

    //댓글 정규식 체크
    let textRegex = /^.{0,99}$/;         //모든 글자 99글자 이하로
    const text = galCommentInput;
    if(!textRegex.test(text)){
        alert('내용은 100글자 이내로 작성해주세요.');
        return;
    }

    alert('댓글 수정이 완료되었습니다.');
    updateBtn.closest('.select-div').querySelector('form').submit();
}
////////////////////////////////갤러리 게시판 글 등록 썸머노트//////////////////////////////////////////
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
function shareKakao(galBoardNum) { //카카오톡 
    // 사용할 앱의 JavaScript 키 설정
    Kakao.init('d9f19096fc6d6af46e68d7f05e372fa6');
    const menuCode = document.querySelector('#menuCode').value;
  
    // 카카오링크 버튼 생성
    Kakao.Link.createDefaultButton({
      container: '#btnKakao', // 카카오공유버튼ID
      objectType: 'feed',
      content: {
        title: "커뮤니티", // 보여질 제목
        description: "게시판 공유합니다", // 보여질 설명
        imageUrl: "devpad.tistory.com/", // 콘텐츠 URL
        link: {
           webUrl: `http://localhost:8081/gallery/boardDetail?galBoardNum=${galBoardNum}&menuCode=${menuCode}`
        }
      }
    });
}