package action.detail;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.detail.DetailDAO;
import model.detail.ReviewDTO;

public class DetailAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		//HttpSession session = request.getSession();
		//Integer user_no = (Integer) session.getAttribute("user_no");
		
		
		int getHasWishList=0;
		int myGradePoint= 0;
		ReviewDTO myReview=null;	
		
		List reviewList=null;
		
		DetailDAO detailDAO=new DetailDAO();
		int mov_no=Integer.parseInt(request.getParameter("mov_no"));
		HttpSession session = request.getSession();
		Integer user_no = (Integer) session.getAttribute("user_no");
		System.out.println("detail.do user_no session!! : "+user_no);
		
		
		//영화에 대한 리뷰 갯수 구하기
		int review_count=detailDAO.getReviewCount(mov_no);
		System.out.println("현재 레코드수(count)=>"+review_count);
		if(review_count > 0){//한개라도 레코드수가 있다면 
			reviewList=detailDAO.getDetailReview(mov_no); //영화 보여주게
		}
		//영화 평균별점
		double avgrating = Math.round(detailDAO.averageRating(mov_no));
		
		
		//영화 별점 1~5점까지
		int grade1 = detailDAO.countGradeByPoint(mov_no, 1);
		int grade2 = detailDAO.countGradeByPoint(mov_no, 2);
		int grade3 = detailDAO.countGradeByPoint(mov_no, 3);
		int grade4 = detailDAO.countGradeByPoint(mov_no, 4);
		int grade5 = detailDAO.countGradeByPoint(mov_no, 5);
		
		
		
		//유저넘버가 있다면 체크해서 내 정보를 보여주게
		
		if (user_no != null) {
			//유저별 보고싶어요
			getHasWishList=detailDAO.getHasWishList(user_no,mov_no);
			
			//유저별 리뷰
			myReview = detailDAO.getMyReview(mov_no,user_no);
		
			//유저별 별점
			int getRating=detailDAO.getRating(user_no,mov_no);
			if (getRating > 0 ) {
			myGradePoint= getRating;
			}
	
		}
		
		//session.setAttribute("user_no", user_no);
		request.setAttribute("user_no", user_no);
		request.setAttribute("mov_no", mov_no);
		request.setAttribute("review_count", review_count);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("myReview", myReview);
		request.setAttribute("getHasWishList", getHasWishList);
		request.setAttribute("avgrating", avgrating);
		request.setAttribute("myGradePoint", myGradePoint);
		request.setAttribute("grade1", grade1);
		request.setAttribute("grade2", grade2);
		request.setAttribute("grade3", grade3);
		request.setAttribute("grade4", grade4);
		request.setAttribute("grade5", grade5);
		
		
		return "detail/detail.jsp";
	}

}