package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.review.ReviewDetailDAO;

public class ReplyDeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
			
		ReviewDetailDAO reviewDetailDAO=new ReviewDetailDAO();
		

		int review_no=Integer.parseInt(request.getParameter("review_no"));
		int mov_no=Integer.parseInt(request.getParameter("mov_no"));
		int reply_no=Integer.parseInt(request.getParameter("reply_no"));
		int user_no=Integer.parseInt(request.getParameter("user_no"));
		System.out.println("DeleteAction mov_no=>"+mov_no+"user_no=>"+user_no+"review_no"+review_no);
		
		int x = reviewDetailDAO.deleteReply(user_no,reply_no);
		
		request.setAttribute("mov_no",mov_no);//삭제페이지로 이동
		request.setAttribute("review_no",review_no);
		request.setAttribute("x",x);//데이터삭제성공유무	
		
		
		return "/review/review_detail_delete.jsp";
	}

}
