// insert_instructor.js
function openPost(){
    new daum.Postcode({
        oncomplete: function(data) {
            document.querySelector('#instructorAddr').value = data.roadAddress;
        }
    }).open();
}

function file(){
    document.getElementById('file').click();
}