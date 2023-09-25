package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.review.ReplyDTO;
import model.review.ReviewDetailDAO;

public class ReplayWriteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
	

		ReviewDetailDAO reviewDetailDAO = new ReviewDetailDAO();
		ReplyDTO reply = new ReplyDTO();

		
		int review_no=Integer.parseInt(request.getParameter("review_no"));
		int user_no=Integer.parseInt(request.getParameter("user_no"));
		System.out.println("replywrite user_no=>"+user_no);
		int mov_no=Integer.parseInt(request.getParameter("mov_no"));


		//리뷰 넣기
		reply.setReview_no(review_no); 
		reply.setUser_no(user_no);
		reply.setReply_content(request.getParameter("comment"));

		reviewDetailDAO.insertReply(reply); 


		request.setAttribute("review_no", review_no);
		request.setAttribute("mov_no", mov_no);
		 
		return "/review/replywrite.jsp";
	}

}