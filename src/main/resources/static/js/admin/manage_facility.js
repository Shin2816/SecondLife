// 삭제하기 버튼 클릭 시 실행되는 함수
function deleteFacility(facilityCode){
    if(confirm("삭제하시겠습니까?")){
        location.href = `/center/deleteFacility?facilityCode=${facilityCode}`;
    }
}

// 대관 가능 유무 상태 변경 함수
function updateRentalAvailable(facilityCode, rentalAvailable){
    fetch('/center/updateRentalAvailable', { 
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        body: new URLSearchParams({
            'facilityCode' : facilityCode ,
            'rentalAvailable' : rentalAvailable
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        return response.text();
    })
    //fetch 통신 후 실행 영역
    .then((data) => {
       alert('대관 가능 유무가 변경되었습니다.');
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}