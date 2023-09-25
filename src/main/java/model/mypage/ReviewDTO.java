package model.mypage;

import java.sql.Date;

public class ReviewDTO {

	private int review_no;
	private Date reviewDate;
	private float movGrade;
	private int recommCount;
	private String reviewContent, movTitle, userNickname, userPic;

	public int getReview_no() {
		return review_no;
	}

	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}

	public float getMovGrade() {
		return movGrade;
	}

	public void setMovGrade(float movGrade) {
		this.movGrade = movGrade;
	}

	public int getRecommCount() {
		return recommCount;
	}

	public void setRecommCount(int recommCount) {
		this.recommCount = recommCount;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = convert(reviewContent);
	}

	public String getMovTitle() {
		return movTitle;
	}

	public void setMovTitle(String movTitle) {
		this.movTitle = convert(movTitle);
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = convert(userNickname);
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
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
