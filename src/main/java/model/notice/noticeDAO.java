package model.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import model.DBConnectionMgr;

public class noticeDAO {

	private DBConnectionMgr pool = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	public noticeDAO() {
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
			sql = "select count(*) from notice";
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

	private noticeDTO makeArticleFromResult() throws Exception {
		noticeDTO article = new noticeDTO();
		article.setNotice_no(rs.getInt("notice_no"));
		article.setNotice_subject(rs.getString("notice_subject"));
		article.setNotice_content(rs.getString("notice_content"));
		article.setNotice_date(rs.getTimestamp("notice_date"));
		return article;
	}

	private int newPrimary() {
		int newPrimary = 1;
		try {
			con = pool.getConnection();
			sql = "select max(notice_no) from notice";
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
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);

		// 시작 페이지
		int startPage = 0;
		if (currentPage % blockSize != 0) {
			startPage = currentPage / blockSize * blockSize + 1;
		} else {
			startPage = ((currentPage / blockSize) - 1) * blockSize + 1;
		}

		int endPage = startPage + blockSize - 1;

		// 블럭별 구분후 링크출력
		if (endPage > pageCount) {
			endPage = pageCount;
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

	public boolean pageCheck(int num) {
		boolean check = false;
		try {
			con = pool.getConnection();
			sql = "select count(*) from notice where notice_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			rs.next();
			if (rs.getInt(1) == 0) {
				check = false;
			}else {
				check = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("pageCheck error : "+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return check;
	}
	
	public List getBoardArticles(int start, int end) {
		List articleList = null;
		try {
			con = pool.getConnection();
			sql = "select * from (select rownum as rnum,x.* from (select * from notice order by notice_no desc) x) where rnum between ? and ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				articleList = new ArrayList(end);
				do {
					noticeDTO article = makeArticleFromResult();
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getBoardArticles() execute error : " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	public void insertArticle(noticeDTO article) {
		int primaryKey = newPrimary();
		try {
			con = pool.getConnection();
			con.setAutoCommit(false);// 트랜잭션 처리
			sql = "insert into notice (notice_no, notice_subject, notice_content, notice_date) values (?, ?, ?, sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, primaryKey);
			pstmt.setString(2, article.getNotice_subject());
			pstmt.setString(3, article.getNotice_content());
			int insert = pstmt.executeUpdate();
			if (insert == 1) {
				System.out.println("게시물 등록 성공");
			} else {
				System.out.println("게시물 등록 실패");
			}
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insertArticle() execute error : " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	public noticeDTO getArticle(int num) {
		noticeDTO article = null;
		try {
			con = pool.getConnection();
			sql = "select * from notice where notice_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = makeArticleFromResult();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getArticle() execute error : " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}

	public void updateArticle(noticeDTO article) {
		try {
			con = pool.getConnection();
			sql = "update notice set notice_subject=?, notice_content=? where notice_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getNotice_subject());
			pstmt.setString(2, article.getNotice_content());
			pstmt.setInt(3, article.getNotice_no());
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("updateArticle() execute error : " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	public void deleteArticle(int num) {
		try {
			con = pool.getConnection();
			sql = "delete from notice where notice_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("deleteArticle() execute error : " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

}
