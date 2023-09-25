/* psh */

package model.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;

import model.DBConnectionMgr;

public class QnaDAO {

	private DBConnectionMgr pool = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	public QnaDAO() {
		try {
			pool=DBConnectionMgr.getInstance(); //DB연결얻어오기
		}catch(Exception e) {
			System.out.println("DB접속 오류=>"+e);
		}
	}
	
	// 내 문의글 총 목록 수
	public int getQnaArticleCount(int user_no) {
		int count = 0;
		try {
			con = pool.getConnection();
			sql ="select count(*) from QNA where user_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getQnaArticleCount() 에러발생=> "+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return count;
	}
	
	// 글 목록 불러오기
	public ArrayList<QnaDTO> getQnaArticleList(int user_no,int start,int end){
		ArrayList<QnaDTO> articleList = null;
		try {
			con = pool.getConnection();
			sql ="select qna_no,qna_subject,qna_date,qna_answer "
					+ "from (select a.*,rownum rn "
					+ "from (select * from QNA where user_no=? order by qna_no desc)a) "
					+ "where rn between ? and ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				articleList = new ArrayList<>(end); //end갯수만큼 공간을 생성해라
				do {
					QnaDTO article = new QnaDTO();
					article.setQna_no(rs.getInt("qna_no")); //rs.get...은 별칭명.필드명을 인식 못하는 듯..?
					article.setQna_subject(rs.getString("qna_subject"));
					article.setQna_date(rs.getTimestamp("qna_date"));
					article.setQna_answer(rs.getString("qna_answer"));
					articleList.add(article);
				}while(	rs.next());
			}
		}catch(Exception e) {
			System.out.println("getQnaArticleList() 에러발생=> "+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}
	
	// 페이징처리
	public Hashtable<String,Integer> pageList(String pageNum,int count) {
			
			Hashtable<String,Integer> pgList = new Hashtable<>();
			
		    int pageSize=5; //페이지당 보여주는 게시물 수
		    int blockSize=5; //블럭당 보여주는 페이지 수
		    
		    if(pageNum==null){
			 pageNum = "1";//default(무조건 1페이지는 선택하지 않아도 보여줘야 되기때문에)
		    }
		    int currentPage = Integer.parseInt(pageNum);//"1"->1 (=nowPage(현재페이지))
		    //                    (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21
		    int startRow = (currentPage-1)*pageSize+1;//시작 레코드 번호
		    int endRow = currentPage*pageSize;//1*10=10,2*10=20,3*10=30(마지막 레코드번호)
		    int number = 0;//beginPerPage->페이지별로  시작하는 맨 처음에 나오는 게시물번호
		    //  System.out.println("현재 레코드수(count)=>"+count);
	
		    number=count-(currentPage-1)*pageSize;
		    //  System.out.println("페이지별 number=>"+number);
		    
		    //총페이지수,시작,종료페이지 계산
		    //                      122/10=12.2+1=>12.2+1.0=13.2=13페이지
		    int pageCount=count/pageSize+(count%pageSize==0?0:1);
		    //블럭당 페이지수 계산->10->10배수,3->3배수
		    int startPage=0;//1,2,3,,,,10 [다음 블럭 10]->11,12,,,,20
		    if(currentPage%blockSize!=0){//1~9,11~19,21~29
		    	startPage=currentPage/blockSize*blockSize+1;
		    }else{ //10%10=0,(10,20,30)
							//((10/10)-1)*10+1
		    	startPage=((currentPage/blockSize)-1)*blockSize+1;
		    }
		    int endPage=startPage+blockSize-1;//1+10-1=10
		    //  System.out.println("startPage="+startPage+",endPage="+endPage);
		    if(endPage > pageCount){
		    	endPage=pageCount;//마지막페이지=총페이지수
		    }
		    //페이징처리에 대한 계산결과->Hashtable,HashMap->ListAction전달->메모리에 저장->list.jsp
		    pgList.put("pageSize", pageSize);// <-> pgList.get(키명)("pageSize")
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
	
	// 문의글 상세보기
	public QnaDTO getQnaArticle(int qna_no) {
		QnaDTO article = null;
		try {
			con = pool.getConnection();
			sql = "select * from qna where qna_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,qna_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new QnaDTO();
				article.setQna_no(rs.getInt("qna_no"));
				article.setUser_no(rs.getInt("user_no"));
				article.setQna_subject(rs.getString("qna_subject"));
				article.setQna_content(rs.getString("qna_content"));
				article.setQna_date(rs.getTimestamp("qna_date"));
				article.setQna_answer(rs.getString("qna_answer"));
			}
		}catch(Exception e) {
			System.out.println("getQnaArticle() 에러발생=> "+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}
	
	// 문의글 작성하기
	public void writeQna(QnaDTO article) {
		try {
			con = pool.getConnection();
			con.setAutoCommit(false);
			sql = "insert into qna(qna_no,user_no,qna_subject,qna_content,qna_date) values(qna_seq.nextval,?,?,?,systimestamp)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,article.getUser_no());
			pstmt.setString(2,article.getQna_subject());
			pstmt.setString(3,article.getQna_content());
			pstmt.executeUpdate();
			con.commit();
		}catch(Exception e) {
			System.out.println("writeQna() 에러발생=> "+e);
			if(con!=null) {
				try {
					con.rollback();
				} catch(Exception e2) {
					System.out.println("writeQna() 에러발생2=> "+e2);
				}
			}
		}finally {
			try {
			con.setAutoCommit(true);
			} catch(Exception e3){
				System.out.println("writeQna() 에러발생3=> "+e3);
			}finally {
				if(pool!=null) {
					pool.freeConnection(con, pstmt);
				}
			}
		}
	}
	
	// 문의 글 수정하기
	public void updateQna(QnaDTO article) {
		try {
			con = pool.getConnection();
			con.setAutoCommit(false);
			sql = "update QNA set qna_subject=?,qna_content=? where qna_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,article.getQna_subject());
			pstmt.setString(2,article.getQna_content());
			pstmt.setInt(3,article.getQna_no());
			pstmt.executeUpdate();
			con.commit();
		}catch(Exception e) {
			System.out.println("updateQna() 에러발생=> "+e);
			if(con!=null) {
				try {
					con.rollback();
				} catch(Exception e2) {
					System.out.println("updateQna() 에러발생2=> "+e2);
				}
			}
		}finally {
			try {
			con.setAutoCommit(true);
			} catch(Exception e3){
				System.out.println("updateQna() 에러발생3=> "+e3);
			}finally {
				if(pool!=null) {
					pool.freeConnection(con, pstmt);
				}
			}
		}
	}
	
	// 문의글 삭제하기
	public void deleteQna(int qna_no) {
		try {
			con = pool.getConnection();
			con.setAutoCommit(false);
			sql = "delete from QNA where qna_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,qna_no);
			pstmt.executeUpdate();
			con.commit();
		}catch(Exception e) {
			System.out.println("deleteQna() 에러발생=> "+e);
			if(con!=null) {
				try {
					con.rollback();
				} catch(Exception e2) {
					System.out.println("deleteQna() 에러발생2=> "+e2);
				}
			}
		}finally {
			try {
			con.setAutoCommit(true);
			} catch(Exception e3){
				System.out.println("deleteQna() 에러발생3=> "+e3);
			}finally {
				if(pool!=null) {
					pool.freeConnection(con, pstmt);
				}
			}
		}
	}
	
	
	// 관리자 문의글 답변+++
	public void answerQna(QnaDTO article) {
		try {
			con = pool.getConnection();
			con.setAutoCommit(false);
			sql = "update QNA set qna_answer=?,qna_answer_date=systimestamp,admin_no=? where qna_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,article.getQna_answer());
			pstmt.setInt(2,article.getAdmin_no());
			pstmt.setInt(3,article.getQna_no());
			pstmt.executeUpdate();
			con.commit();
		}catch(Exception e) {
			System.out.println("updateQna() 에러발생=> "+e);
			if(con!=null) {
				try {
					con.rollback();
				} catch(Exception e2) {
					System.out.println("updateQna() 에러발생2=> "+e2);
				}
			}
		}finally {
			try {
			con.setAutoCommit(true);
			} catch(Exception e3){
				System.out.println("updateQna() 에러발생3=> "+e3);
			}finally {
				if(pool!=null) {
					pool.freeConnection(con, pstmt);
				}
			}
		}
	}
	
	
}
