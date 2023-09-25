package model.member;

import java.sql.Timestamp;

public class MemberDTO {

	private int num;
	private int user_no;
	private String user_email;
	private String user_name;
	private String user_birth;
	private String user_password;
	private String user_pic;
	private String user_nickname;
	private String user_introduce;
	private Timestamp user_regdate;
	private String user_status;
	private int user_logincount;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Timestamp getUser_regdate() {
		return user_regdate;
	}

	public void setUser_regdate(Timestamp user_regdate) {
		this.user_regdate = user_regdate;
	}

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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = convert(user_name);
	}

	public String getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(String user_birth) {
		this.user_birth = convert(user_birth);
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = convert(user_password);
	}

	public String getUser_pic() {
		return user_pic;
	}

	public void setUser_pic(String user_pic) {
		this.user_pic = convert(user_pic);
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = convert(user_nickname);
	}

	public String getUser_introduce() {
		return user_introduce;
	}

	public void setUser_introduce(String user_introduce) {
		this.user_introduce = convert(user_introduce);
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public int getUser_logincount() {
		return user_logincount;
	}

	public void setUser_logincount(int user_logincount) {
		this.user_logincount = user_logincount;
	}

	private static String convert(String name) {
		if (name != null) {
			name = name.replaceAll("<", "&lt");
			name = name.replaceAll(">", "&gt");
			// 추가 eval(" " or ' ')
			name = name.replaceAll("\\(", "&#40");
			name = name.replaceAll("\\)", "&#41");
			// "test" 'test'
			name = name.replaceAll("\"", "&quot");
			name = name.replaceAll("\'", "&apos");
		} else {
			return null;
		}
		return name;
	}

}
