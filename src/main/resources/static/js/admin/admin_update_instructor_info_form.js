//admin_update_instructor_info_form.js

// // 모달 변수
// const myModal = new bootstrap.Modal('#setNameModal');

// // 이름 수정
// function showChangeNameBox(){
//     document.querySelector('#each-info-save-button').setAttribute('onclick', 'setName();');
//     document.querySelector('#eachUpdateForm').innerHTML = '이름 : <input type="text" id="modal-name">';
//     myModal.show();
// }
// function setName(){
//     document.querySelector('#input-name').value = document.querySelector('#modal-name').value;
//     myModal.hide();
//     //myModal.show();
// }
// // 나이 수정
// function showChangeAgeBox(){
//     document.querySelector('#each-info-save-button').setAttribute('onclick', 'setAge();');
//     document.querySelector('#eachUpdateForm').innerHTML = '나이 : <input type="text" id="modal-age">';
//     myModal.show();
// }
// function setAge(){
//     document.querySelector('#input-age').value = document.querySelector('#modal-age').value;
//     myModal.hide();
// }
// // 성별 수정
// function showChangeGenderBox(){
//     document.querySelector('#each-info-save-button').setAttribute('onclick', 'setGender();');
//     document.querySelector('#eachUpdateForm').innerHTML = '남자<input type="radio" class="modal-gender" name="ggg"> 여자<input type="radio" class="modal-gender" name="ggg">';
//     myModal.show();
// }
// function setGender(){
//     document.querySelector('#input-gender').value = document.querySelector('.modal-gender').value;
//     myModal.hide();
// }
function buttonSubmit(){
    if(confirm('변경사항을 저장 하시겠습니까?')){
        document.querySelector('#updateForm').submit();
    }
}