package action.mypage;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.mypage.UserDAO;

public class MypageAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//원래는 로그인시 받아와야하지만 없으니까 임의로 넣었음/////////////
		HttpSession session=request.getSession();
		int userNo =(int) session.getAttribute("user_no");
//		int loginNo = 0;
//		HttpSession session=request.getSession();
		session.setAttribute("userNo", userNo);
		
		//실제로 세션에서 받아온 유저식별번호//////////////////////
//		int userNo =(int) session.getAttribute("userNo");
		
		UserDAO	dbuser = new UserDAO();
		Map<String,String> user = dbuser.getBannerUser(userNo);
		//닉네임
		request.setAttribute("nickname", user.get("userNickname"));
		//자기소개
		request.setAttribute("introduce", user.get("userIntroduce"));
		//유저이미지
		request.setAttribute("pic", user.get("userPic"));
		return "/mypage/mypage.jsp";
	}

}