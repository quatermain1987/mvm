package model.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
		
			
			
			

					

}

