function categoryTab(categoryCode){
    fetch('/center/centerGuideFetch', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'centerCateCode' : categoryCode
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
    .then((data) => {//data -> controller에서 리턴되는 데이터(facilityList)
        console.log(data);
        let guideTag = document.querySelector('#guide-content');

        let str ='';
        data.forEach(facilityInfo => {
            str += '<div class="row mb-2">'
            str += '<div class="col-6">'
            str += `<img width="300px" height="200px" src="/images/center/${facilityInfo.facilityImageVO.facilityAttachedFileName}" alt="센터이미지">`
            str += '</div>'
            str += '<div class="col-6">'
            str += '<h4>' + facilityInfo.facilityName + '</h4>';
            str += '<p>' + facilityInfo.facilityContent + '</p>';
            str += '<p>위치 : ' + facilityInfo.facilityPlaceInfo + '</p>';
            str += '</div>'
            str += '</div>'
            str += '<hr>'
        });    

        guideTag.innerHTML = str;
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}