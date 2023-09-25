package model.detail;

import java.sql.Timestamp;

public class ReviewJoinDTO {
	
	
	private int review_no,mov_no,user_no,grade_no,recomm_count,reply_no;
	private String review_content,user_nickname,reply_content,user_pic;
	private double grade_point;
	private Timestamp review_date;
	private Timestamp reply_date;
	
	

	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public int getMov_no() {
		return mov_no;
	}
	public void setMov_no(int mov_no) {
		this.mov_no = mov_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getGrade_no() {
		return grade_no;
	}
	public void setGrade_no(int grade_no) {
		this.grade_no = grade_no;
	}
	public double getGrade_point() {
		return grade_point;
	}
	public void setGrade_point(double grade_point) {
		this.grade_point = grade_point ;
	}
	public int getRecomm_count() {
		return recomm_count;
	}
	public void setRecomm_count(int recomm_count) {
		this.recomm_count = recomm_count;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public Timestamp getReview_date() {
		return review_date;
	}
	public void setReview_date(Timestamp review_date) {
		this.review_date = review_date;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Timestamp getReply_date() {
		return reply_date;
	}
	public void setReply_date(Timestamp reply_date) {
		this.reply_date = reply_date;
	}
	
	public int getReply_no() {
		return reply_no;
	}
	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}
	public String getUser_pic() {
		return user_pic;
	}
	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}
	
	
	
}
