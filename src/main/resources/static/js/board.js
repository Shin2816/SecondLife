///////////////////////////////////////////////////댓글작성(비동기)
function freeRegComment(freeBoardWriter, selectedTag, freeBoardNum){
    const commentContent = selectedTag.closest('div').querySelector('input[type="text"]').value;

    fetch('/board/freeBoardComment', { //요청경로

        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            // 데이터명 : 데이터값
            'commentContent' : commentContent,
            'commentWriter' : freeBoardWriter,
            'commentNum' : freeBoardNum
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
            location.href=`/board/boardDetail?freeBoardNum=${freeBoardNum}`;//등록이 완료되고 해당 게시글 상세페이지로 이동
        }
        else{//로그인 정보가 없다면
            if(confirm('로그인이 필요한 서비스입니다.\n로그인 페이지로 이동하시겠습니까?')){//알람 띄운 후 확인버튼 누르면
                location.href='/member/loginForm';//로그인 페이지로 이동
            }
        }
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}
////////////////////////////////////////////////////////삭제(비동기)
function freeDeleteComment(commentId, freeBoardNum){
    if(confirm('삭제하시면 되돌릴 수 없습니다.\n삭제 하시겠습니까?')){
        fetch('/board/freeDeleteComment', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
                // 데이터명 : 데이터값
                'commentId' : commentId
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
            alert('댓글 삭제가 완료 되었습니다.');
            location.href=`/board/boardDetail?freeBoardNum=${freeBoardNum}`;
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    }
}
///////////////////////////////////////////////////////////////////댓글 수정
function freeUpdateModal(freeUpdateCommentContent){//실제 데이터value
    const freeCommentInput = document.querySelector('#freeCommentInput'); //input 지정해서 저장
    freeCommentInput.value = freeUpdateCommentContent;//input안에 내용 넣기
}
///////////////////////////////////////////////////////////////////댓글 수정(비동기)
function freeUpdateComment(commentId, freeBoardNum){//수정버튼을 누르면 도착, div id : freeCommentInput 안에 데이터 넣기
    const freeCommentInput = document.querySelector('#freeCommentInput').value;

    fetch('/board/freeUpdateComment', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            // 데이터명 : 데이터값
            'commentId' : commentId,
            'commentContent' : freeCommentInput
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
        alert('댓글 수정이 완료 되었습니다.');
        location.href=`/board/boardDetail?freeBoardNum=${freeBoardNum}`;
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}
//////////////////////////////////////////자유게시판 글 등록//////////////////////////////////////
function freeRegValidate(){
    //form태그 id값 가져오기
    const freeRegBoard = document.querySelector('#freeRegBoard');
    //제목 입력 여부 체크
    if(freeRegBoard.freeTitle.value == ''){
        inputInvalidate('.title-error-div', '제목은 필수 입력입니다.');
        return;
    }
    //내용 입력 여부 체크
    if(freeRegBoard.freeText.value == ''){
        inputInvalidate('.text-error-div', '내용을 입력해주세요.');
        return;
    }
    //제목 정규식 체크
    let titleRegex = /^.{0,74}$/;        //모든글자 74글자 이하로
    const title = freeRegBoard.freeText.value; //제목 input값 가져오기
    if(!titleRegex.test(title)){
        inputInvalidate('.title-error-div', '제목은 74글자 내로 작성해주세요.');
    }
}