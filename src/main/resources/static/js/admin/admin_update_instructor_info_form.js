//모달 변수
const myModal = new bootstrap.Modal('#setNameModal');

// admin_update_instructor_info_form.js

function showChangeNameBox(){
    document.querySelector('#each-info-save-button').setAttribute('onclick', 'setName();');
    document.querySelector('#eachUpdateForm').innerHTML = '이름 : <input type="text" id="aaa">';
    myModal.show();
}
function setName(){
    const name = document.querySelector('#aaa').value;
    document.querySelector('#bbb').value = name;
    myModal.hide();
    //myModal.show();
}

function showChangeAgeBox(){
    
}