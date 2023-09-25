package model.notice;

import java.sql.Timestamp;

public class noticeDTO {

	private int notice_no;
	private String notice_subject;
	private String notice_content;
	private Timestamp notice_date;


	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public String getNotice_subject() {
		return notice_subject;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public Timestamp getNotice_date() {
		return notice_date;
	}


	public void setNotice_subject(String notice_subject) {
		this.notice_subject = convert(notice_subject);
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = convert(notice_content);
	}

	public void setNotice_date(Timestamp notice_date) {
		this.notice_date = notice_date;
	}

	// 이 클래스에서만 사용하기위해서 접근지정자 private <,>,(,)=>변경메서드
	private static String convert(String name) {
		if (name != null) {
			// 2.입력받은 문자열중에서 자바스크립트 구문을 실행시킬수 있는 특수기호를 입력X(<,>)
			// 문자열메서드->replaceAll(1.변경전문자열,2.변경후 문자열)

			name = name.replaceAll("<", "&lt");
			name = name.replaceAll(">", "&gt");
			// 추가 eval(" " or ' ')
			name = name.replaceAll("\\(", "&#40");
			name = name.replaceAll("\\)", "&#41");
			// "test" 'test'
			name = name.replaceAll("\"", "&quot");
			name = name.replaceAll("\'", "&apos");
		} else { // name==null
			return null; // 입력을 하지 않았다면 더 이상 실행X
		}
		return name;
	}

}
