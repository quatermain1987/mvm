package action.mypage;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.mypage.UserDAO;

public class MylikegenreeditAction implements CommandAction {
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	// TODO Auto-generated method stub
	request.setCharacterEncoding("utf-8");//세션에서 유저식별번호 받아옴
	HttpSession session=request.getSession();
	int userNo =(int) session.getAttribute("userNo");
	Integer genre1=Integer.parseInt(request.getParameter("genre1"));
	Integer genre2=Integer.parseInt(request.getParameter("genre2"));
	Integer genre3=Integer.parseInt(request.getParameter("genre3"));
	UserDAO userdb = new UserDAO();
	List<Integer> likegenreList= new ArrayList<>();
	if (genre1 != null || genre1 != 0)
		likegenreList.add(genre1);
	if (genre2 != null || genre2 != 0)
		likegenreList.add(genre2);
	if (genre3 != null || genre3 != 0)
		likegenreList.add(genre3);
	userdb.updateLikeGenre(userNo,likegenreList);

    String cookieValue = URLEncoder.encode("선호장르가 수정되었습니다", "UTF-8");
    Cookie cookie = new Cookie("popupContent", cookieValue);
    cookie.setPath("/Movivum");
    cookie.setMaxAge(60);
    response.addCookie(cookie);
	
	return"/mypage/mypageIndex.jsp";
	}
}
