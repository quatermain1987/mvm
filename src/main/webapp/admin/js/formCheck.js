function writeSave(){
	
	if(document.writeform.notice_subject.value==""){
	  alert("제목을 입력하십시요.");
	  document.writeform.notice_subject.focus();
	  return false;
	}
	
	if(document.writeform.notice_content.value==""){
	  alert("내용을 입력하십시요.");
	  document.writeform.notice_content.focus();
	  return false;
	}
        
 }

