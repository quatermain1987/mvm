$(function(){
      	
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

    $('.alert').hide();
    //사용자 이름

    $('#user_name').on('propertychange change keyup paste input',function (){
        let input = $(this).val();
        let inputlength = getTextLength(input);
        if(input!=""){
            if(inputlength < 4){
                $('.nameCheck').addClass('alert');
                $('.nameCheck > .tip_icon > img').attr('src','img/alert-circle-outline.svg');
                $('#user_name').removelass('checked');
            }
            if(inputlength > 4){
                $('.nameCheck').removeClass('alert');
                $('.nameCheck > .tip_icon > img').attr('src','img/help-circle-outline.svg');    
                $('#user_name').addClass('checked');
            }    
        }else{
            $('.nameCheck').removeClass('alert');
            $('.nameCheck > .tip_icon > img').attr('src','img/help-circle-outline.svg');
            $('#user_name').removeClass('checked');
        }
    });
	
	//slide-4
	//닉네임, 프로필 사진(only default)
    $('#user_nickname').on('input',function(){
		let nickname= $('#user_nickname').val();
		if(nickname==""){
			$('#user_nickname').removeClass('checked');
		}else{
			$('#user_nickname').addClass('checked');
		}
	})

    $('#confirmbtn').on('click',function(){
        $('#userdetailForm').submit();
    })
    
   
    $('#user_pic').on('change', handleImgChange);
    function handleImgChange(e){
		var files=e.target.files;
		var reader=new FileReader();
		reader.onload=function(e){
			$('.img').attr('src', e.target.result);
		}	
		reader.readAsDataURL(files[0]);
	}

	
})