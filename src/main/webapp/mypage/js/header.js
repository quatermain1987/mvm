/**
 * header
 */
$(function(){
	/* 로그인 - 서브메뉴 */
	$(document).mouseup(function(e){
		let target=$('.mymenu_sub');
	    if(target.has(e.target).length==0) {
	        target.hide();
	    } 
	});   
	
})
