package model.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import model.DBConnectionMgr;

public class reportDAO {

	private DBConnectionMgr pool = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	public reportDAO() {
		// TODO Auto-generated constructor stub
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public int getArticleCount() {
		int count = 0;
		try {
			con = pool.getConnection();
			sql = "select count(*) from report";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getArticleCount() execute error : "+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return count;
	}
	
	private reportDTO makeArticleFromResult() throws Exception{
		reportDTO article = new reportDTO();
		article.setReport_no(rs.getInt("report_no"));
		article.setUser_no(rs.getInt("user_no"));
		article.setReply_no(rs.getInt("reply_no"));
		article.setReview_no(rs.getInt("review_no"));
		article.setReport_option(rs.getInt("report_option"));
		article.setReport_date(rs.getTimestamp("report_date"));
		article.setReport_content(rs.getString("report_content"));
		article.setReport_condition(rs.getInt("report_condition"));
		article.setReport_type(rs.getInt("report_type"));
		article.setUser_name(rs.getString("user_name"));
		article.setUser_nickname(rs.getString("user_nickname"));
		return article;
	}
	
	private int newPrimary() {
		int newPrimary = 1;
		try {
			con = pool.getConnection();
			sql = "select max(report_no) from report";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				newPrimary = rs.getInt(1) + 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("newPrimary error : " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return newPrimary;
	}
	
	public int getArticleSearchCount(String search, String searchtext) {
		int x = 0; // 총 레코드 수
		try {
			con = pool.getConnection();
			if (search==null || search=="") {
				sql = "select count(*) from report a, member b where a.user_no = b.user_no";
			}else if (search.equals("user_name")) {
				sql = "select count(*) from report a, member b where a.user_no = b.user_no and b.user_name like '%"+searchtext+"%'";
			}else if (search.equals("user_nickname")){
				sql = "select count(*) from report a, member b where a.user_no = b.user_no and b.user_nickname like '%"+searchtext+"%'";
			}else if (search.equals("condition_1")){
				sql = "select count(*) from report a, member b where a.user_no = b.user_no and a.report_condition = 1"; // search text > 1
			}else if (search.equals("condition_2")){
				sql = "select count(*) from report a, member b where a.user_no = b.user_no and a.report_condition = 2"; // search text > 2
			}
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getArticleSearchCount() execute error : "+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	
	public Hashtable pageList(String pageNum, int count) {
		Hashtable<String, Integer> pgList = new Hashtable<String, Integer>();
		int pageSize = 10; // numPerPage
		int blockSize = 10; // pagePerBlock
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1; // 2page > (2-1)*10+1=record11
		int endRow = currentPage * pageSize; // 2page > 2*10 = record20
		int number = count - (currentPage - 1) * pageSize; // 페이지별 첫번째 게시물 번호
		int pageCount = count/pageSize+(count%pageSize==0?0:1);
		
		// 시작 페이지
		int startPage = 0;
		if(currentPage%blockSize!=0){
			startPage = currentPage/blockSize*blockSize+1;
		}else{
			startPage = ((currentPage/blockSize)-1)*blockSize+1;
		}

		int endPage = startPage+blockSize-1;

		// 블럭별 구분후 링크출력
		if(endPage > pageCount){
			endPage=pageCount;
		}
		pgList.put("pageSize", pageSize);
		pgList.put("blockSize", blockSize);
		pgList.put("currentPage", currentPage);
		pgList.put("startRow", startRow);
		pgList.put("endRow", endRow);
		pgList.put("number", number);
		pgList.put("pageCount", pageCount);
		pgList.put("startPage", startPage);
		pgList.put("endPage", endPage);
		pgList.put("count", count);
		
		return pgList;
	}
	
	public List getBoardArticles(int start, int end, String search, String searchtext) {
		List articleList = null; // ArrayList<Board> articleList = null;
		try {
			con = pool.getConnection();
			if (search==null || search=="") {
				sql = "select * from (select rownum as rnum,x.* from (select a.*, b.user_nickname, b.user_name from report a, member b ";
				sql = sql+"where a.user_no=b.user_no order by report_no desc) x)where rnum between ? and ?";
			}else if (search.equals("user_name")) {
				sql = "select * from (select rownum as rnum,x.* from (select a.*, b.user_nickname, b.user_name from report a, member b ";
				sql = sql+"where a.user_no=b.user_no and user_name like '%"+searchtext+"%' order by report_no desc) x)where rnum between ? and ?";
			}else if (search.equals("user_nickname")) {
				sql = "select * from (select rownum as rnum,x.* from (select a.*, b.user_nickname, b.user_name from report a, member b ";
				sql = sql+"where a.user_no=b.user_no and user_nickname like '%"+searchtext+"%' order by report_no desc) x)where rnum between ? and ?";
			}else if (search.equals("condition_1")) {
				sql = "select * from (select rownum as rnum,x.* from (select a.*, b.user_nickname, b.user_name from report a, member b ";
				sql = sql+"where a.user_no=b.user_no and report_condition=1 order by report_no desc) x)where rnum between ? and ?";
			}else if (search.equals("condition_2")) {
				sql = "select * from (select rownum as rnum,x.* from (select a.*, b.user_nickname, b.user_name from report a, member b ";
				sql = sql+"where a.user_no=b.user_no and report_condition=2 order by report_no desc) x)where rnum between ? and ?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			System.out.println("getBoardArticle() sql : "+sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				articleList = new ArrayList(end);
				do {
					reportDTO article = makeArticleFromResult();
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getBoardArticles() execute error : "+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}
	
	public void insertArticle(reportDTO article) {
		int primaryKey = newPrimary();
		try {
			con = pool.getConnection();
			sql = "insert into report(report_no, user_no, reply_no, review_no, report_option, report_date, report_content, report_type)"
					+ " values(?, ?, ?, ?, ?, sysdate, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, primaryKey);
			pstmt.setInt(2, article.getUser_no());
			System.out.println("reply number : "+article.getReply_no());
			pstmt.setInt(3, article.getReply_no());
			pstmt.setInt(4, article.getReview_no());
			pstmt.setInt(5, article.getReport_option());
			pstmt.setString(6, article.getReport_content());
			pstmt.setInt(7, article.getReport_type());
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insertArticle() execute error : "+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
	public void reportCondition(int report_no) {
		try {
			con = pool.getConnection();
			sql = "update report set report_condition=2 where report_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, report_no);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("컨디션 없데이트 성공");
			}else if (result == 0) {
				System.out.println("컨디션 없데이트 실패");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("reportCondition() execute error : "+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
	public void reviewDelete(int review_no) {
		try {
			con = pool.getConnection();
			sql = "delete from review where review_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_no);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("신고리뷰 삭제 성공");
			}else if (result == 0) {
				System.out.println("신고리뷰 삭제 실패");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("reviewDelete() execute error : "+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
	public void replyDelete(int reply_no) {
		try {
			con = pool.getConnection();
			sql = "delete from reply where reply_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reply_no);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("신고리뷰 삭제 성공");
			}else if (result == 0) {
				System.out.println("신고리뷰 삭제 실패");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("reviewDelete() execute error : "+e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
}
