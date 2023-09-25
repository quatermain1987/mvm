$(function(){
    let swiper = new Swiper(".swiper", {
    pagination: {
        el: ".swiper-pagination",
        type: "progressbar",
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    }, 
    on: {
        slideChangeTransitionStart: function () {
            // Slide captions
            let swiper = this;
            let slideTitle = $(swiper.slides[swiper.realIndex]).attr("data-title");
            $(".slide-captions").html(function() {
            return "<h2 class='current-title'>" + slideTitle + "</h2>" + "<p class='current-subtitle'>"+ slideSubtitle + "</p>";
            });
			}
		}
     });

    // 슬라이드 제목,부제목
    let sizes1 = $(swiper.slides[swiper.realIndex]).attr("data-title");
    let sizes2 = $(swiper.slides[swiper.realIndex]).attr("data-subtitle");
    $(".slide-captions").html(function() {
        return "<h2 class='current-title'>" + sizes1 + "</h2>" + "<p class='current-subtitle'>"+ sizes2 + "</p>";
    });

})


$('.tip').hide();
function loginCheck(){
    let email = $('#user_email').val();
    let pwd = $('#user_password').val();
    if(email=='' && pwd==''){//모두 공란인 경우
        $('.emailCheck, .passwordCheck').show();
        return;
    }
    if(email=='' && pwd!=''){//email만 공란인 경우
        $('.emailCheck').show();
        $('.passwordCheck').hide();
        return;
    }
    if(email!='' && pwd==''){//pwd만 공란인 경우
        $('.emailCheck').hide();
        $('.passwordCheck').show();
        return;
    }
    if(email!='' && pwd!=''){
    	let formData=$('#login_form').serialize();
    	$.ajax({
			type:'post',
			url:'loginProc.do',
			data: formData,
			 success: function(result) {
	            let rs = $.trim(result);
	            console.log(rs);
	            if (rs === 'false') {
	                $('#user_email').focus();
	                $('.loginCheck').show();
	                return;
	            } else if (rs === 'true') {
	                window.location.replace('/Movivum/main.do');
	         }
	        }
		})
    }
 }
