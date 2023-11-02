function categoryTab(categoryCode){
    fetch('/center/centerGuideFetch', { 
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        body: new URLSearchParams({
            'centerCateCode' : categoryCode
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }

        return response.json(); 
    })
    .then((data) => {//data -> controller에서 리턴되는 데이터(facilityList)
        let guideTag = document.querySelector('#guide-tab-content');

        let str ='';
        data.forEach(facilityInfo => {
            str += '<div class="row mb-2">'
            str += '<div class="offset-1 col-5 mb-2">'
            str += `<img width="300px" height="200px" src="/images/center/${facilityInfo.facilityImageVO.facilityAttachedFileName}" alt="센터이미지">`
            str += '</div>'
            str += '<div class="col-6">'
            str += '<h3>' + facilityInfo.facilityName + '</h3>';
            str += '<ul>'
            str += '<li><p>' + facilityInfo.facilityContent + '</p></li>';
            str += '<li><p>위치 : ' + facilityInfo.facilityPlaceInfo + '</p></li>';
            str += '</ul>'
            str += '</div>'
            str += '<hr>'
            str += '</div>'
        });    

        guideTag.innerHTML = str;
        
    })
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}