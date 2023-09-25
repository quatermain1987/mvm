package model.member;

import java.sql.Timestamp;

public class MemberAdminDTO {
	private int num;
	private int user_no;
	private String user_name;
	private String user_email;
	private String user_nickname;
	private Timestamp user_regdate;
	private String user_status;
	private int user_reportcount;
	

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public Timestamp getUser_regdate() {
		return user_regdate;
	}
	public void setUser_regdate(Timestamp user_regdate) {
		this.user_regdate = user_regdate;
	}
	public String getUser_status() {
		return user_status;
	}
	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}
	public int getUser_reportcount() {
		return user_reportcount;
	}
	public void setUser_reportcount(int user_reportcount) {
		this.user_reportcount = user_reportcount;
	}
	
	
}
