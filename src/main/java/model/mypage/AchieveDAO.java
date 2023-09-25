package model.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.DBConnectionMgr;

public class AchieveDAO {

	private DBConnectionMgr pool = null;
	// 공통
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;// select
	private String sql = "";// 실행시킬 SQL구문

	// 2.생성자를 통해서 연결
	public AchieveDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
			System.out.println("pool=>" + pool);
		} catch (Exception e) {
			System.out.println("DB접속 오류=>" + e);
		}
	}

	// DTO생성
	private AchieveDTO makeAchieveFormResult() throws Exception {
		AchieveDTO achieve = new AchieveDTO();
		achieve.setGetDate(rs.getDate("get_date"));
		achieve.setAchieveNo(rs.getInt("achieve_no"));
		achieve.setUserNo(rs.getInt("user_no"));
		achieve.setAvg(rs.getFloat("avg"));
		return achieve;
	}
		
	// 가장 최근 도전과제 가져오기
	public List<AchieveDTO> getRecentAchieve(int idnum) {// getMemberCount()
		List<AchieveDTO> achieveList = new ArrayList<>();
		try {
			con = pool.getConnection();
			sql = "SELECT a.achieve_no, a.user_no, a.get_date, COUNT(b.achieve_no) / (SELECT COUNT(*) FROM member) AS avg "
					+ "FROM achieve a "
					+ "LEFT JOIN achieve b ON a.achieve_no = b.achieve_no "
					+ "WHERE a.user_no = ? AND a.get_date >= ADD_MONTHS(SYSDATE, -1) "
					+ "GROUP BY a.achieve_no, a.user_no, a.get_date";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 보여주는 결과가 있다면
				do {
					AchieveDTO achieve = makeAchieveFormResult();
					achieveList.add(achieve);
				} while (rs.next());
				System.out.println(rs);
			}
			System.out.println("getAchieve()호출됨=" + achieveList.size());
		} catch (Exception e) {
			System.out.println("getAchieve() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return achieveList;
	}

	//도전과제 가져오기
	public List<AchieveDTO> getAchieve(int idnum) {
		List<AchieveDTO> achieveList = new ArrayList<>();
		try {
			con = pool.getConnection();
			System.out.println("con=>" + con);
			sql = "SELECT a.achieve_no, a.user_no, a.get_date, COUNT(b.achieve_no) / (SELECT COUNT(*) FROM member) AS avg "
			           + "FROM achieve a "
			           + "LEFT JOIN achieve b ON a.achieve_no = b.achieve_no "
			           + "WHERE a.user_no = ? "
			           + "GROUP BY a.achieve_no, a.user_no, a.get_date";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 보여주는 결과가 있다면
				achieveList = new ArrayList<AchieveDTO>();
				do {
					AchieveDTO achieve = makeAchieveFormResult();
					achieveList.add(achieve);
				} while (rs.next());
				System.out.println(rs);
			}
			System.out.println("getAchieve()호출됨=" + achieveList.size());
		} catch (Exception e) {
			System.out.println("getAchieve() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return achieveList;
	}
	// 도전과제 달성처리
	public int insertAchieve(int idnum, int achieveNo) {
		int result;
		try {
			// oracle 11g에서 update문을 left join할수없으므로 각테이블마다 실행함
			con = pool.getConnection();
			sql = "insert into achieve values (?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, achieveNo);
			pstmt.setInt(2, idnum);
			result = pstmt.executeUpdate();
			System.out.println("insertAchieve() 호출됨");
		} catch (Exception e) {
			result = 0;
			System.out.println("insertAchieve() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}
}
