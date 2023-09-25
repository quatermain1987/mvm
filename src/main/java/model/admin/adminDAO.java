package model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.DBConnectionMgr;

public class adminDAO {

	private DBConnectionMgr pool = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	public adminDAO() {
		// TODO Auto-generated constructor stub
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public int QnaCount() {
		int count = 0;
		try {
			con = pool.getConnection();
			sql = "SELECT COUNT(*) FROM qna WHERE TO_CHAR(qna_date, 'YY/MM/DD') = TO_CHAR(SYSDATE, 'YY/MM/DD')";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getArticleCount() execute error : " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return count;
	}

	public int ReportCount() {
		int count = 0;
		try {
			con = pool.getConnection();
			sql = "SELECT COUNT(*) FROM report WHERE TO_CHAR(report_date, 'YY/MM/DD') = TO_CHAR(SYSDATE, 'YY/MM/DD')";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getArticleCount() execute error : " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return count;
	}

	private adminDTO makeArticleQna() throws Exception {
		adminDTO article = new adminDTO();
		article.setQna_no(rs.getInt("qna_no"));
		article.setQna_subject(rs.getString("qna_subject"));
		article.setUser_no(rs.getInt("user_no"));
		article.setUser_name(rs.getString("user_name"));
		article.setUser_nickname(rs.getString("user_nickname"));
		return article;
	}

	private adminDTO makeArticleReport() throws Exception {
		adminDTO article = new adminDTO();
		article.setReport_no(rs.getInt("report_no"));
		article.setReview_no(rs.getInt("review_no"));
		article.setReport_option(rs.getInt("report_option"));
		article.setUser_no(rs.getInt("user_no"));
		article.setUser_name(rs.getString("user_name"));
		article.setUser_nickname(rs.getString("user_nickname"));
		return article;
	}

	public List getQnaArticles() {
		List articleList = null;
		try {
			con = pool.getConnection();
			sql = "select * from (select rownum as rnum,x.* from "
					+ "(select a.*, b.user_name, b.user_nickname from qna a, member b "
					+ "WHERE a.user_no = b.user_no and TO_CHAR(qna_date, 'YY/MM/DD') = TO_CHAR(SYSDATE, 'YY/MM/DD') "
					+ "order by qna_no desc) x) where rnum between 1 and 5";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				articleList = new ArrayList(5);
				do {
					adminDTO article = makeArticleQna();
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getQnaArticles() execute error : " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	public List getReportArticles() {
		List articleList = null;
		try {
			con = pool.getConnection();
			sql = "select * from (select rownum as rnum,x.* from "
					+ "(select a.*, b.user_name, b.user_nickname from report a, member b "
					+ "WHERE a.user_no = b.user_no and TO_CHAR(report_date, 'YY/MM/DD') = TO_CHAR(SYSDATE, 'YY/MM/DD') "
					+ "order by report_no desc) x) where rnum between 1 and 5";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				articleList = new ArrayList(5);
				do {
					adminDTO article = makeArticleReport();
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getReportArticles() execute error : " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	public int getRegCount() {
		int count = 0;
		try {
			con = pool.getConnection();
			sql = "select count(*) from member where to_char(user_regdate, 'YY/MM/DD') = to_char(sysdate, 'YY/MM/DD')";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getRegCount() execute error : " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return count;
	}
	
	public int getRejectCount() {
		int count = 0;
		try {
			con = pool.getConnection();
			sql = "select count(*) from reject where to_char(reject_date, 'YY/MM/DD') = to_char(sysdate, 'YY/MM/DD')";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getRejectCount() execute error : " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return count;
	}
	
	public int documentCount() {
		int reviewCount =0;
		int replyCount = 0;
		try {
			con = pool.getConnection();
			sql = "select count(*) from review where to_char(review_date, 'YY/MM/DD') = to_char(sysdate, 'YY/MM/DD')";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reviewCount = rs.getInt(1);
			}
			sql = "select count(*) from reply where to_char(reply_date, 'YY/MM/DD') = to_char(sysdate, 'YY/MM/DD')";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				replyCount = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("documentCount() error : "+e);
		}
		return reviewCount+replyCount;
	}
	
}
