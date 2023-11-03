// admin_instructor_list.js

function editPhone(instructorCode, editBtn){
    let totalPhone = '';
    let instructorPhone = editBtn.closest('.phone').querySelectorAll('.phones');
    console.log(instructorPhone);
    instructorPhone.forEach((phone, idx) => {
        totalPhone += phone.value + '-'; //010-1111-2222-
    });
    fetch('/instructor/adminUpdateInstructorPhone', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'instructorCode' : instructorCode
           , 'instructorPhone' : totalPhone.substring(0, totalPhone.length - 1)
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
        alert('연락처가 수정 되었습니다.');
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}

function editAddr(instructorCode, editBtn){
    let totalAddr = '';
    let instructorAddr = editBtn.closest('.addr').querySelectorAll('.addrs')
    instructorAddr.forEach((addr, idx) => {
        totalAddr += addr.value + '-'
    });

    fetch('/instructor/adminUpdateInstructorAddr', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'instructorCode' : instructorCode
           , 'instructorAddr' : totalAddr.substring(0, totalAddr.length - 1)
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
        alert('주소가 변경되었습니다.')
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}

function deleteInstructor(instructorCode){
    if(confirm('정말 삭제 하시겠습니까?')){
        location.href=`/instructor/adminDeleteInstructor?instructorCode=${instructorCode}`;
    }   
}