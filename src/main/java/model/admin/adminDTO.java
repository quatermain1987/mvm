package model.admin;

public class adminDTO {
	private int qna_no;
	private String qna_subject;
	private int report_no;
	private int report_option;
	private int review_no;
	private int user_no;
	private String user_name;
	private String user_nickname;

	public int getQna_no() {
		return qna_no;
	}

	public int getReport_no() {
		return report_no;
	}

	public String getQna_subject() {
		return qna_subject;
	}

	public int getReport_option() {
		return report_option;
	}

	public int getReview_no() {
		return review_no;
	}

	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}

	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}

	public void setQna_subject(String qna_subject) {
		this.qna_subject = qna_subject;
	}

	public void setReport_option(int report_option) {
		this.report_option = report_option;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

}
