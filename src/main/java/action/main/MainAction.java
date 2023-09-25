package action.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.member.LoginDTO;
import model.member.MemberDAO;

public class MainAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		session.removeAttribute("loginResult");
		
		session.getAttribute("user_no");
		session.getAttribute("user_email");
		session.getAttribute("user_password");
		session.getAttribute("user_pic");
		session.getAttribute("user_nickname");
		
		return "/main/main.jsp";
	}

}
