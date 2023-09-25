package action.review;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.review.ReplyDTO;
import model.review.ReviewDetailDAO;

public class ReplyUpdateAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		
		request.setCharacterEncoding("utf-8");


		ReviewDetailDAO reviewDetailDAO=new ReviewDetailDAO();
		ReplyDTO reply = new ReplyDTO();
		
//		int mov_no=Integer.parseInt(request.getParameter("mov_no"));
//		int reply_no=Integer.parseInt(request.getParameter("reply_no"));
//		int user_no=Integer.parseInt(request.getParameter("user_no"));
//		int review_no=Integer.parseInt(request.getParameter("review_no"));
		
		
		String reply_content = request.getParameter("reply_content");
		if(reply_content == null){
		    reply_content = "1"; // 빈 문자열로 할당
		  
		}
		int mov_no = 0;
		if (request.getParameter("mov_no") != null) {
		    mov_no = Integer.parseInt(request.getParameter("mov_no"));
		}

		int reply_no = 0;
		if (request.getParameter("reply_no") != null) {
		    reply_no = Integer.parseInt(request.getParameter("reply_no"));
		}

		int user_no = 0;
		if (request.getParameter("user_no") != null) {
		    user_no = Integer.parseInt(request.getParameter("user_no"));
		}

		int review_no = 0;
		if (request.getParameter("review_no") != null) {
		    review_no = Integer.parseInt(request.getParameter("review_no"));
		}
		
		 System.out.println("ReplyUpdateAction "+mov_no+"MOV"+reply_no+"reply_no"+user_no+"user_no"+review_no+"review_no"+reply_content+"reply_content");
		
		reply.setUser_no(user_no);
		reply.setReply_no(reply_no);
		reply.setReply_content(reply_content);
		
		
		reviewDetailDAO.updateReply(reply);
		
		request.setAttribute("mov_no", mov_no);
		request.setAttribute("review_no", review_no);
		response.reset(); 
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("{\"result\": \"success\"}");
		out.flush();
		out.close();

		
		return null;
		
		//return "/review_detail/review_detail_update.jsp";
		
	}

}