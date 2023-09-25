package action.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.mypage.UserDAO;

public class MyprofileDeleteAction implements CommandAction {
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	// TODO Auto-generated method stub
	request.setCharacterEncoding("utf-8");//세션에서 유저식별번호 받아옴
	HttpSession session=request.getSession();
	int userNo =(int) session.getAttribute("userNo");
	
	UserDAO userdb =new UserDAO();
	userdb.deleteUserProfile(userNo);

    session.setAttribute("popupContent", "탈퇴되었습니다");
		
	return"";
	}
}
