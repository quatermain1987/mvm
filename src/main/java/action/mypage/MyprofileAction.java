package action.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.mypage.UserDAO;
import model.mypage.UserDTO;

public class MyprofileAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//실제로 세션에서 받아온 유저식별번호//////////////////////
		HttpSession session=request.getSession();
		int userNo =(int) session.getAttribute("userNo");
		
		UserDAO	dbuser = new UserDAO();
		UserDTO user = dbuser.getUser(userNo);
		//닉네임
		request.setAttribute("user", user);
		return "/mypage/profileEditUser.jsp";
	}

}