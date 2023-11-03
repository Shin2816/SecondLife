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