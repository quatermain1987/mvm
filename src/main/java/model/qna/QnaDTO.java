/* psh */

package model.qna;

import java.sql.Timestamp;

public class QnaDTO {
	
	private int qna_no;
	private int user_no;
	private String qna_subject;
	private String qna_content;
	private Timestamp qna_date;
	private String qna_answer;
	private Timestamp qna_answer_date;
	private int admin_no;
	
	
	public int getQna_no() {
		return qna_no;
	}
	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getQna_subject() {
		return qna_subject;
	}
	public void setQna_subject(String qna_subject) {
		this.qna_subject = qna_subject;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public Timestamp getQna_date() {
		return qna_date;
	}
	public void setQna_date(Timestamp qna_date) {
		this.qna_date = qna_date;
	}
	public String getQna_answer() {
		return qna_answer;
	}
	public void setQna_answer(String qna_answer) {
		this.qna_answer = qna_answer;
	}
	public Timestamp getQna_answer_date() {
		return qna_answer_date;
	}
	public void setQna_answer_date(Timestamp qna_answer_date) {
		this.qna_answer_date = qna_answer_date;
	}
	public int getAdmin_no() {
		return admin_no;
	}
	public void setAdmin_no(int admin_no) {
		this.admin_no = admin_no;
	}
	
	
}


	
