package model.mypage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.DBConnectionMgr;

public class UserDAO {

	private DBConnectionMgr pool = null;// 1.선언
	// 추가
	// 공통으로 접속할 경우 필요한 멤버변수
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";// 실행시킬 SQL구문 저장
	// 2.생성자를 통해서 연결=>의존성

	public UserDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
			System.out.println("pool=>" + pool);
		} catch (Exception e) {
			System.out.println("DB접속오류=>" + e);
		}
	}// 생성자

	private UserDTO CreateUserDTO() throws Exception {
		UserDTO user = new UserDTO();
		user.setUserNickname(rs.getString("USER_NICKNAME"));
		user.setUserPic(rs.getString("USER_PIC"));
		// DB상에 userBirth 자료형이 String이라 Date자료형으로 바꿔 저장
//		String userBirthStr = rs.getString("USER_BIRTH");
//		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
//		java.util.Date userBirth = dateFormat.parse(userBirthStr);
//		System.out.println("testsetsetset : "+new Date(userBirth.getTime()));
//		user.setUserBirth(new Date(userBirth.getTime()));
		user.setUserBirth(rs.getString("USER_BIRTH"));
		System.out.println("user-birth : "+rs.getString("USER_BIRTH"));
		user.setUserName(rs.getString("USER_NAME"));
		user.setUserIntroduce(rs.getString("USER_INTRODUCE"));
		user.setUesrRegdate(rs.getDate("USER_REGDATE"));
		user.setUserEmail(rs.getString("USER_EMAIL"));
		user.setUserPasswd(rs.getString("user_password"));
		return user;
	}

	// 사용자 정보 가져오기
	public UserDTO getUser(int idnum) {
		UserDTO user = new UserDTO();
		try {
			con = pool.getConnection();
			sql = "select m.user_nickname,m.user_pic,m.user_birth,"
					+ "m.user_name,m.user_introduce,m.user_regdate,l.user_email,l.user_password "
					+ "from (select * from MEMBER where USER_NO=?) " 
					+ "m left join login l on m.USER_NO=l.USER_NO"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 보여주는 결과가 있다면
				user = CreateUserDTO();// 모든 DTO에 값을 넣을 필요한 값만 넣음
			}
			System.out.println("getUser() 호출됨");
		} catch (Exception e) {
			System.out.println("getUserNickname()메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return user;
	}

	// 배너 사용자 정보 가져오기
	public Map<String, String> getBannerUser(int idnum) {
		Map<String, String> user = new HashMap<>();
		try {
			con = pool.getConnection();
			sql = "select USER_NICKNAME,USER_INTRODUCE,USER_PIC from MEMBER where USER_NO=?"; // select count(*) from
																								// member
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 보여주는 결과가 있다면
				user.put("userNickname", rs.getString("USER_NICKNAME"));
				user.put("userIntroduce", rs.getString("USER_INTRODUCE"));
				user.put("userPic", rs.getString("USER_PIC"));
				System.out.println("@@@@ : "+rs.getString("USER_PIC"));
			}
			System.out.println("getBannerUser() 호출됨");
		} catch (Exception e) {
			System.out.println("getBannerUser()메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return user;
	}

	// 사용자 선호장르 가져오기
	public List<String> getUserLikeGenre(int idnum) {
		List<String> userLikeGenreList = new ArrayList<>();
		try {
			con = pool.getConnection();
			sql = "SELECT g.genre_name FROM like_genre l " + "INNER JOIN genre g "
					+ "ON l.genre_no = g.genre_no where l.user_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					String userLikeGenre = rs.getString("genre_name");
					System.out.println(userLikeGenre);
					userLikeGenreList.add(userLikeGenre);
				} while (rs.next());
				System.out.println(rs);
			}
			System.out.println("getUserLikeGenre() 호출됨=" + userLikeGenreList.size());
		} catch (Exception e) {
			System.out.println("getUserLikeGenre() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return userLikeGenreList;
	}

	// 사용자 정보 수정
	public int updateUserProfile(int idnum, String nick, String pic, String name, String intro, String passwd) {
		int result;
		try {
			// oracle 11g에서 update문을 left join할수없으므로 각테이블마다 실행함
			con = pool.getConnection();
			con.setAutoCommit(false);
			sql = "update member set user_nickname=?, user_pic=?, user_name=?, user_introduce=? where user_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nick);
			pstmt.setString(2, pic);
			pstmt.setString(3, name);
			pstmt.setString(4, intro);
			pstmt.setInt(5, idnum);
			result = pstmt.executeUpdate();
			System.out.println("updateUserProfile() member테이블 호출됨");
			sql = "update login set user_password=? where user_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, passwd);
			pstmt.setInt(2, idnum);
			result += pstmt.executeUpdate();
			con.commit();
			System.out.println("updateUserProfile() login테이블 호출됨");
		} catch (Exception e) {
			result = 0;
			System.out.println("updateUserProfile() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	// 사용자 정보 수정 사진없이(오버리딩)
	public int updateUserProfile(int idnum, String nick, String name, String intro, String passwd) {
		int result;
		try {
			
			// oracle 11g에서 update문을 left join할수없으므로 각테이블마다 실행함
			con = pool.getConnection();
			con.setAutoCommit(false);
			sql = "update member set user_nickname=?, user_name=?, user_introduce=? where user_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nick);
			pstmt.setString(2, name);
			pstmt.setString(3, intro);
			pstmt.setInt(4, idnum);
			result = pstmt.executeUpdate();
			System.out.println("updateUserProfile() member테이블 호출됨");
			sql = "update login set user_password=? where user_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, passwd);
			pstmt.setInt(2, idnum);
			result += pstmt.executeUpdate();
			con.commit();
			System.out.println("updateUserProfile() login테이블 호출됨");
		} catch (Exception e) {
			result = 0;
			System.out.println("updateUserProfile() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	
	// 사용자 탈퇴
	public int deleteUserProfile(int idnum) {
		int result;
		try {
			// oracle 11g에서 update문을 left join할수없으므로 각테이블마다 실행함
			con = pool.getConnection();
			sql = "delete from login where user_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			result = pstmt.executeUpdate();
			System.out.println("deleteUserProfile() login테이블 삭제 호출됨");
			sql = "UPDATE member set user_status=0 where user_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			result += pstmt.executeUpdate();
			System.out.println("deleteUserProfile() status 수정 호출됨");
		} catch (Exception e) {
			result = 0;
			System.out.println("deleteUserProfile() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	
	public String getPasswd(int idnum) {
		String passwd = "";
		try {
			con = pool.getConnection();
			sql = "select user_password from login where user_no=?"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 보여주는 결과가 있다면
				passwd = rs.getString("USER_PASSWORD");// 모든 DTO에 값을 넣을 필요한 값만 넣음
			}
			System.out.println("getPasswd() 호출됨");
		} catch (Exception e) {
			System.out.println("getPasswd()메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return passwd;
	}
	

	//선호장르 수정
	public int updateLikeGenre(int idnum, List<Integer> likegenreList) {
		int result;
		try {
			con = pool.getConnection();
			sql = "delete from like_genre where user_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			result = pstmt.executeUpdate();
			System.out.println("updateLikeGenre() delete 호출됨");
			sql = "insert into like_genre (like_genre_no,user_no,genre_no) values (like_genre_seq.NEXTVAL,?,?)";
	        pstmt = con.prepareStatement(sql);
	        for (int genre : likegenreList) {
	            pstmt.setInt(1, idnum);
	            pstmt.setInt(2, genre);
	            result = pstmt.executeUpdate();
	        }
	        System.out.println("updateLikeGenre() update 수정 호출됨");
		} catch (Exception e) {
			result = 0;
			System.out.println("updateLikeGenre()메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}
}
