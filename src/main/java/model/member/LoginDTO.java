package model.member;

public class LoginDTO {
	private int user_no;
	private String user_email;
	private String user_password;
	private String user_nickname;
	private String user_pic;
	
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = convert(user_email);
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = convert(user_password);
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = convert(user_nickname);
	}
	public String getUser_pic() {
		return user_pic;
	}
	public void setUser_pic(String user_pic) {
		this.user_pic = convert(user_pic);
	}
	
	//이 클래스에서만 사용하기위해서 접근지정자 private <,>,(,)=>변경메서드
	private static String convert(String name) {
		if(name!=null){
	    	//2.입력받은 문자열중에서 자바스크립트 구문을 실행시킬수 있는 특수기호를 입력X(<,>)
	    	//문자열메서드->replaceAll(1.변경전문자열,2.변경후 문자열)
	    	
	    	name=name.replaceAll("<","&lt");
	    	name=name.replaceAll(">","&gt");
	    	//추가 eval(" " or ' ')
	    	name=name.replaceAll("\\(","&#40");
	    	name=name.replaceAll("\\)","&#41");
	    	//"test"  'test'
	    	name=name.replaceAll("\"","&quot");
	    	name=name.replaceAll("\'","&apos");
	    }else{ //name==null
	    	return null; //입력을 하지 않았다면 더 이상 실행X
	    }
		return name;
	}
	
}
 