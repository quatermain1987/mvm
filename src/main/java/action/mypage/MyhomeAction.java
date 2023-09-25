package action.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.mypage.AchieveDAO;
import model.mypage.AchieveDTO;
import model.mypage.ReviewDAO;
import model.mypage.ReviewDTO;
import model.mypage.UserDAO;
import model.mypage.UserDTO;

public class MyhomeAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//세션에서 유저식별번호 받아옴
		HttpSession session=request.getSession();
		int userNo =(int) session.getAttribute("user_no");
		System.out.println("user NO : "+userNo);
		//리뷰정보 리스트형태로 정보가져오기
		ReviewDAO dbreview = new ReviewDAO();
		List<ReviewDTO> reviewList = dbreview.getReview(userNo, 0, 3);
		request.setAttribute("reviewList", reviewList);
		//유저정보
		UserDAO	dbuser = new UserDAO();
		UserDTO user = dbuser.getUser(userNo);
		request.setAttribute("nickname", user.getUserNickname());
		//유저선호장르정보
		List<String> userLikeGenreList= dbuser.getUserLikeGenre(userNo);
		request.setAttribute("userLikeGenreList", userLikeGenreList);
		
		//도전과제
		AchieveDAO dbachieve = new AchieveDAO();
		List<AchieveDTO> achieveList = dbachieve.getAchieve(userNo);
		//도전과제 통계내기
		int max_num=6;
		int count_num=achieveList.size();
		int count_rate = (int)Math.round((double)count_num/max_num*100);
		request.setAttribute("count_num", count_num);
		request.setAttribute("count_rate", count_rate);
		
		return "/mypage/myhome.jsp";
	}

}
