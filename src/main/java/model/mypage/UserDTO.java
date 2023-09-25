package model.mypage;

import java.sql.Date;//시간정보는 저장하지 않는 자료형임

///사용자 정보 전달
public class UserDTO {
	private String userNickname, userPic, userName, userBirth, userIntroduce, userEmail, userPassword;
	private Date userRegdate;

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
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
		this.userPic = convert(userPic);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = convert(userName);
	}

	public String getUserIntroduce() {
		return userIntroduce;
	}

	public void setUserIntroduce(String userIntroduce) {
		this.userIntroduce = convert(userIntroduce);
	}

	public Date getUesrRegdate() {
		return userRegdate;
	}

	public void setUesrRegdate(Date uesrRegdate) {
		this.userRegdate = uesrRegdate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = convert(userEmail);
	}

	public String getUserPasswd() {
		return userPassword;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPassword = convert(userPasswd);
	}

	public Date getUserRegdate() {
		return userRegdate;
	}

	public void setUserRegdate(Date userRegdate) {
		this.userRegdate = userRegdate;
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
