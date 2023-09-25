package model.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.DBConnectionMgr;

public class ReviewDAO {
	private DBConnectionMgr pool = null;
	// 공통
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;// select
	private String sql = "";// 실행시킬 SQL구문

	// 2.생성자를 통해서 연결
	public ReviewDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
			System.out.println("pool=>" + pool);
		} catch (Exception e) {
			System.out.println("DB접속 오류=>" + e);
		}
	}

	// ReviewDTO생성
	private ReviewDTO makeReviewFormResult() throws Exception {
		ReviewDTO review = new ReviewDTO();
		// review.setMovNo(rs.getInt("mov_no")); 실제 사용되지 않을예정이므로 주석처리
		review.setReview_no(rs.getInt("review_no"));
		review.setReviewContent(rs.getString("review_content"));
		review.setReviewDate(rs.getDate("review_date"));
		review.setRecommCount(rs.getInt("review_count"));
		review.setMovTitle(rs.getString("mov_title"));
		review.setUserNickname(rs.getString("user_nickname"));
		review.setUserPic(rs.getString("user_pic"));
		review.setMovGrade(rs.getFloat("grade_point"));
		return review;
	}

	// ReviewGenreDTO생성
	private ReviewGenreDTO makeReviewGenreFormResult() throws Exception {
		ReviewGenreDTO review = new ReviewGenreDTO();
		// review.setMovNo(rs.getInt("mov_no")); 실제 사용되지 않을예정이므로 주석처리
		review.setGenreName(rs.getString("genre_name"));
		review.setSumGradePoint(rs.getInt("sum_grade_point"));
		review.setCountGenreNo(rs.getInt("count_genre_No"));
		review.setAvgGradePoint(rs.getInt("avg_grade_point"));
		return review;
	}

	// 리뷰 통계
	public List<Float> getReviewSummary(int idnum) {
		List<Float> reviewSummaryList = new ArrayList<>();
		try {
			con = pool.getConnection();
			sql = "select grade_point from grade where user_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					float ReviewSummary = rs.getFloat("grade_point");
					reviewSummaryList.add(ReviewSummary);
				} while (rs.next());
			}
			System.out.println("getReviewSummary() 호출됨=" + reviewSummaryList.size());
		} catch (Exception e) {
			System.out.println("getReviewSummary() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return reviewSummaryList;
	}

	// 리뷰장르 통계
	public List<ReviewGenreDTO> getReviewGenreSummary(int idnum) {
		List<ReviewGenreDTO> reviewGenreSummaryList = new ArrayList<>();
		try {
			con = pool.getConnection();
			StringBuffer sqlbuf = new StringBuffer();
			sqlbuf.append(
					"SELECT g.genre_name, SUM(gg.grade_point) AS sum_grade_point, COUNT(mg.genre_no) AS count_genre_no,");
			sqlbuf.append("(SUM(gg.grade_point) / COUNT(mg.genre_no)) AS avg_grade_point FROM review r ");
			sqlbuf.append("JOIN grade gg ON r.user_no = gg.user_no AND r.mov_no = gg.mov_no ");
			sqlbuf.append("JOIN movie_genre mg ON r.mov_no = mg.mov_no JOIN genre g ON mg.genre_no = g.genre_no ");
			sqlbuf.append("WHERE r.user_no = ? GROUP BY g.genre_name ORDER BY avg_grade_point DESC");
			sql = sqlbuf.toString();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 보여주는 결과가 있다면
				do {
					ReviewGenreDTO ReviewGenre = makeReviewGenreFormResult();
					reviewGenreSummaryList.add(ReviewGenre);
				} while (rs.next());
				System.out.println(rs);
			}
			System.out.println("getReviewGenreSummary() 호출됨=" + reviewGenreSummaryList.size());
		} catch (Exception e) {
			System.out.println("getReviewGenreSummary() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return reviewGenreSummaryList;
	}

	// 작성 리뷰 가져오기(페이징 처리)
	public List<ReviewDTO> getReview(int idnum, int startNum, int endNum) {
		List<ReviewDTO> reviewList = new ArrayList<>();
		try {
			con = pool.getConnection();
			StringBuffer sqlbuf = new StringBuffer();
			sqlbuf.append("SELECT * FROM (");
			sqlbuf.append("SELECT r.review_no, r.review_content,r.review_date,rc.review_count,m.mov_title,mem.user_nickname,mem.user_pic,");
			sqlbuf.append(
					"(SELECT g.grade_point FROM grade g WHERE m.mov_no = g.mov_no AND r.user_no = g.user_no) AS grade_point,ROWNUM as row_num ");
			sqlbuf.append("FROM (SELECT * FROM review WHERE user_no=?) r ");
			sqlbuf.append(
					"LEFT JOIN (SELECT rr.review_no, COUNT(rr.review_no) as review_count FROM review_recomm rr GROUP BY rr.review_no) rc ON r.review_no = rc.review_no ");
			sqlbuf.append("LEFT JOIN movie m ON r.mov_no = m.mov_no LEFT JOIN member mem ON mem.user_no = r.user_no)");
			sqlbuf.append("WHERE row_num BETWEEN ? AND ?");
			sql = sqlbuf.toString();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 보여주는 결과가 있다면
				do {
					ReviewDTO review = makeReviewFormResult();
					reviewList.add(review);
				} while (rs.next());
				System.out.println(rs);
			}
			System.out.println("getReview()페이징 호출됨=" + reviewList.size());
		} catch (Exception e) {
			System.out.println("getReview()페이징 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return reviewList;
	}

	// 검색된 작성 리뷰 가져오기(오버로딩)
	public List<ReviewDTO> getReview(int idnum, int startNum, int endNum, String query) {
		List<ReviewDTO> reviewList = new ArrayList<>();
		try {
			con = pool.getConnection();
			StringBuffer sqlbuf = new StringBuffer();
			sqlbuf.append("SELECT * FROM (");
			sqlbuf.append("SELECT r.review_no, r.review_content,r.review_date,rc.review_count,m.mov_title,mem.user_nickname,mem.user_pic,");
			sqlbuf.append(
					"(SELECT g.grade_point FROM grade g WHERE m.mov_no = g.mov_no AND r.user_no = g.user_no) AS grade_point, ROWNUM as row_num ");
			sqlbuf.append("FROM (SELECT * FROM review WHERE user_no=?) r ");
			sqlbuf.append(
					"LEFT JOIN (SELECT rr.review_no, COUNT(rr.review_no) as review_count FROM review_recomm rr GROUP BY rr.review_no) rc ON r.review_no = rc.review_no ");
			sqlbuf.append("LEFT JOIN movie m ON r.mov_no = m.mov_no LEFT JOIN member mem ON mem.user_no = r.user_no ");
			sqlbuf.append(
					"WHERE m.mov_title LIKE ? OR TO_CHAR(r.review_date,'YYYY.MM.DD') LIKE ? OR r.review_content LIKE ?)");
			sqlbuf.append("AND WHERE row_num BETWEEN ? AND ?");
			sql = sqlbuf.toString();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			pstmt.setString(2, "%" + query + "%");
			pstmt.setString(3, "%" + query + "%");
			pstmt.setString(4, "%" + query + "%");
			pstmt.setInt(5, startNum);
			pstmt.setInt(6, endNum);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 보여주는 결과가 있다면
				reviewList = new ArrayList<ReviewDTO>();
				do {
					ReviewDTO review = makeReviewFormResult();
					reviewList.add(review);
				} while (rs.next());
				System.out.println("쿼리실행됨" + rs);
			}
			System.out.println("getReview()검색 호출됨=" + reviewList.size());
		} catch (Exception e) {
			System.out.println("getReview()검색 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return reviewList;
	}

	// 모든 작성 리뷰 가져오기(오버로딩)
	public List<ReviewDTO> getReview(int idnum) {
		List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
		try {
			con = pool.getConnection();
			StringBuffer sqlbuf = new StringBuffer();
			sqlbuf.append("SELECT * FROM (");
			sqlbuf.append("SELECT r.review_no, r.review_content,r.review_date,rc.review_count,m.mov_title,mem.user_nickname,mem.user_pic,");
			sqlbuf.append(
					"(SELECT g.grade_point FROM grade g WHERE m.mov_no = g.mov_no AND r.user_no = g.user_no) AS grade_point ");
			sqlbuf.append("FROM (SELECT * FROM review WHERE user_no=?) r ");
			sqlbuf.append(
					"LEFT JOIN (SELECT rr.review_no, COUNT(rr.review_no) as review_count FROM review_recomm rr GROUP BY rr.review_no) rc ON r.review_no = rc.review_no ");
			sqlbuf.append("LEFT JOIN movie m ON r.mov_no = m.mov_no LEFT JOIN member mem ON mem.user_no = r.user_no)");
			sql = sqlbuf.toString();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 보여주는 결과가 있다면
				do {
					ReviewDTO review = makeReviewFormResult();
					reviewList.add(review);
				} while (rs.next());
				System.out.println("쿼리실행됨" + rs);
			}
			System.out.println("getReview()총리뷰 호출됨=" + reviewList.size());
		} catch (Exception e) {
			System.out.println("getReview()총리뷰 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return reviewList;
	}

	// 총 추천받은 갯수가져오기
	public Integer getReviewRecommCount(int idnum) {
		Integer ReviewRecommCount = 0;
		try {
			con = pool.getConnection();
			StringBuffer sqlbuf = new StringBuffer();
			sqlbuf.append("select count(r.review_no) from review r ");
			sqlbuf.append("left join review_recomm r_r on ");
			sqlbuf.append("r_r.review_no=r.review_no where r.review_no=?");
			sql = sqlbuf.toString();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 보여주는 결과가 있다면
				do {
					ReviewRecommCount = rs.getInt("count(r.review_no)");
				} while (rs.next());
				System.out.println("쿼리실행됨" + rs);
			}
			System.out.println("getReviewRecommCount() 호출됨" + ReviewRecommCount);
		} catch (Exception e) {
			System.out.println("getReviewRecommCount() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return ReviewRecommCount;
	}

	// 총 추천받은 갯수가져오기
	public Integer getReviewCount(int idnum) {
		Integer ReviewRecommCount = 0;
		try {
			con = pool.getConnection();
			sql = "select count(*) from review where user_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idnum);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 보여주는 결과가 있다면
				do {
					ReviewRecommCount = rs.getInt("count(*)");
				} while (rs.next());
				System.out.println("쿼리실행됨" + rs);
			}
			System.out.println("getReviewCount() 호출됨" + ReviewRecommCount);
		} catch (Exception e) {
			System.out.println("getReviewCount() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return ReviewRecommCount;
	}
}
