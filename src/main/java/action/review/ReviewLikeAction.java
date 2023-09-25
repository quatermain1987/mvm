package action.review;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import action.CommandAction;
import model.review.ReviewDetailDAO;

public class ReviewLikeAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		// @(임시세션) 로그인 페이지 연결시 아래 2줄 비활성화!@
		// HttpSession session = request.getSession();
		// session.setAttribute("user_no", 52);

		session.getAttribute("user_no");
		System.out.println("session(user_no) = " + session.getAttribute("user_no"));
		int user_no = Integer.parseInt(request.getParameter("user_no"));
		int review_no = Integer.parseInt(request.getParameter("review_no"));

		System.out.println("ReviewLikeAction의 user_no=" + user_no + ", review_no=" + review_no);
		ReviewDetailDAO reviewDetailDAO = new ReviewDetailDAO();

		if (session.getAttribute("user_no") != null) {
			reviewDetailDAO.checkLike(user_no, review_no);
		} else {
			// 아니면 로그인창으로 보내버리기 또는 그냥 페이지 -1하기
			// response.sendRedirect("/login.jsp");
			response.sendRedirect("javascript:history.back(-1);");
		}

		int like = reviewDetailDAO.selectLike(review_no);
		System.out.println("ReviewLikeAction의 like개수=" + like); // 해당 review_no의 모든 like

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("like", like);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println(out);
		out.print(jsonObject.toString());
		out.flush();
		out.close();
		// review_detail 페이지의 비동기
		return null;

	}
}