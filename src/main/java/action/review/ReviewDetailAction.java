package action.review;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.detail.ReviewJoinDTO;
import model.review.ReviewDetailDAO;

public class ReviewDetailAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int review_no=Integer.parseInt(request.getParameter("review_no"));
		//int user_no=(int)(Math.random()*60);
		
		HttpSession session = request.getSession();
		Integer user_no = (Integer) session.getAttribute("user_no");
		System.out.println("review detail user_no session!! : "+user_no);
		System.out.println("reveiw_detailAction=>review_no"+review_no);
		
		//상단 리뷰내용 출력
		ReviewDetailDAO reviewDetailDAO=new ReviewDetailDAO();

		ReviewJoinDTO review = reviewDetailDAO.getDetailReview(review_no);

		//댓글 페이징
		String pageNum=request.getParameter("pageNum");
		List replyList=null; //화면에 출력할 댓글저장
		int count=reviewDetailDAO.getReplyCount(review_no);
		
		Hashtable<String,Integer>pgList=reviewDetailDAO.pageList(pageNum, count);
		
		if(count > 0) {
			System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
			replyList=reviewDetailDAO.getReply(review_no,pgList.get("startRow"), pgList.get("endRow"));
			System.out.println("reveiw_detailAction의 replyList=>"+replyList);
		}else {
			replyList=Collections.EMPTY_LIST;//비어있는 List객체반환
		}
		
		int myreply=0; 
		int likeColor = 0;
		//유저별 댓글 유무
		if (user_no != null) {
			myreply=reviewDetailDAO.myReply(user_no, review_no);
			likeColor  = reviewDetailDAO.checkColorLike(user_no, review_no);
		}
		
		int like = reviewDetailDAO.selectLike(review_no);
		
		// report
		if (request.getParameter("report_no") != null) {
			int report_no = Integer.parseInt(request.getParameter("report_no"));
			request.setAttribute("report_no", report_no);
		}
		
		request.setAttribute("count",count);
		request.setAttribute("pgList",pgList);
		request.setAttribute("replyList", replyList);
		request.setAttribute("review", review); 
		request.setAttribute("user_no", user_no);
		request.setAttribute("myreply", myreply);
		request.setAttribute("likeCount", like);
		request.setAttribute("likeColor", likeColor);
	
		return "/review/review_detail.jsp";
	}

}
