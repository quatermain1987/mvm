package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import model.DBConnectionMgr;


public class MemberDAO {

		private DBConnectionMgr pool=null;
		private Connection con=null;
		private PreparedStatement pstmt=null;
		private ResultSet rs=null;
		private String sql="";
	
	public MemberDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		}catch(Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}
	}
	
	//회원가입 - 중복 email체크
	public boolean emailCheck(String user_email) {
		boolean check=false;
		try {
			con=pool.getConnection();
			sql="select user_email from login where user_email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user_email);
			rs=pstmt.executeQuery();
			check=rs.next();
			if(check) {
				System.out.println("중복되는 이메일이 존재합니다.");
			}else {
				System.out.println("중복되는 이메일이 없습니다.");
			}
		}catch(Exception e) {
			System.out.println("emailCheck()에서 실행에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return check;
	}
	
	
	//회원가입 - member, login테이블에 insert
	public boolean userInsert(MemberDTO user) {
	    boolean check = false;
	    int insertLogin = 0,insertUser = 0;
	    try {
	        con = pool.getConnection();
	        con.setAutoCommit(false);
	        sql = "INSERT INTO MEMBER (USER_NO, USER_EMAIL, USER_PASSWORD, USER_NAME, USER_BIRTH, USER_NICKNAME, USER_PIC, USER_INTRODUCE, USER_REGDATE, USER_STATUS, USER_LOGINCOUNT) " +
	              "VALUES ((SELECT NVL(MAX(USER_NO), 0)+1 FROM MEMBER), ?, ?, ?, ?, ?, ?, 'None', SYSDATE, '1', 1)";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, user.getUser_email());
	        pstmt.setString(2, user.getUser_password());
	        pstmt.setString(3, user.getUser_name());
	        pstmt.setString(4, user.getUser_birth());
	        pstmt.setString(5, user.getUser_nickname());
	        pstmt.setString(6, user.getUser_pic());
	        insertUser = pstmt.executeUpdate();
	        if (insertUser == 1) {
	        	sql = "SELECT USER_NO FROM MEMBER WHERE USER_EMAIL=?";
	        	pstmt = con.prepareStatement(sql);
	        	pstmt.setString(1, user.getUser_email());
	        	rs = pstmt.executeQuery();
	        	if(rs.next()) {
	        	int userNo=rs.getInt("user_no");
	            sql = "INSERT INTO LOGIN (USER_NO, USER_EMAIL, USER_PASSWORD, USER_NICKNAME, USER_PIC) VALUES (?, ?, ?, ?, ?)";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, userNo);
	            pstmt.setString(2, user.getUser_email());
	            pstmt.setString(3, user.getUser_password());
	            pstmt.setString(4, user.getUser_nickname());
	            pstmt.setString(5, user.getUser_pic());
	            insertLogin = pstmt.executeUpdate();
	        	}
	            if (insertLogin == 1) {
	                con.commit();
	                check = true;
	            }
	        }
	        System.out.println("userInsert() 성공");
	    } catch (Exception e) {
	        System.out.println("userInsert()에서 에러유발=>" + e);
	    } finally {
	        pool.freeConnection(con, pstmt);
	    }
	    return check;
	}
	
	
	//회원가입 - 선호장르에 장르 넣기
	public boolean likeGenreInsert (LikeGenreDTO likeGenre,String user_email) {
		boolean check=false;
		try {
			con=pool.getConnection();
			con.setAutoCommit(false);
			sql="SELECT USER_NO FROM MEMBER WHERE USER_EMAIL=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user_email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int userNo=rs.getInt("USER_NO");
				System.out.println("userNo=>"+userNo);
				sql = "INSERT INTO LIKE_GENRE (USER_NO, LIKE_GENRE) VALUES(?, ?) ";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, userNo);
				pstmt.setInt(2, likeGenre.getUser_genres());
			}
            int insertGenre=pstmt.executeUpdate();
			con.commit();
			System.out.println("likeGenreInsert() 성공=>"+insertGenre);
			if(insertGenre > 0)
				check = true;
			System.out.println();
		}catch(Exception e) {
			System.out.println("likeGenreInsert()에서 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return check;
	}
	
	//로그인 - 이메일과 패스워드로 검색 -> 검색이 되면 로그인 정보 가져오기
	public LoginDTO getUserLogin(String user_email, String user_password) {
		LoginDTO login=null;
		try {
			con=pool.getConnection();
			sql="select * from login where user_email=? and user_password=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user_email);
			pstmt.setString(2, user_password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				login=new LoginDTO();
				login.setUser_no(rs.getInt("user_no"));
				login.setUser_email(user_email);
				login.setUser_password(user_password);
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
		
	
	//회원관리 - 전체 레코드 수를 구하기
	public int getUserCount() {
		int count=0;
		try {
			con=pool.getConnection();
			sql="SELECT MAX(USER_NO) FROM MEMBER";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1); 
			}
		}catch(Exception e) {
			System.out.println("getMemberCount() 에러발생=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return count;
	}

	
	//페이징처리
	public Hashtable pageList(String pageNum,int count) {
		Hashtable <String,Integer> pgList = new Hashtable<String,Integer>();
		int pageSize=10;
		int blockSize=5;
		
		if(pageNum==null){
			pageNum="1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;	
		int endRow = currentPage * pageSize;
		
		int number = 0;
		System.out.println("현재 레코드 수(count)=>" + count);
		
		number = count - (currentPage - 1) * pageSize;
		System.out.println("페이지별 number=>" + number);
		
		int pageCount=count/pageSize+(count%pageSize==0?0:1);

		int startPage=0;
		if(currentPage%blockSize!=0){
			startPage=currentPage/blockSize*blockSize+1;
		}else{
			startPage=((currentPage/blockSize)-1)*blockSize+1;
		}

		int endPage=startPage+blockSize-1;
		System.out.println("startPage=>"+startPage+", endPage=>"+endPage);
		if(endPage > pageCount) endPage=pageCount; 
		
		pgList.put("pageSize", pageSize);
		pgList.put("blockSize", blockSize);
		pgList.put("currentPage", currentPage);
		pgList.put("startRow", startRow);
		pgList.put("endRow", endRow);
		pgList.put("count", count);
		pgList.put("number", number);
		pgList.put("startPage", startPage);
		pgList.put("endPage", endPage);
		pgList.put("pageCount", pageCount);

		return pgList;
	}

	
	//회원관리 - 검색결과 수
	public int getUserSearchCount(String searchOption, String searchText) {
		int x=0;
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
	
			if(searchOption==null || searchText.equals("")) {
				sql="select count(*) from member";
			}else {
				sql="select count(*) from member where "+searchOption+" like '%"+searchText+"%'";
			}
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) { 
				x=rs.getInt(1);
			}
			System.out.println("getUserSearchCount() 완료");
		}catch(Exception e) {
			System.out.println("getUserSearchCount() 에러발생=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	//회원관리 - 검색어에 따른 레코드의 범위 지정에 대한 메서드
	public Map<MemberDTO, Integer> getUserSearchResult(int start, int end, String searchOption, String searchText) {
		System.out.println("start="+start+"end="+end);
		Map<MemberDTO, Integer> userMap = null;
	    try {
	        con=pool.getConnection();
	        if (searchOption == null || searchText.equals("")) {
	        	sql = "SELECT rownum user_no, user_email, user_password, user_name, user_nickname, user_birth, user_pic, user_regdate, user_logincount, user_status, reportcount \r\n"
	        			+ "FROM (SELECT m.user_no, m.user_email, m.user_password, m.user_name, m.user_nickname, m.user_birth, m.user_pic, m.user_regdate, m.user_logincount, m.user_status, count(r.user_no) AS reportcount \r\n"
	        			+ "FROM MEMBER m LEFT JOIN report r ON m.user_no=r.user_no GROUP BY m.user_no, m.user_email, m.user_password, m.user_name, m.user_nickname, m.user_birth, m.user_pic, m.user_regdate, m.user_logincount, m.user_status \r\n"
	        			+ "ORDER BY m.user_no DESC) WHERE user_no <=? AND user_no>=? ORDER BY user_no DESC";
	        } else {
	        	if(searchOption!=null) {
	        		sql = "SELECT rownum user_no, user_email, user_password, user_name, user_nickname, user_birth, user_pic, user_regdate, user_logincount, user_status, reportcount \r\n"
		        			+ "FROM (SELECT m.user_no, m.user_email, m.user_password, m.user_name, m.user_nickname, m.user_birth, m.user_pic, m.user_regdate, m.user_logincount, m.user_status, count(r.user_no) AS reportcount \r\n"
		        			+ "FROM MEMBER m LEFT JOIN report r ON m.user_no=r.user_no where "+searchOption+" like '%"+searchText+"%' GROUP BY m.user_no, m.user_email, m.user_password, m.user_name, m.user_nickname, m.user_birth, m.user_pic, m.user_regdate, m.user_logincount, m.user_status \r\n"
		        			+ "ORDER BY m.user_no DESC) WHERE user_no <=? AND user_no>=? ORDER BY user_no DESC";
	        	}
	        }
	        pstmt=con.prepareStatement(sql);
	        pstmt.setInt(1, end);
	        pstmt.setInt(2, start);
	        rs=pstmt.executeQuery();
	        if(rs.next()) {
	            userMap=new HashMap<>();
	            do {
	            	MemberDTO user=new MemberDTO();
		        	user.setUser_no(rs.getInt("user_no"));
		        	user.setUser_email(rs.getString("user_email"));
		        	user.setUser_password(rs.getString("user_password"));
		        	user.setUser_name(rs.getString("user_name"));
		        	user.setUser_nickname(rs.getString("user_nickname"));
		        	user.setUser_birth(rs.getString("user_birth"));
		        	user.setUser_pic(rs.getString("user_pic"));
		        	user.setUser_regdate(rs.getTimestamp("user_regdate"));
		        	user.setUser_logincount(rs.getInt("user_logincount"));
		        	user.setUser_status(rs.getString("user_status"));
		        	int reportcount=rs.getInt("reportcount");
		        	userMap.put(user, reportcount);
	            } while(rs.next());
	        }
	    } catch(Exception e) {
	        System.out.println("getUserSearchResult() 에러발생=>"+e);
	    } finally {
	        pool.freeConnection(con, pstmt, rs);
	    }
	    return userMap;
	}
	

	//회원관리 - 상세보기
	public MemberDTO getUserDetail(int user_no){
		MemberDTO user=null;
	    try {
	        con = pool.getConnection();
	        sql = "SELECT * FROM Member WHERE user_no=?";
	        pstmt=con.prepareStatement(sql);
	        pstmt.setInt(1, user_no);
			rs=pstmt.executeQuery();
	        if(rs.next()) {
	        	user=new MemberDTO();
	        	user.setUser_no(rs.getInt("user_no"));
	        	user.setUser_email(rs.getString("user_email"));
	        	user.setUser_password(rs.getString("user_password"));
	        	user.setUser_name(rs.getString("user_name"));
	        	user.setUser_nickname(rs.getString("user_nickname"));
	        	user.setUser_birth(rs.getString("user_birth"));
	        	user.setUser_pic(rs.getString("user_pic"));
	        	user.setUser_regdate(rs.getTimestamp("user_regdate"));
	        	user.setUser_logincount(rs.getInt("user_logincount"));
	        	user.setUser_status(rs.getString("user_status"));
	        }
	        System.out.println("getUserDetail() 성공");
	    } catch (Exception e) {
	        System.out.println("getUserDetail()에서 에러유발=>" + e);
	    } finally {
	        pool.freeConnection(con, pstmt, rs);
	    }
	    return user;
	}
	
	//회원관리 - 신고당한 횟수
	public int getUserReportCount(int user_no){
		int reportCount=0;
	    try {
	        con = pool.getConnection();
	        sql = "SELECT COUNT(*) FROM REPORT WHERE user_no=?";
	        pstmt=con.prepareStatement(sql);
	        pstmt.setInt(1, user_no);
			rs=pstmt.executeQuery();
			if(rs.next()) { 
				reportCount=rs.getInt(1);
			}
	        System.out.println("getUserReportCount() 성공");
	    } catch (Exception e) {
	        System.out.println("getUserReportCount()에서 에러유발=>" + e);
	    } finally {
	        pool.freeConnection(con, pstmt, rs);
	    }
	    return reportCount;
	}
	
	//회원관리 - 회원의 프로필 사진
	public String getUserFileName(int user_no){
		String fileName="";
	    try {
	        con = pool.getConnection();
	        sql = "SELECT user_pic FROM MEMBER WHERE user_no=?";
	        pstmt=con.prepareStatement(sql);
	        pstmt.setInt(1, user_no);
			rs=pstmt.executeQuery();
	        if(rs.next()) {
	        	fileName=rs.getString("user_pic");
	        }
	        System.out.println("getUserFileName() 성공");
	    } catch (Exception e) {
	        System.out.println("getUserFileName()에서 에러유발=>" + e);
	    } finally {
	        pool.freeConnection(con, pstmt, rs);
	    }
	    return fileName;
	}
	
	
	//회원관리 - 회원수정
	public int updateUser(MemberDTO user) {
		int check=0;
		try {
			con=pool.getConnection();
			con.setAutoCommit(false);
			int no=user.getUser_no();
			String email=user.getUser_email();
			String password=user.getUser_password();
			String nickname=user.getUser_nickname();
			String pic=user.getUser_pic();
			System.out.println("no=>"+no);
			sql="UPDATE MEMBER SET USER_EMAIL=?, USER_PASSWORD=?, USER_NAME=?, USER_NICKNAME=?, USER_PIC=?, USER_STATUS=? WHERE USER_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setString(3, user.getUser_name());
			pstmt.setString(4, nickname);
			pstmt.setString(5, pic);
			pstmt.setString(6, user.getUser_status());
			pstmt.setInt(7, no);
			int updateMember=pstmt.executeUpdate();
			System.out.println("updateMember=>"+updateMember);
			if(updateMember==1) {
				sql="UPDATE LOGIN SET USER_EMAIL=?, USER_PASSWORD=?, USER_NICKNAME=?, USER_PIC=? WHERE USER_NO=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, email);
				pstmt.setString(2, password);
				pstmt.setString(3, nickname);
				pstmt.setString(4, pic);
				pstmt.setInt(5, no);
				int updateLogin=pstmt.executeUpdate();
				System.out.println("updateUser(데이터 수정유무)=>"+updateLogin);
				if(updateLogin==1) {
					con.commit();
					check=1;
				}
			}
		}catch(Exception e) {
			System.out.println("updateUser()에서 실행에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return check;
	}
	
	
}
