// 삭제하기 버튼 클릭 시 실행되는 함수
function deleteFacility(facilityCode){
    if(confirm("삭제하시겠습니까?")){
        location.href = `/center/deleteFacility?facilityCode=${facilityCode}`;
    }
}

// 대관 가능 유무 상태 변경 함수
function updateRentalAvailable(available){
    document.querySelector('#rentalAvailableInput').value = available;
    document.querySelector('#updateRentalAvailableForm').submit();
    alert('대관 가능 상태가 변경되었습니다.')
}

