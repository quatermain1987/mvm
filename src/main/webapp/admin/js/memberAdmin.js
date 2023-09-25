$(function(){

    $('#searchText').on('focus',function(){
        $('.search_wrap').addClass('focusin');
    })
    $('#searchText').on('blur',function(){
        $('.search_wrap').removeClass('focusin');
    })

    $('.mymenu_btn').on('click',function(){
        $('.mymenu_sub').toggle();
    })
    
    $(document).mouseup(function(e){
		let target=$('.mymenu_sub');
	    if(target.has(e.target).length==0) {
	        target.hide();
	    } 
	});
	
	$('#searchBtn').on('click',function(){
		$('#searchForm').submit();
	})
	
})