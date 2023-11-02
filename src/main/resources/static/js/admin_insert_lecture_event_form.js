// admin_insert_lecture_event_form.js
function showImg(inputTag){
    var reader = new FileReader();
    reader.onload = function(e){
        console.log(e.target.result);
        document.querySelector('#uploadImg').src = e.target.result;
        document.querySelector('#uploadImg').style.width = '400px';
        document.querySelector('#uploadImg').style.height = '250px';
        document.querySelector('#uploadImg').style.margin = '-15px';
    };
    reader.readAsDataURL(inputTag.files[0]);
}
function submitBtn(){
    document.querySelector('#event-form').submit();
}
function sendClick(){
    document.querySelector('#file-input-tag').click();
}