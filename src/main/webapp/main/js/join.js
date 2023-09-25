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
            let slideSubtitle = $(swiper.slides[swiper.realIndex]).attr("data-subtitle");
            $(".slide-captions").html(function() {
            return "<h2 class='current-title'>" + slideTitle + "</h2>" + "<h4 class='current-subtitle'>"+"<span class='step'>step"+(swiper.realIndex+1)+".&nbsp;</step>"+ slideSubtitle + "</h4>";
            });
            
           	//실제 슬라이드 인덱스번호 확인
           	console.log("swiper.realIndex="+swiper.realIndex);
            
            //이름,생일,약간 모두 유효성 체크 후 다음버튼 활성화
            if(swiper.realIndex==1){
				$('.swiper-button-next').addClass('swiper-button-disabled');
				if(($('#user_name').hasClass('checked')) && ($('#user_birth').hasClass('checked')) && ($('#checkAll').hasClass('clicked'))){
					$('.swiper-button-next').removeClass('swiper-button-disabled');
				}
				if(!($('#user_name').hasClass('checked')) || !($('#user_birth').hasClass('checked')) || !($('#checkAll').hasClass('clicked'))){
					$('.swiper-button-next').addClass('swiper-button-disabled');
				}
			}
			
			if(swiper.realIndex==2){
				if($('.pwd').hasClass('checked')){
					$('.swiper-button-next').removeClass('swiper-button-disabled');
				} else{
					$('.swiper-button-next').addClass('swiper-button-disabled');
				}
			}
            
            if(swiper.realIndex==3){
				if($('#user_nickname').hasClass('checked')){
					$('.swiper-button-next').removeClass('swiper-button-disabled');
				} else{
					$('.swiper-button-next').addClass('swiper-button-disabled');
				}
			}
            
            if(swiper.realIndex==4){
                $('.join_swiper').css('height','500px');
                $('.swiper .swiper-button-prev').css('top','460px');
                $('.swiper .swiper-button-next').css('top','460px');
            }else{
                $('.join_swiper').css('height','450px');
                $('.swiper .swiper-button-prev').css('top','410px');
                $('.swiper .swiper-button-next').css('top','410px');
                $('.swiper-button-next').text("다음");
                $('.checkmark').hide();
                $('.swiper-button-prev').show();
            }
            
            if(swiper.realIndex==5){
                $('.swiper-button-next').removeClass('swiper-button-disabled');
                $('.swiper-button-next').text("완료");
                $('.swiper-button-prev').hide();
                $('.checkmark').show(); 
            }
            
            if(swiper.realIndex==4){
				let genreClicked = $('.slide-5 .box').hasClass('clicked');
				if(genreClicked.length){
					console.log(genreClicked.length);
					$('.swiper-button-next').removeClass('swiper-button-disabled');
				} else{
					$('.swiper-button-next').addClass('swiper-button-disabled');
					//장르 슬라이드에서 [다음]버튼 클릭 시 submit.
					$('.swiper-button-next').click(function(){
						let formData = $('form').serialize();
					   console.log(formData);
						$.ajax({
			                type: 'post',
			                url: 'joinProc.do',
			                data: formData,
			                success: function(result) {
								let rs=$.trim(result);
								console.log(rs)
								if(rs=='true'){
									swiper.slideTo(6, 1000, false);
									$('.slide-6 > .fail').hide();
									$('.slide-6 > .success').show();
									let confirm=$('.swiper-button-next').text("완료");
									$(confirm).on('click',function(){
									 	location.href='main.do';
									})
					                return;
								}else if(rs=='false'){
									swiper.slideTo(6, 1000, false);
									$('.slide-6 > .fail').show();
									$('.slide-6 > .success').hide();
									let confirm=$('.swiper-button-next').text("완료");
									$(confirm).on('click',function(){
									 	location.href='main.do';
									})
									return;
							   }
							}//success
						})//ajax
						
			 		})//click
				}//else
			}//if
		   }   
		}
     });
   
  	//스와이퍼 다음 버튼 비활성화
	$('.swiper-button-next').addClass('swiper-button-disabled');
    
    // 슬라이드 제목,부제목
    let sizes1 = $(swiper.slides[swiper.realIndex]).attr("data-title");
    let sizes2 = $(swiper.slides[swiper.realIndex]).attr("data-subtitle");
    $(".slide-captions").html(function() {
        return "<h2 class='current-title'>" + sizes1 + "</h2>" + "<h4 class='current-subtitle'>"+"<span class='step'>step"+(swiper.realIndex+1)+".&nbsp;</step>"+ sizes2 + "</h4>";
    });

    $('.fail').hide();

    //slide-1
	//사용자 이메일 => RFC 5322형식을 이용한 유효성체크
	$('.emailCheck').hide();
	$('#email_form .emailAuthCheck').hide();
	$('.sendEmail').on('click', function(e) {
	let userEmail = $('#user_email').val();
    let regex = /^[-0-9A-Za-z!#$%&'*+/=?^_`{|}~.]+@[-0-9A-Za-z!#$%&'*+/=?^_`{|}~]+[.]{1}[0-9A-Za-z]/;
    if (userEmail != "") {//메일 입력란이 공란이 아닌 경우
        if (regex.test(userEmail) == false) {//메일주소가 유효하지 않는 경우
            $('.emailCheck').show();
        } else {//유효한 메일주소인 경우
            $.ajax({
                type: 'post',
                url: 'main/emailSend.jsp',
                data: {user_email: userEmail},
                success: function(data) {//메일 보내기 성공한 경우
                    $('.emailCheck').show();
                    $('.emailCheck').removeClass('alert').addClass('check');
                    $('.emailCheck').find('img').attr('src','main/img/checked.svg');
                    $('.emailCheck > .tip_text').text('인증키가 메일로 전송되었습니다!');
                    let code=$.trim(data);
                    alert(code);
                    $('#email_auth').on('propertychange change keyup paste input',function(){//이메일 인증번호 입력
						let email_auth=$(this).val();
						if(email_auth==code){//이메일로 보낸 인증코드가 인증코드 입력란에 입력된 값과 동일한 경우
							$.ajax({
								type: 'post',
				                url: 'main/emailCheck.jsp',
				                data: {user_email: userEmail},
				                success: function(result) {
									let rs=$.trim(result);
									//console.log(rs);
									if(rs=="true"){//중복된 이메일(가입되어 있는 이메일)
										$('.emailAuthCheck').show();
										$('.emailAuthCheck').removeClass('check').addClass('alert');
										$('.emailAuthCheck > .tip_text').text('이미 가입되어 있는 이메일입니다.');
										$('.swiper-button-next').addClass('swiper-button-disabled');
									}else{//중복된 이메일이 없는 경우(이미 가입된 이메일이 아닌 경우)
										$('.emailAuthCheck').show();
										$('.emailAuthCheck').removeClass('alert').addClass('check');
					                    $('.emailAuthCheck').find('img').attr('src','main/img/checked.svg');
					                    $('.emailAuthCheck > .tip_text').text('인증되었습니다!');
					                    if($('.slide-1').hasClass('swiper-slide-active')){
					               			$('.swiper-button-next').removeClass('swiper-button-disabled');
					               		}
									}
								}, //성공
								error: function(){
									$('.emailAuthCheck').show();
									$('.emailAuthCheck').removeClass('check').addClass('alert');
									$('.emailAuthCheck > .tip_text').text('인증에 실패했습니다. 다시 시도해주세요.');
									if($('.slide-1').hasClass('swiper-slide-active')){
					               			$('.swiper-button-next').addClass('swiper-button-disabled');
					               	}
								}//실패
							});//inner ajax
						}
					});// $('#email_auth').on('propertychange~
                },
                error: function() {
                    $('.emailCheck').show();
                    $('.emailCheck').removeClass('check').addClass('alert');
                    $('.emailCheck').find('img').attr('src','main/img/alert-circle-outline.svg');
                    $('.emailCheck > .tip_text').text('메일 전송에 실패했습니다.');
                    }
                  });
         		}//if (regex.test(userEmail) == false) {//메일주소가 유효하지 않는 경우
           }
       });
 
    	
	// 글자길이 계산
    function getTextLength(str) {
        let len = 0;
        for (let i = 0; i < str.length; i++) {
            if (escape(str.charAt(i)).length == 6) {
                len++;
            }
            len++;
        }
        return len;
    }

	//slide-2
    //사용자 이름
    $('#user_name').on('change',function (){
        let input = $(this).val();
        let inputlength = getTextLength(input);
        console.log("#user_name=>"+$('#user_name').val());
        
        if(input!=""){
            if(inputlength < 2){//한글기준 2글자 미만인 경우
                $('.nameCheck').addClass('alert');
                $('.nameCheck > .tip_icon > img').attr('src','main/img/alert-circle-outline.svg');
                $(this).removeClass('checked');
            }
            if(inputlength > 4){//한글기준 4글자 이상인 경우
                $('.nameCheck').removeClass('alert');
                $('.nameCheck > .tip_icon > img').attr('src','main/img/help-circle-outline.svg');
                $(this).addClass('checked');
            } 
        }
        if(input==""){
			$('.nameCheck').removeClass('alert');
            $('.nameCheck > .tip_icon > img').attr('src','main/img/help-circle-outline.svg');
            $(this).removeClass('checked');
		}
    });
	       
  
	//연령체크
    $('#user_birth').on('change',function (){
		let today = new Date();//오늘
    	let yyyy = today.getFullYear();//오늘 연도
        //사용자가 입력하는 값
        let input = $(this).val();
        let inputlength = getTextLength(input);
        console.log("#user_birth=>"+input);
        
        //입력한 값을 연월일로 분리
        if(inputlength==8){
            let inputYY = input.substr(0,4);//index 0번부터 4개까지
            let inputMM = input.substr(4,2);
            let inputDD = input.substr(6,2);
            if((yyyy-inputYY)<15){//14세 미만
                $('.birthCheck').addClass('alert');
                $('.birthCheck > .tip_icon > img').attr('src','main/img/alert-circle-outline.svg');
                $(this).removeClass('checked');
            }
            if((yyyy-inputYY)>15){//14세 이상
                $('.birthCheck').removeClass('alert');
                $('.birthCheck > .tip_icon > img').attr('src','main/img/help-circle-outline.svg');
                $(this).addClass('checked');
            }
        }else{
            $('.birthCheck').removeClass('alert');
            $('.birthCheck > .tip_icon > img').attr('src','main/img/help-circle-outline.svg');
            $(this).removeClass('checked');
        }
    });

    // 약관동의
   $('#checkAll').removeClass('.clicked');
   $('.checker').on('click', '#checkAll',function () {//전체선택
        let checked = $(this).is(':checked');
        if(checked){
            $(this).parents('.checker').find('input').prop('checked', true);
            $('#checkAll').addClass('clicked');
        }
        if(checked == false){
            $(this).parents('.checker').find('input').prop('checked', false);
   			$('#checkAll').removeClass('clicked');
        }
    });
	    
	$('.checker').on('click', '.normal', function () {//둘 중 하나라도 선택해제 되면 전체선택 해제
        let checkeditem = $(this).is(':checked');
        if(!checkeditem){
            $('#checkAll').prop('checked', false);
            $('#checkAll').removeClass('clicked');
        }
    });
    
    $('.checker').on('click', '.normal', function() {//두 개가 모두 선택되면 전체선택 선택
        let checkeditems = true;
        $('.checker .normal').each(function(){
            checkeditems = checkeditems && $(this).is(':checked');
        });
        $('#checkAll').prop('checked', checkeditems);
        if($('#checkAll').is(':checked')){
			$('#checkAll').addClass('clicked');
		}   
    });
    
    //step2 - 이름,나이,약관동의 모두 완료 시
    $('#user_name, #user_birth, #checkAll').on('change', function(){
		if(($('#user_name').hasClass('checked')) && ($('#user_birth').hasClass('checked')) && ($('#checkAll').hasClass('clicked'))){
			$('.swiper-button-next').removeClass('swiper-button-disabled');
			return;
		}
		if(!($('#user_name').hasClass('checked')) || !($('#user_birth').hasClass('checked')) || !($('#checkAll').hasClass('clicked'))){
			$('.swiper-button-next').addClass('swiper-button-disabled');
			return;
		}
	})
    
	    
		
    //slide-3
    //사용자 암호 동일한지 체크
	//.pwd는 #user_password과 #password_check가 공통으로 갖는 클래스
	$('.pwdCheck2').hide();
	$('.pwd').on('change',function(){
		let pwd1=$('#user_password').val();
		let pwd2=$('#password_check').val();
		console.log("pwd1="+pwd1+"pwd2"+pwd2);
		if(pwd1=='' && pwd2==''){
			$('.pwdCheck2').hide();
			}
		if(pwd1!='' && pwd2==''){
			$('.swiper-button-next').addClass('swiper-button-disabled');
		}
        if(pwd1!='' && pwd2!='') {//입력란이 모두 공란이 아닌 경우
            if (pwd1 == pwd2) {// 비밀번호 일치 이벤트 실행
          		$('.pwdCheck2').hide();
                $('.pwdCheck').removeClass('alert');
      	 		$('.pwdCheck > .tip_icon > img').attr('src','main/img/help-circle-outline.svg');
       			$('#user_password, #password_check').addClass('checked');
       			$('.swiper-button-next').removeClass('swiper-button-disabled');
            } else {
                // 비밀번호 불일치 이벤트 실행
                $('.pwdCheck2').show();
       			$('#user_password, #password_check').removeClass('checked');
       			$('.swiper-button-next').addClass('swiper-button-disabled');
            }
        }
	})

	//slide-4
	//닉네임, 프로필 사진(only default)
    $('#user_nickname').on('change',function(){
		let nickname= $('#user_nickname').val();
		if(nickname==""){
			$('#user_nickname').removeClass('checked');
			$('.swiper-button-next').addClass('swiper-button-disabled');
		}else{
			$('#user_nickname').addClass('checked');
			$('.swiper-button-next').removeClass('swiper-button-disabled');
		}
	})
    let defaultPics=["default_1.jpg","default_2.jpg","default_3.jpg","default_4.jpg","default_5.jpg"]
    let nowDefaultPic=defaultPics[Math.floor(Math.random() * defaultPics.length)]
    let defaultPicPath=`main/img/pics/${nowDefaultPic}`;
    $('.user_pic > .pic').append(`<img src="${defaultPicPath}" alt="">`);
    $('.user_pic > .pic').append(`<input type="hidden" name="user_pic" value="${defaultPicPath}">`);
    
	//slide-5 장르 선택
   $('.slide-5 .box').on('click',function(){
	    let genrebtn=$(this);
	    let genreBoxClick = genrebtn.hasClass('clicked');
        let genreValue=genrebtn.val();
		let inputHidden=`<input type="hidden" name="user_genres" value="${genreValue}">`;
		let clickedCount = $('.clicked').length;
		
	    if(genreBoxClick){
	        genrebtn.removeClass('clicked');
	        $('.swiper-button-next').addClass('swiper-button-disabled');
	        genrebtn.find("input[name=user_genres]").remove();
	    }else{
	        genrebtn.addClass('clicked');
	        genrebtn.append(inputHidden);
			if(clickedCount==3){	       
	        	$('.swiper-button-next').removeClass('swiper-button-disabled');
	        }else{
			 	$('.swiper-button-next').addClass('swiper-button-disabled');
			}
			console.log(clickedCount);
	    }
	});
})


