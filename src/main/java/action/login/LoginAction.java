package action.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.member.LikeGenreDTO;
import model.member.LoginDTO;
import model.member.MemberDAO;

public class LoginAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String user_email=request.getParameter("user_email");
		if (user_email == null) { user_email = "none"; }
		String user_password=request.getParameter("user_password");
		if (user_password == null) { user_password = "none"; }
		
		System.out.println("LoginAction : user_email="+user_email+", user_password="+user_password);
		
		return "/main/login.jsp";
	}

}
