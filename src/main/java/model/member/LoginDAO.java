package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.DBConnectionMgr;

public class LoginDAO {

	private DBConnectionMgr pool=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private String sql="";

	public LoginDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		}catch(Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}
	}
	
	
	// 입력된 이메일과 비밀번호로 로그인 인증 수행
	public boolean Authenticate(String user_email, String user_password) {
	    boolean check = false; // check 변수를 false로 초기화
	    try {
	        LoginDTO login = getMember(user_email,user_password);
	        if (login != null && login.getUser_password().equals(user_password) && login.getUser_email().equals(user_email)) {
	            check = true;
	        }
	        System.out.println("Authenticate() 성공");
	    } catch (Exception e) {
	        System.out.println("Authenticate() 에러유발=>" + e);
	    }
	    return check;
	}

	//특정 회원을 찾기
	public LoginDTO getMember(String user_email, String user_password) {
		LoginDTO login=null;
		try {
			con=pool.getConnection();
//			sql="select * from login where user_email=? and user_password=?";
			sql="select a.*, b.user_nickname, b.user_pic "
					+ "	from login a, member b "
					+ "	where a.user_no = b.user_no"
					+ "	and a.user_email=?"
					+ "	and a.user_password=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user_email);
			pstmt.setString(2, user_password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				login=new LoginDTO();
				login.setUser_no(rs.getInt("user_no"));
				login.setUser_email(rs.getString("user_email"));
				login.setUser_password(rs.getString("user_password"));
				login.setUser_nickname(rs.getString("user_nickname"));
				login.setUser_pic(rs.getString("user_pic"));
			}
		}catch(Exception e) {
			System.out.println("getMember()에서 실행에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return login;
	}
	
	
}
