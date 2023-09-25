package model.detail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import model.DBConnectionMgr;


public class DetailDAO {

		//1.연결할 클래스 객체선언
			private DBConnectionMgr pool=null;
			//공통
			private Connection con=null;
			private PreparedStatement pstmt=null;
			private ResultSet rs=null;//select는 반환값이 있기 때문에 그 반환값을 담기 위함
			private String sql=""; //실행시킬 sql구문 저장용
			
			//2. 생성자를 통해서 서로 연결
			public DetailDAO() {
				try {
					pool=DBConnectionMgr.getInstance(); //DB연결얻어오기
				}catch(Exception e) {
					System.out.println("DB접속 오류=>"+e);
				}
			}
			
			private int movieGenrePrimary() {
				int newPrimary = 1;
				try {
					con = pool.getConnection();
					sql = "select max(movie_genre_no) from movie_genre";
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
			
			private int reviewPrimary() {
				int newPrimary = 1;
				try {
					con = pool.getConnection();
					sql = "select max(review_no) from review";
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
			
			private int gradePrimary() {
				int newPrimary = 1;
				try {
					con = pool.getConnection();
					sql = "select max(grade_no) from grade";
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
			
			//특정영화의 총 리뷰갯수 구하기 
			public int getReviewCount(int mov_no){
				int x=0;
				try {
					con=pool.getConnection();
					System.out.println("con=>"+con);//DBConnectionMgr
					sql="select count(*) from review where mov_no=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,mov_no);
					rs=pstmt.executeQuery();
					if(rs.next()) {//보여주는 결과가 있다면
						x=rs.getInt(1);//변수명=rs.get자료형(필드명 또는 인덱스명) 
					}
				}catch(Exception e) {
					System.out.println("getReviewCount() 에러발생=>"+e);
				}finally {
					pool.freeConnection(con, pstmt, rs);
				}
				return x;
			}

			//내리뷰 출력
			
			public ReviewDTO getMyReview(int mov_no, int user_no) {
			    ReviewDTO review = null;
			    try {
			        con = pool.getConnection();
			        sql = "select * from review where mov_no=? and user_no = ?";
			        pstmt = con.prepareStatement(sql);
			        pstmt.setInt(1, mov_no);
			        pstmt.setInt(2, user_no);
			        rs = pstmt.executeQuery();
			        if (rs.next()) {
			            review = new ReviewDTO();
			            review.setReview_no(rs.getInt("review_no"));
			            review.setUser_no(rs.getInt("user_no"));
			            review.setMov_no(rs.getInt("mov_no"));
			            review.setReview_content(rs.getString("review_content"));
			        }
			    } catch (Exception e) {
			        System.out.println("getMyReview() 에러 발생 => " + e);
			    } finally {
			        pool.freeConnection(con, pstmt, rs);
			    }
			    return review;
			}
			
			//장르저장하기
			public void insertGenres(MovieGenreDTO genres) { 
				//1.장르가 저장되어있는지 확인
				int mov_no=genres.getMov_no();
				int primary = movieGenrePrimary();
				try {
					con=pool.getConnection();
					sql="select * from movie_genre where mov_no=? and genre_no=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, mov_no);
					pstmt.setInt(2, genres.getGenre_no());
					rs=pstmt.executeQuery();
					if(rs.next()) {
						System.out.println("이미 장르가 있는 영화");
					}else {//데이터가 없는경우->0
					con.setAutoCommit(false);
					sql="insert into movie_genre (movie_genre_no,mov_no,genre_no) values (?,?,?)";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, primary);
					pstmt.setInt(2, mov_no);
					pstmt.setInt(3, genres.getGenre_no());
					int insert=pstmt.executeUpdate();
					System.out.println("장르입력유무(insert)=>"+insert);
					con.commit();
					}	
				}catch(Exception e) {
					System.out.println("insertGenres() 에러발생=>"+e);
				}finally{
					pool.freeConnection(con, pstmt, rs);
				}
			}
			
			//영화이름 저장하기
			public void insertMovie(MovieDTO movie) { 
				//1.영화가 저장되어있는지 확인
				try {
					con=pool.getConnection();
					sql="select * from movie where mov_no=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,movie.getMov_no());
					rs=pstmt.executeQuery();
					if(rs.next()) {
						System.out.println("이미 제목이 있는 영화");
					}else {//데이터가 없는경우->0
					con.setAutoCommit(false);
					sql="insert into movie (mov_no,mov_title) values (?,?)";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, movie.getMov_no());
					pstmt.setString(2, movie.getMov_title());
					int insert=pstmt.executeUpdate();
					con.commit();
					System.out.println("영화입력유무(insert)=>"+insert);
					}	
				}catch(Exception e) {
					System.out.println("insertGenres() 에러발생=>"+e);
				}finally{
					pool.freeConnection(con, pstmt, rs);
				}
			}

			//리뷰쓰기 
			public void insertReview(ReviewDTO review, MovieDTO movie) { 
				//1.신규글인지 확인
				int user_no=review.getUser_no();
				int mov_no=review.getMov_no();
				int primary = reviewPrimary();
				try {
					con=pool.getConnection();
					sql="select * from review where mov_no=? and user_no = ?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,mov_no);
					pstmt.setInt(2,user_no);
					rs=pstmt.executeQuery();
					if(rs.next()) {
						System.out.println("이미입력한유저");
					}else {//데이터가 없는경우->0
					insertMovie(movie);
					con.setAutoCommit(false);
					sql="insert into review (review_no,user_no,mov_no,review_content,review_date,recomm_count) values (?,?,?,?,sysdate,0)";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, primary);
					pstmt.setInt(2, user_no);
					pstmt.setInt(3, mov_no);
					pstmt.setString(4, review.getReview_content());
					int insert=pstmt.executeUpdate();
					System.out.println("리뷰입력(insert)=>"+insert);
					con.commit();
					}
				}catch(Exception e) {
					System.out.println("insertArticle() 에러발생=>"+e);
				}finally{
					pool.freeConnection(con, pstmt, rs);
				}
			}

			//내리뷰삭제
			
			public int deleteReview(int review_no, int user_no) {
				System.out.println("deleteReview review_no=>"+review_no+"user_no="+user_no);
				int x=-1; //게시물의 삭제성공유무
				try {
					con = pool.getConnection();
					sql = "select * from review where review_no=? and user_no=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, review_no);
					pstmt.setInt(2, user_no);
					rs = pstmt.executeQuery();
					if (rs.next()) {
							con.setAutoCommit(false);
							sql = "delete from review where review_no=? and user_no=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, review_no);
							pstmt.setInt(2, user_no);
							int delete = pstmt.executeUpdate();
							con.commit();
							System.out.println("글 삭제 성공유무(delete)=>" + delete);
							x=1;//삭제 성공
					} else {
						System.out.println("등록된 데이터가 없음");
						x=0;
				       }
				}catch(Exception e) {
					System.out.println("deleteArticle() 에러발생=>"+e);
				}finally {
					pool.freeConnection(con, pstmt, rs);
				}
				return x;
			}		
			
			//내리뷰수정
			
			public void updateReview(ReviewDTO review) {
				try {
					con = pool.getConnection();
					sql = "select * from review where mov_no=? and user_no=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, review.getMov_no());
					pstmt.setInt(2, review.getUser_no());
					rs = pstmt.executeQuery();
					if (rs.next()) {
						con.setAutoCommit(false);
						sql="update review set review_content=? where mov_no=? and user_no=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setString(1, review.getReview_content());
						pstmt.setInt(2, review.getMov_no());
						pstmt.setInt(3, review.getUser_no());
						   int update = pstmt.executeUpdate();
						   con.commit();
						    System.out.println("글 수정 성공유무(update)=>" + update);
						} else {
							System.out.println("등록된 데이터가 없음");
						}
				} catch (Exception e) {
					System.out.println("updateArticle() 에러발생=>" + e);
				} finally {
					pool.freeConnection(con, pstmt, rs);
				}
			}
			
			//리뷰목록보기(상세페이지)
			public List getDetailReview(int mov_no) {
				List reviewList=null; 
				try {
					con=pool.getConnection();
					sql="select r.review_no,r.user_no,r.mov_no,r.review_content,r.review_date,r.recomm_count,m.user_nickname,m.user_pic,g.grade_point from review r left join grade g\n"
							+ "on (r.mov_no = g.mov_no and r.user_no = g.user_no) left join member m\n"
							+ "on (r.user_no=m.user_no)\n"
							+ "where r.mov_no=?\n"
							+ "order by recomm_count desc";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, mov_no);
					rs=pstmt.executeQuery();
					if(rs.next()) {//화면에 보여줄 데이터가 있으면
						reviewList=new ArrayList(6); //end갯수만큼 공간을 생성해라
						do {
							ReviewJoinDTO review=new ReviewJoinDTO();
							review.setReview_no(rs.getInt("review_no")); 
							review.setUser_no(rs.getInt("user_no"));
							review.setMov_no(rs.getInt("mov_no"));
							review.setReview_content(rs.getString("review_content"));
							review.setReview_date(rs.getTimestamp("review_date"));
							review.setRecomm_count(rs.getInt("recomm_count"));
							review.setGrade_point(rs.getDouble("grade_point"));
							review.setUser_nickname(rs.getString("user_nickname"));
							review.setUser_pic(rs.getString("user_pic"));
							//추가
							reviewList.add(review);//생략하면 데이터 저장X => for문X(Null)
						}while(rs.next());//더 있으면 계속
					}
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("getDetailReview() 에러발생=>"+e);
				}finally {
					pool.freeConnection(con, pstmt, rs);
				}
				return reviewList; //list.jsp에서 반환 -> for문 테이블에 출력
			}
	
		
			//선택에 따른 리뷰목록 불러오기
			public List getSelectedReviews(String select,int mov_no,int end,int start) {
				System.out.println("getSelectedReviews의"+"select"+select+"mov"+mov_no+"end"+end+"start"+start);
					List reviewList=null;
				try {
					con=pool.getConnection();
					System.out.println("con=>"+con);//DBConnectionMgr
					//-------------------------------------------------
					if(select == null || select.equals("fastest")  ) { //선택되지않은경우 
						sql="SELECT * FROM ("
								+ "  SELECT ROWNUM rnum, a.*"
								+ "  FROM ("
								+ "    SELECT r.review_no,r.user_no,r.mov_no,r.review_content,r.review_date,r.recomm_count,m.user_nickname,m.user_pic,g.grade_point"
								+ "    FROM review r"
								+ "    LEFT JOIN grade g ON r.mov_no = g.mov_no AND r.user_no = g.user_no"
								+ "    LEFT JOIN member m ON r.user_no = m.user_no"
								+ "    WHERE r.mov_no=?"
								+ "    ORDER BY r.review_date DESC"
								+ "  ) a"
								+ "  WHERE ROWNUM <=?"
								+ ")"
								+ "WHERE rnum >=?";
					}else if (select.equals("popular")) { //리뷰 추천 많은 순
						sql = "SELECT * FROM (SELECT ROWNUM rnum, a.* FROM ("
						        + "SELECT r.review_no, r.user_no, r.mov_no, r.review_content, r.review_date, r.recomm_count, m.user_nickname,m.user_pic,g.grade_point "
						        + "FROM review r "
						        + "LEFT JOIN grade g ON r.mov_no = g.mov_no AND r.user_no = g.user_no "
						        + "LEFT JOIN member m ON r.user_no = m.user_no "
						        + "WHERE r.mov_no=?"
						        + "ORDER BY r.recomm_count asc "
						        + ") a) WHERE ROWNUM <= ? AND rnum >= ?";
					} else if (select.equals("grade")) { //별점 순
						sql = "SELECT * FROM (SELECT ROWNUM rnum, a.* FROM ("
						        + "SELECT r.review_no, r.user_no, r.mov_no, r.review_content, r.review_date, r.recomm_count, m.user_nickname,m.user_pic,g.grade_point "
						        + "FROM review r "
						        + "LEFT JOIN grade g ON r.mov_no = g.mov_no AND r.user_no = g.user_no "
						        + "LEFT JOIN member m ON r.user_no = m.user_no "
						        + "WHERE r.mov_no=? "
						        + "ORDER BY g.grade_point asc "
						        + ") a) WHERE ROWNUM <= ? AND rnum >= ?";
					}
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,mov_no);
					pstmt.setInt(2,end);
					pstmt.setInt(3,start);
					rs=pstmt.executeQuery();
					if(rs.next()) {//화면에 보여줄 데이터가 있으면
						reviewList=new ArrayList(end);		
						do { ReviewJoinDTO review=new ReviewJoinDTO();
							review.setReview_no(rs.getInt("review_no")); 
							review.setUser_no(rs.getInt("user_no"));
							review.setMov_no(rs.getInt("mov_no"));
							review.setReview_content(rs.getString("review_content"));
							review.setReview_date(rs.getTimestamp("review_date"));
							review.setRecomm_count(rs.getInt("recomm_count"));
							review.setGrade_point(rs.getDouble("grade_point"));
							review.setUser_nickname(rs.getString("user_nickname"));
							review.setUser_pic(rs.getString("user_pic"));
							//추가
							reviewList.add(review);//생략하면 데이터 저장X => for문X(Null)
						}while(rs.next());//더 있으면 계속
					}
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("getSelectedReviews() 에러발생=>"+e);
				}finally {
					pool.freeConnection(con, pstmt, rs);
				}
				return reviewList; 
			}
			
			//페이징처리
			
			public Hashtable pageList(String pageNum,int count) {
				
				//0.페이징처리결과를 저장할 Hashtable객체를 선언
				Hashtable<String,Integer> pgList=new Hashtable<String,Integer>();
				
				int pageSize=9; //numPerPage 페이지당 보여주는 게시물 수(=레코드 수)(default:10)
				int blockSize=3; //pagePerBlock 블럭 당 보여주는 페이지 수(default:10)
				
				if(pageNum==null){//처음에 1페이지는 내가 누를 수 없기 때문에 parameter값이 null
					pageNum="1";//무조건 default로 1페이지 설정
				}
				//nowPage(현재페이지(클릭해서 보고 있는 페이지))
				int currentPage = Integer.parseInt(pageNum); //if문 안에 넣으면 null인 경우에만 돌아가기 때문에 빼놓기
				//					(1-1)*10+1=1 레코드 번호가 1부터 시작, (2-1)*10+1=11, (3-1)*10+1=21 
				int startRow = (currentPage - 1) * pageSize + 1; //시작 레코드 번호	
				int endRow = currentPage * pageSize;//1*10=10, 2*10=20, 3*10=3
				

				System.out.println("현재 레코드 수(count)=>" + count);
				
				//1.총페이지수 구하기
				   //                    122/10=12.2+1.0=13.2=13,(122%10)=1
				   int pageCount=count/pageSize+(count%pageSize==0?0:1);
				   //2.시작페이지
				   int startPage=0;
				   if(currentPage%blockSize!=0){//1~9,11~19,21~29
				      startPage=currentPage/blockSize*blockSize+1;
				   }else{//10%10=0,(10,20,30,40~)
						             //((10/10)-1)*10+1=1, 20=>11
					  startPage=((currentPage/blockSize)-1)*blockSize+1; 
				   }
				   //종료페이지
				   int endPage=startPage+blockSize-1;//1+10-1=10,11+10-1=20
				   System.out.println
				    ("startPage=>"+startPage+",endPage="+endPage);
				   //블럭별로 구분해서 링크걸어서 출력
				   //     11     >          10      //마지막페이지=총페이지수
				   if(endPage > pageCount)  endPage=pageCount;
				   //페이징처리에 대한 계산결과=>hashtable에 저장->listAction전달
				   //->메모리에 저장->공유->list.jsp에서 불러다 사용
				   pgList.put("pageSize", pageSize);//<->pgList.get(키명)("pageSize")
			       pgList.put("blockSize", blockSize);
			       pgList.put("currentPage", currentPage);
			       pgList.put("startRow", startRow);
			       pgList.put("endRow", endRow);
			       pgList.put("count", count);
			       pgList.put("startPage", startPage);
			       pgList.put("endPage", endPage);
			       pgList.put("pageCount", pageCount);
			       
			       return pgList;
			}


			//유저 + 영화 보고싶어요 유무
			
			
			public int getHasWishList(int user_no,int mov_no){
				int x=0;
				try {
					con=pool.getConnection();
					System.out.println("con=>"+con);//DBConnectionMgr
					sql="select count(*) from wish where user_no=? and mov_no=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,user_no);
					pstmt.setInt(2,mov_no);
					rs=pstmt.executeQuery();
					if(rs.next()) {//보여주는 결과가 있다면
						x=rs.getInt(1);//변수명=rs.get자료형(필드명 또는 인덱스명) 
					}
				}catch(Exception e) {
					System.out.println("getReviewCount() 에러발생=>"+e);
				}finally {
					pool.freeConnection(con, pstmt, rs);
				}
				return x;
			}
			
			//보고싶어요 추가하기
			
			public void insertWish(int user_no,int mov_no, MovieDTO movie) { 
				int check=0;
				try {
					check=getHasWishList(user_no,mov_no);
					if(check > 0) {
						deleteWish(user_no,mov_no);
					}else {//데이터가 없는경우->0
					insertMovie(movie);
					con.setAutoCommit(false);
					sql="insert into wish (user_no, mov_no) values (?,?)";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, user_no);
					pstmt.setInt(2, mov_no);
					int insert=pstmt.executeUpdate();
					System.out.println("보고싶어요(insert)=>"+insert);
					con.commit();
					}
				}catch(Exception e) {
					System.out.println("보고싶어요() 에러발생=>"+e);
				}finally{
					pool.freeConnection(con, pstmt, rs);
				}
			}

			//보고싶어요 삭제
			public void deleteWish(int user_no,int mov_no) { 
				int check=0; 
				try {
					check=getHasWishList(user_no,mov_no);
						if(check > 0) { 
							con=pool.getConnection();
							con.setAutoCommit(false);
							sql="delete from wish where user_no=? and mov_no=?";
							pstmt=con.prepareStatement(sql);
							pstmt.setInt(1, user_no);
							pstmt.setInt(2, mov_no);
							int delete=pstmt.executeUpdate();
							con.commit();
					    System.out.println("보고싶어요 삭제 성공유무(delete)=>"+delete);
						}
						}catch(Exception e) {
						 System.out.println("DeleteArticle() 에러유발=>"+e);
					 }finally {
						pool.freeConnection(con, pstmt, rs);
					 } 
				}

			//유저의 총 보고싶어요 갯수
			public int getWishCount(int user_no){
				int x=0;
				try {
					con=pool.getConnection();
					System.out.println("con=>"+con);//DBConnectionMgr
					sql="select count(*) from wish where user_no=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,user_no);
					rs=pstmt.executeQuery();
					if(rs.next()) {//보여주는 결과가 있다면
						x=rs.getInt(1);//변수명=rs.get자료형(필드명 또는 인덱스명) 
					}
				}catch(Exception e) {
					System.out.println("getReviewCount() 에러발생=>"+e);
				}finally {
					pool.freeConnection(con, pstmt, rs);
				}
				return x;
			}
			
		
			//보고싶어요 보여주기
			
			//public List getWishList(int user_no, int start,int end) {
				public List getWishList(int user_no, int start) {
				List WishList=null;
				int count=0;
			try {
				count=getWishCount(user_no);
				con=pool.getConnection();
				//------------------------------------------------- 
				sql="SELECT * FROM ( SELECT w.*, ROWNUM AS rnum  FROM ( SELECT *  FROM wish WHERE user_no = ? ) w ) WHERE rnum BETWEEN ? AND ?";	
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,user_no);
				pstmt.setInt(2,start);
				pstmt.setInt(3,count); //보고싶어요 한 만큼 가져오기
				rs=pstmt.executeQuery();
				if(rs.next()) {//화면에 보여줄 데이터가 있으면
					WishList=new ArrayList(count); //end갯수만큼 공간을 생성해라
					do { WishDTO wish=new WishDTO();
						wish.setUser_no(user_no); 
						wish.setMov_no(rs.getInt("mov_no"));
						//추가
						WishList.add(wish);
					}while(rs.next());
				}
			}catch(Exception e) {
				System.out.println("getSelectedReviews() 에러발생=>"+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return WishList; 
		}
			
			//영화ID 평균 별점구하기
			
			public double averageRating (int mov_no){
				double x=0.0;
				try {
					con=pool.getConnection();
					System.out.println("con=>"+con);//DBConnectionMgr
					sql="select avg(grade_point) from grade where mov_no=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,mov_no);
					rs=pstmt.executeQuery();
					if(rs.next()) {
						x=rs.getDouble(1);
					}
				}catch(Exception e) {
					System.out.println("getReviewCount() 에러발생=>"+e);
				}finally {
					pool.freeConnection(con, pstmt, rs);
				}
				return x; //기본값을 넣을까
			}
			
			//영화 별점 각각 카운트
			
			public int countGradeByPoint(int mov_no, int grade_point) {
			    int count = 0;
			    try {
			        con = pool.getConnection();
			        sql = "select count(*) from grade where mov_no = ? and grade_point = ?";
			        pstmt = con.prepareStatement(sql);
			        pstmt.setInt(1, mov_no);
			        pstmt.setInt(2, grade_point);
			        rs = pstmt.executeQuery();
			        if (rs.next()) {
			            count = rs.getInt(1);
			        }
			    } catch (Exception e) {
			        System.out.println("countGradeByPoint() 에러 발생 => " + e);
			    } finally {
			        pool.freeConnection(con, pstmt, rs);
			    }
			    return count;
			}
			
			
			//유저 + 영화 별점 유무 및 유저 별점
			public int getRating (int user_no,int mov_no){
				System.out.println(user_no+mov_no+"=>getRating");
				int x=0;
				try {
					con=pool.getConnection();
					System.out.println("con=>"+con);//DBConnectionMgr
					sql="select grade_point from grade where user_no=? and mov_no=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,user_no);
					pstmt.setInt(2,mov_no);
					rs=pstmt.executeQuery();
					if(rs.next()) {//보여주는 결과가 있다면
						x=rs.getInt(1);//변수명=rs.get자료형(필드명 또는 인덱스명) 
					}
				}catch(Exception e) {
					System.out.println("getRating() 에러발생=>"+e);
				}finally {
					pool.freeConnection(con, pstmt, rs);
				}
				return x;
			}
			
			//별점추가하기
			
			public void insertGrade(GradeDTO grade,MovieDTO movie) { 
				//1.신규글인지 확인
				int check=0;
				int user_no=grade.getUser_no();
				int mov_no=grade.getMov_no();
				int primary = gradePrimary();
				try {
					check = getRating(user_no,mov_no);
					if(check > 0) {
						UpdateGrade(grade);
					}else {//데이터가 없는경우->0
					insertMovie(movie);
					con=pool.getConnection();
					con.setAutoCommit(false);
					sql="insert into grade (grade_no,user_no,mov_no,grade_point) values (?,?,?,?)";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, primary);
					pstmt.setInt(2, user_no);
					pstmt.setInt(3, mov_no);
					pstmt.setDouble(4, grade.getGrade_point());
					int insert=pstmt.executeUpdate();
					System.out.println("별점(insert)=>"+insert);
					con.commit();
					}
				}catch(Exception e) {
					System.out.println("별점() 에러발생=>"+e);
				}finally{
					pool.freeConnection(con, pstmt, rs);
				}
			}
			
			//별점수정
			
			public void UpdateGrade(GradeDTO grade) { 
			    int check = 0;
			    int user_no = grade.getUser_no();
			    int mov_no = grade.getMov_no();
			    try {
			        check = getRating(user_no, mov_no);
			        if(check > 0) {
			            con = pool.getConnection();
			            con.setAutoCommit(false);
			            sql = "update grade set grade_point=? where mov_no=? and user_no=?";
			            pstmt = con.prepareStatement(sql);
			            pstmt.setDouble(1, grade.getGrade_point());
			            pstmt.setInt(2, mov_no);
			            pstmt.setInt(3, user_no);
			            int update = pstmt.executeUpdate();
			            con.commit();
			            System.out.println("별점 수정 성공유무(update)=>" + update);
			        }
			    } catch(Exception e) {
			        System.out.println("UpdateGrade() 에러유발=>" + e);
			    } finally {
			        pool.freeConnection(con, pstmt, rs);
			    } 
			}

			
			//별점 삭제
			
			public void deleteGrade(GradeDTO grade) { 
				int check=0;
				int user_no=grade.getUser_no();
				int mov_no=grade.getMov_no();
				
				 try {
					 	check = getRating(user_no,mov_no);
						if(check > 0) {
							con=pool.getConnection();
							con.setAutoCommit(false);
							sql="delete from grade where user_no=? and mov_no=?";
							pstmt=con.prepareStatement(sql);
							pstmt.setInt(1, user_no);
							pstmt.setInt(2, mov_no);
							int delete=pstmt.executeUpdate();
							con.commit();
					    System.out.println("별점 삭제 성공유무(delete)=>"+delete);
						}
						}catch(Exception e) {
						 System.out.println("DeleteArticle() 에러유발=>"+e);
					 }finally {
						pool.freeConnection(con, pstmt, rs);
					 } 
				}

					

}

