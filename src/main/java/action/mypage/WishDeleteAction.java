package action.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.mypage.DetailDAO;

public class WishDeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		DetailDAO detailDAO=new DetailDAO();
		
		int mov_no=Integer.parseInt(request.getParameter("mov_no"));
		int userNo=Integer.parseInt(request.getParameter("userNo"));
		detailDAO.deleteWish(userNo,mov_no); 
		
		request.setAttribute("mov_no", mov_no);
		request.setAttribute("userNo", userNo);
		
		return "/mypage/mypageIndex.jsp";
	}

}
