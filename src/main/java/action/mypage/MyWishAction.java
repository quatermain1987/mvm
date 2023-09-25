package action.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class MyWishAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		//HttpSession session = request.getSession();
		//Integer user_no = (Integer) session.getAttribute("user_no");
		
		Integer user_no=0;
		

		request.setAttribute("user_no", user_no);
		
		return "/mypage/wish.jsp";
	}

}
