///////////////////////////////////////////////////댓글작성(비동기)
function freeRegComment(freeBoardWriter, selectedTag, freeBoardNum, loginInfo){
    const commentContent = selectedTag.closest('div').querySelector('input[type="text"]').value;

    //댓글 입력 여부 체크
    if(loginInfo == null){//로그인을 하지 않았다면
        if(confirm('로그인이 필요한 서비스입니다.\n로그인 페이지로 이동하시겠습니까?')){//알람 띄운 후 확인버튼 누르면
            location.href='/member/loginForm';//로그인 페이지로 이동
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
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

////////////////////////////////////////////////////////댓글 삭제(비동기)
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
    const freeCommentInput = document.querySelector('#freeCommentInput'); //input 지정해서 변수로
    freeCommentInput.value = freeUpdateCommentContent;//input안에 내용 넣기
}
///////////////////////////////////////////////////////////////////댓글 수정(비동기)
function freeUpdateComment(commentId, freeBoardNum){//수정버튼을 누르면 도착, div id : freeCommentInput 안에 데이터 넣기
    const freeCommentInput = document.querySelector('#freeCommentInput').value;

    if(freeCommentInput == ''){
        alert('내용을 입력해주십시요.');
        return;
    }

    //댓글 정규식 체크
    let textRegex = /^.{0,99}$/;         //모든 글자 99글자 이하로
    const text = freeCommentInput;
    if(!textRegex.test(text)){
        alert('내용은 100글자 이내로 작성해주세요.');
        return;
    }

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
////////////////////////////////자유게시판 글 등록 썸머노트//////////////////////////////////////////
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
////////////////////////////////자유게시판 글 등록 유효성 정규식//////////////////////////////////////
function freeRegValidate(){

    //오류 메세지 리셋
    resetMessage();

    //form태그 id값 가져오기
    const freeRegBoard = document.querySelector('#freeRegBoard');
    //제목 입력 여부 체크
    if(freeRegBoard.freeBoardTitle.value == ''){
        inputInvalidate('.title-error-div', '제목은 필수 입력입니다.');
        return;
    }
    //내용 입력 여부 체크
    if(freeRegBoard.freeBoardContent.value == ''){
        inputInvalidate('.text-error-div', '내용을 입력해주세요.');
        return;
    }
    //제목 정규식 체크
    let titleRegex = /^.{0,49}$/;        //모든글자 50글자 이하로
    const title = freeRegBoard.freeBoardTitle.value; //제목 input값 가져오기
    if(!titleRegex.test(title)){
        inputInvalidate('.title-error-div', '제목은 100글자 이내로 작성해주세요.');
        return;
    }
    //내용 정규식 체크
    let textRegex = /^.{0,299}$/;         //모든 글자 300글자 이하로
    const text = freeRegBoard.freeBoardContent.value;
    if(!textRegex.test(text)){
        inputInvalidate('.text-error-div', '내용은 300글자 이내로 작성해주세요.');
        return;
    }

    //submit 실행
    freeRegBoard.submit();
}

//validate 실패 시 메세지 설정
function inputInvalidate(tagid, content){
    document.querySelector(tagid).style.display = 'block';
    document.querySelector(tagid).textContent = content;
}

//글 등록 오류 메세지 초기화
function resetMessage(){
    document.querySelector('.title-error-div').style.display = 'none';
    document.querySelector('.text-error-div').style.display = 'none';
}
/////////////////////////////////////////////////////////////////////////////자유게시판 글 수정 유효성 검사
function freeUpdateValidate(){
    //오류 메세지 리셋
    resetBoardMessage();
    
    //form태그 id값 가져오기
    const freeUpdateBoardForm = document.querySelector('#freeUpdateBoardForm');
    //제목 입력 여부 체크
    if(freeUpdateBoardForm.freeBoardTitle.value == ''){
        boardInvalidate('.title-error-div', '제목은 필수 입력입니다.');
        return;
    }
    //내용 입력 여부 체크
    if(freeUpdateBoardForm.freeBoardContent.value == ''){
        boardInvalidate('.text-error-div', '내용을 입력해주세요.');
        return;
    }
    //제목 정규식 체크
    let titleRegex = /^.{0,49}$/;        //모든글자 50글자 이하로
    const title = freeUpdateBoardForm.freeBoardTitle.value; //제목 input값 가져오기
    if(!titleRegex.test(title)){
        boardInvalidate('.title-error-div', '제목은 100글자 이내로 작성해주세요.');
        return;
    }
    //내용 정규식 체크
    let textRegex = /^.{0,299}$/;         //모든 글자 300글자 이하로
    const text = freeUpdateBoardForm.freeBoardContent.value;
    if(!textRegex.test(text)){
        boardInvalidate('.text-error-div', '내용은 300글자 이내로 작성해주세요.');
        return;
    }

    //submit 실행
    freeUpdateBoardForm.submit();
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

/////////////////////////////////////////////////////////////////////////////자유게시판 글 삭제
function deleteboard(freeBoardNum){
    if(confirm('삭제하시면 되돌릴 수 없습니다 \n삭제 하시겠습니까?')){
        location.href=`/board/deleteFreeBoard?freeBoardNum=${freeBoardNum}`;
    }
}
//////////////////////////////////////////////////////////////////////////////내글 찾기 비동기
function freeMyBoard(){
    fetch('/board/freeMyBoard', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            // 데이터명 : 데이터값
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
        console.log(data);
        //컨트롤러에서 데이터 조회 후 조회한 데이터를 표로 다시 그려준다.
        //1. 다시 그래주기위해 표를 우선 지운다!
        const boardList = document.querySelector('#board-list-block');
        boardList.innerHTML = ''; //초기화

        data.forEach((element) => {//하나씩 뺏을 때 element
            const row = document.createElement('tr');
            row.classList.add('board-tr'); //tr태그에 class board-tr 스타일적용
            //내용 변경
            row.innerHTML = ` 
                <td>${element.freeBoardNum}</td>
                <td>${element.freeBoardTitle}</td>
                <td>${element.freeBoardWriter}</td>
                <td>${element.freeCreateDate}</td>
                <td>${element.freeBoardReadCnt}</td>
            `;
            boardList.appendChild(row); //<th:block></th:block>안에 tr태그 넣기
        });

        //boardList.textContent = '';

        //2. 조회한 데이터로 표를 다시 그려준다!
        //let tag = '';

        //html의 반복문 인식 못하는듯

        // //element : 리스트에서 하나씩 뺀 데이터의 이름
        // data.map(function(element, index){
        //     tag = `<tr class="board-tr" th:each="freeBoard, state : ${freeBoardList}" th:onclick="|location.href='@{/board/boardDetail(freeBoardNum=${freeBoard.freeBoardNum})}'|">;
        //                 <td>${element.freeBoardNum}</td>
        //                 <td>${element.freeBoardTitle}</td>
        //                 <td>${element.freeBoardWriter}</td>
        //                 <td>${element.freeCreateDate}</td>
        //                 <td>${element.freeBoardReadCnt}</td>
        //             <tr>`;
        // });

        //3. 새로 만든 html을 원하는 위치에 붙여 넣어야한다!
        //boardList.insertAdjacentHTML('afterbegin', tag);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}
