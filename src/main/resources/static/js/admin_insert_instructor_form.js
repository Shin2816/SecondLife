// insert_instructor.js
function openPost(){
    new daum.Postcode({
        oncomplete: function(data) {
            document.querySelector('#instructorAddr').value = data.roadAddress;
        }
    }).open();
}

function file(){
    document.querySelector('#file').click();
}

function submitButton(){
    document.querySelector('#form').submit();
}

function uploadImg(inputTag){
    var reader = new FileReader();
    reader.onload = function(e) {
        console.log(e.target.result);
        document.querySelector('#instructorImg').src = e.target.result;
        document.querySelector('#instructorImg').style.width = '160px';
        document.querySelector('#instructorImg').style.height = '210px';
        document.querySelector('#instructorImg').style.margin = '-10px';
    };
    reader.readAsDataURL(inputTag.files[0]);
}
// console.log(event);
// let img = document.createElement("img");
//     console.log(img)
//     img.setAttribute("src", '/images/icons/baseball.png');
//     document.querySelector('#img-container').appendChild(img);

// // const reader = new FileReader(document.querySelector('#file'));
// // console.log(reader.onload);
// // reader.onload = function(event) {
// //     console.log(111);
// //     let img = document.createElement("img");
// //     console.log(img)
// //     img.setAttribute("src", event.target.result);
// //     document.querySelector('#img-container').appendChild(img);
// // };
