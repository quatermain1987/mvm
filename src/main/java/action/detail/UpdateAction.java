package action.detail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.detail.DetailDAO;
import model.detail.ReviewDTO;

public class UpdateAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");


		DetailDAO detailDAO=new DetailDAO();
		ReviewDTO review = new ReviewDTO();
		
		int mov_no=Integer.parseInt(request.getParameter("mov_no"));
		int user_no=Integer.parseInt(request.getParameter("user_no"));
		String review_contet=request.getParameter("review_content");
		
		review.setMov_no(mov_no); 
		review.setUser_no(user_no);
		review.setReview_content(review_contet);
		
		
		detailDAO.updateReview(review);
		
		request.setAttribute("mov_no", mov_no);
		
		return "/detail/update.jsp";
		
		/* 비동기시
		response.reset(); 
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("{\"result\": \"success\"}");
		out.flush();
		out.close();
		
		return null;
		*/
	}

}
