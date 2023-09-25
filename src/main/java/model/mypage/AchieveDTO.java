package model.mypage;

import java.sql.Date;

public class AchieveDTO {

	private int achieveNo, userNo;
	private float avg;
	private Date getDate;
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getAchieveNo() {
		return achieveNo;
	}
	public void setAchieveNo(int achieveNo) {
		this.achieveNo = achieveNo;
	}
	public Date getGetDate() {
		return getDate;
	}
	public void setGetDate(Date getDate) {
		this.getDate = getDate;
	}
	public float getAvg() {
		return avg;
	}
	public void setAvg(float avg) {
		this.avg = avg;
	}

	
}
