package action.detail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.detail.DetailDAO;

public class DeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
	DetailDAO detailDAO=new DetailDAO();
		
		int review_no=Integer.parseInt(request.getParameter("review_no"));
		int mov_no=Integer.parseInt(request.getParameter("mov_no"));
		int user_no=Integer.parseInt(request.getParameter("user_no"));
		System.out.println("DeleteAction mov_no=>"+mov_no+"user_no=>"+user_no+"review_no"+review_no);
		
		int x = detailDAO.deleteReview(review_no, user_no);
		
		request.setAttribute("mov_no",mov_no);//삭제페이지로 이동
		request.setAttribute("x",x);//데이터삭제성공유무	
	
		
		return "detail/delete.jsp";
	}

}
