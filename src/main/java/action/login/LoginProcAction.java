package action.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.member.LoginDTO;
import model.member.MemberDAO;
public class LoginProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		boolean loginResult=false; //로그인 결과
		
		String user_email=request.getParameter("user_email");
		if (user_email == null) {
			user_email = "none";
			}
		String user_password=request.getParameter("user_password");
		if (user_password == null) {
			user_password = "none";
			}

		MemberDAO memberDao=new MemberDAO();			
		LoginDTO login=memberDao.getUserLogin(user_email,user_password);
		if(login!=null) {
			session.setAttribute("user_no",login.getUser_no());
			session.setAttribute("user_email",login.getUser_email());
			session.setAttribute("user_nickname",login.getUser_nickname());
			session.setAttribute("user_pic",login.getUser_pic());
			loginResult=true;
		}else {
			loginResult=false;
		}

		System.out.println("LoginProcAction loginResult(로그인 성공여부)="+loginResult);
		request.setAttribute("loginResult", loginResult);
		
		return "/main/loginProc.jsp";
	}
}
