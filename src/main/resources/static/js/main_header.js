




//메인페이지 slick 슬라이드
$(function(){
    $('.slide').slick({
        infinite: true,
        autoplay: true,
        autoplaySpeed: 2700,
        speed: 1200,
        slidesToShow: 1,
        prevArrow : '.top_banner_prev',
        nextArrow : '.top_banner_next',
        adaptiveHeight: true
    });
})