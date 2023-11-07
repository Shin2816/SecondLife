// admin_insert_lecture_form.js

function submitBtn(){
    document.querySelector('#formTag').submit();
}

function dateCompare(thisInput){
    startDate = document.querySelector('.startDate-inputTag');
    console.log(thisInput.value);
    console.log(startDate.value);
    if(startDate.value != '' && thisInput.value == startDate.value){
        alert('시작일과 종강일이 같을 수 없습니다');
        thisInput.value = '';
    } else if (startDate.value != '' && thisInput.value < startDate.value){
        alert('종강일이 시작일보다 더 빠를 수 없습니다')
        thisInput.value = '';
    } else if (startDate.value == ''){
        alert('시작일을 먼저 입력해주세요.')
        thisInput.value = '';
    }
}

function todayCompare(thisInputTag){
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth() + 1;
    const date = now.getDate();
    let today = year + "-" + ("00" + month.toString()).slice(-2) + "-" + ("00" + date.toString()).slice(-2);
    if(thisInputTag.value == today){
        alert('시작일을 오늘로 설정 할 수 없습니다')
        thisInputTag.value = '';
    } else if (thisInputTag.value < today){
        alert('시작일이 과거 일 수 없습니다.')
        thisInputTag.value = '';
    }
}