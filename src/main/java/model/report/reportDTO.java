package model.report;

import java.sql.Timestamp;

public class reportDTO {

	private int report_no;
	private int user_no;
	private int reply_no;
	private int review_no;
	private int report_option;
	private Timestamp report_date;
	private String report_content;
	private int report_condition; // 1 > 처리중, 2 > 처리완료
	private int report_type; // 1 > 리뷰, 2 > 댓글
	// member DTO
	private String user_name;
	private String user_nickname;

	public String getUser_name() {
		return user_name;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public int getReport_no() {
		return report_no;
	}

	public int getReport_option() {
		return report_option;
	}

	public void setReport_option(int report_option) {
		this.report_option = report_option;
	}

	public int getReport_condition() {
		return report_condition;
	}

	public int getReport_type() {
		return report_type;
	}

	public void setReport_condition(int report_condition) {
		this.report_condition = report_condition;
	}

	public void setReport_type(int report_type) {
		this.report_type = report_type;
	}

	public int getUser_no() {
		return user_no;
	}

	public int getReply_no() {
		return reply_no;
	}

	public int getReview_no() {
		return review_no;
	}

	public Timestamp getReport_date() {
		return report_date;
	}

	public String getReport_content() {
		return report_content;
	}

	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}

	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}

	public void setReport_date(Timestamp report_date) {
		this.report_date = report_date;
	}

	public void setReport_content(String report_content) {
		this.report_content = convert(report_content);
	}

	private static String convert(String name) {
		if (name != null) {

			name = name.replaceAll("<", "&lt");
			name = name.replaceAll(">", "&gt");
			name = name.replaceAll("\\(", "&#40");
			name = name.replaceAll("\\)", "&#41");
			name = name.replaceAll("\"", "&quot");
			name = name.replaceAll("\'", "&apos");
		} else {
			return null;
		}
		return name;
	}

}
