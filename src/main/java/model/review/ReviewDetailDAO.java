package model.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import model.DBConnectionMgr;
import model.detail.ReviewJoinDTO;


public class ReviewDetailDAO {
	
	//1.연결할 클래스 객체선언
	private DBConnectionMgr pool=null;
	//공통
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;//select는 반환값이 있기 때문에 그 반환값을 담기 위함
	private String sql=""; //실행시킬 sql구문 저장용
	
	//2. 생성자를 통해서 서로 연결
	public ReviewDetailDAO() {
		try {
			pool=DBConnectionMgr.getInstance(); //DB연결얻어오기
		}catch(Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}
	}

	private int replyPrimary() {
		int newPrimary = 1;
		try {
			con = pool.getConnection();
			sql = "select max(reply_no) from reply";
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
	
	//리뷰상세내용 출력
	public ReviewJoinDTO getDetailReview(int review_no) {
	    ReviewJoinDTO review = null;
	    try {
	        con = pool.getConnection();
	        sql = "select r.review_no,r.user_no,r.mov_no,r.review_content,r.review_date,r.recomm_count,m.user_nickname,m.user_pic,g.grade_point from review r left join grade g "
	            + "on (r.mov_no = g.mov_no and r.user_no = g.user_no) left join member m "
	            + "on (r.user_no=m.user_no) "
	            + "where r.review_no=?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, review_no);
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            review = new ReviewJoinDTO();
	            review.setReview_no(rs.getInt("review_no"));
	            review.setUser_no(rs.getInt("user_no"));
	            review.setMov_no(rs.getInt("mov_no"));
	            review.setReview_content(rs.getString("review_content"));
	            review.setReview_date(rs.getTimestamp("review_date"));
	            review.setRecomm_count(rs.getInt("recomm_count"));
	            review.setGrade_point(rs.getDouble("grade_point"));
	            review.setUser_nickname(rs.getString("user_nickname"));
	            review.setUser_pic(rs.getString("user_pic"));
	        }
	    } catch (Exception e) {
	        System.out.println("getDetailReview() 에러발생=>" + e);
	    } finally {
	        pool.freeConnection(con, pstmt, rs);
	    }
	    return review;
	}
	
	//리뷰에 대한 갯수확인
	public int getReplyCount(int review_no){
		int x=0;
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);//DBConnectionMgr
			sql="select count(*) from reply where review_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,review_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				x=rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getReviewCount() 에러발생=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	//댓글 목록 불러오기
	
	public List getReply(int review_no,int start,int end) {
		List replyList=null; 
		int check = 0;
		try {
			check=getReplyCount(review_no);
	
			 if(check>0) {
		
			con=pool.getConnection();
			sql="SELECT * FROM ("
					+ "  SELECT r.reply_no,r.review_no, r.user_no, r.reply_content, r.reply_date, m.user_nickname,m.user_pic, rownum as rnum"
					+ "  FROM reply r LEFT JOIN member m ON r.user_no = m.user_no WHERE r.review_no = ?"
					+ "  ORDER BY r.reply_date DESC ) WHERE rnum BETWEEN ? AND ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,review_no);
			pstmt.setInt(2,start);
			pstmt.setInt(3,end);
			rs=pstmt.executeQuery();
			if(rs.next()) {//화면에 보여줄 데이터가 있으면
				replyList=new ArrayList(end); //end갯수만큼 공간을 생성해라
				do {
					ReviewJoinDTO review=new ReviewJoinDTO();
					review.setReply_no(rs.getInt("reply_no"));
					review.setReview_no(rs.getInt("review_no")); 
					review.setUser_no(rs.getInt("user_no"));
					review.setReply_content(rs.getString("reply_content"));
					review.setReply_date(rs.getTimestamp("reply_date"));
					review.setUser_nickname(rs.getString("user_nickname"));
					review.setUser_pic(rs.getString("user_pic"));
					//추가
					replyList.add(review);//생략하면 데이터 저장X => for문X(Null)
				}while(rs.next());//더 있으면 계속
			}
			 }
		}catch(Exception e) {
			System.out.println("getReply() 에러발생=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return replyList; 
	}
	
	
	//페이징처리
	
	public Hashtable pageList(String pageNum,int count) {
		
		//0.페이징처리결과를 저장할 Hashtable객체를 선언
		Hashtable<String,Integer> pgList=new Hashtable<String,Integer>();
		
		int pageSize=15; //numPerPage 페이지당 보여주는 게시물 수(=레코드 수)(default:10)
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

	
	//댓글쓰기 
	public void insertReply(ReplyDTO reply) { 
		//1.신규글인지 확인
		int user_no=reply.getUser_no();
		int review_no=reply.getReview_no();
		int primary = replyPrimary();
		System.out.println(user_no+"()insertReply");
		System.out.println(review_no+"()review_no");
		try {
			con=pool.getConnection();
			con.setAutoCommit(false);
			sql="insert into reply values (?,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, primary);
			pstmt.setInt(2, user_no);
			pstmt.setInt(3, review_no);
			pstmt.setString(4,reply.getReply_content());
			int insert=pstmt.executeUpdate();
			System.out.println("댓글입력(insert)=>"+insert);
			con.commit();
		}catch(Exception e) {
			System.out.println("insertArticle() 에러발생=>"+e);
		}finally{
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
	//리뷰에 내 댓글이 있는지 
	public int myReply(int user_no,int review_no){
		int x=0;
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);//DBConnectionMgr
			sql="select * from reply where user_no=? and review_no =?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,user_no);
			pstmt.setInt(2,review_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				x=rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getReviewCount() 에러발생=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	//삭제 댓글
	
	public int deleteReply(int user_no,int reply_no) {
		System.out.println("deleteReply user_no=>"+user_no+"reply_no="+reply_no);
		int x=-1; //게시물의 삭제성공유무
		   try {
			   con=pool.getConnection();
				sql="select * from reply where user_no=? and reply_no=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,user_no);
				pstmt.setInt(2,reply_no);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					con = pool.getConnection();
					con.setAutoCommit(false);
					sql = "delete from reply where user_no=? and reply_no=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, user_no);
					pstmt.setInt(2, reply_no);
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
	
	//수정댓글
	
	public int updateReply(ReplyDTO reply) {
		int x=-1;
		int user_no=reply.getUser_no();
		int reply_no=reply.getReply_no();		
		try {
			con=pool.getConnection();
			sql="select * from reply where user_no=? and reply_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,user_no);
			pstmt.setInt(2,reply_no);
			rs=pstmt.executeQuery();
			if(rs.next())  {
				con = pool.getConnection();
				con.setAutoCommit(false);
				sql="update reply set reply_content=? where user_no=? and reply_no=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, reply.getReply_content());
				pstmt.setInt(2, user_no);
				pstmt.setInt(3, reply_no);
				   int update = pstmt.executeUpdate();
				   con.commit();
				    System.out.println("댓글 수정 성공유무(update)=>" + update);
				} else {
					System.out.println("등록된 데이터가 없음");
				}
		} catch (Exception e) {
			System.out.println("updateReply() 에러발생=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	//5.좋아요(리뷰를 추천)
	   
	public int checkColorLike(int user_no,int review_no) {
		int x = 0;
		try {
			con = pool.getConnection();
	        sql = "SELECT * FROM REVIEW_RECOMM WHERE user_no=? AND review_no=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, user_no);
            pstmt.setInt(2, review_no);
            rs = pstmt.executeQuery();
            if(rs.next()) {
        	   x = 1;
           }
		}catch(Exception e) {
	           e.printStackTrace();
	       } finally {
	           pool.freeConnection(con, pstmt, rs);
	       }
		
		return x;
	}
	
	
	
	//5.좋아요(리뷰를 추천)
	   
	   public void checkLike(int user_no, int review_no) {
	       try {
	           con = pool.getConnection();
	           sql = "SELECT * FROM REVIEW_RECOMM WHERE user_no=? AND review_no=?";
	           pstmt = con.prepareStatement(sql);
	           pstmt.setInt(1, user_no);
	           pstmt.setInt(2, review_no);
	           rs = pstmt.executeQuery();
	           if (rs.next()) {
	              minusRecomm(review_no);
	               sql = "DELETE FROM REVIEW_RECOMM WHERE user_no=? AND review_no=?";
	               pstmt = con.prepareStatement(sql);
	               pstmt.setInt(1, user_no);
	               pstmt.setInt(2, review_no);
	               pstmt.executeUpdate();
	           } else {
	              plusRecomm(review_no);
	              sql = "INSERT INTO REVIEW_RECOMM VALUES (?, ?)";
	              pstmt = con.prepareStatement(sql);
	               pstmt.setInt(1, user_no);
	               pstmt.setInt(2, review_no);
	               try {
	               pstmt.executeUpdate();
	               } catch (SQLException e) {
	                   con.rollback();
	                   throw e;
	               }}
	       } catch (Exception e) {
	           e.printStackTrace();
	       } finally {
	           pool.freeConnection(con, pstmt, rs);
	       }
	   }

	   //5-4. 연결된 REVIEW 테이블에 추천수+1 해주기
	   public void plusRecomm(int review_no) {
	       try {
	           con = pool.getConnection();
	           sql = "update REVIEW set recomm_count=recomm_count+1 where review_no=?";
	           pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, review_no);
	           pstmt.executeUpdate();       
	       } catch (Exception e) {
	           e.printStackTrace();
	       } finally {
	           pool.freeConnection(con, pstmt);
	       }
	   }
	   
	   //5-5. 연결된 REVIEW 테이블에  추천수-1 해주기
	   public void minusRecomm(int review_no) {
	       try {
	           con = pool.getConnection();
	           sql = "update REVIEW set recomm_count=recomm_count-1 where review_no=?";
	           pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, review_no);
	           pstmt.executeUpdate();       
	       } catch (Exception e) {
	           e.printStackTrace();
	       } finally {
	           pool.freeConnection(con, pstmt);
	       }
	   }
	   
	   //5-6. 해당 리뷰에 모든 좋아요 개수 불러오기
	   public int selectLike(int review_no) {
	      int like= 0;
	       try {
	          con = pool.getConnection();
	           sql = "select count(*) from review_recomm where review_no=?";
	           pstmt = con.prepareStatement(sql);
	           pstmt.setInt(1, review_no);
	           rs = pstmt.executeQuery();
	           if(rs.next()) {
	              like = rs.getInt(1); //변경
	           }
	       } catch(Exception e) {
	           e.printStackTrace();
	       } finally {
	          pool.freeConnection(con, pstmt, rs);
	       }
	       return like;
	   }
		
		
	
}
