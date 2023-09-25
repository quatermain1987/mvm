package action.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.mypage.ReviewDAO;
import model.mypage.ReviewGenreDTO;

public class MyreviewAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		//세션에서 유저식별번호 받아옴
		HttpSession session=request.getSession();
		int userNo =(int) session.getAttribute("user_no");
		request.setAttribute("user_no", userNo);
		//리뷰 통계
		ReviewDAO dbreview = new ReviewDAO();
		List<Float> reviewSummaryList = dbreview.getReviewSummary(userNo);

		float average=0;
		for(var i=0; i<reviewSummaryList.size(); i++) {
			average+= reviewSummaryList.get(i);
		}
		average=average/reviewSummaryList.size();
		request.setAttribute("average", Math.round(average * 10) / 10.0);
		request.setAttribute("count", reviewSummaryList.size());
		System.out.println("reviewSummaryList 크기"+reviewSummaryList.size());
		
		//리뷰장르 통계
		List<ReviewGenreDTO> reviewGenreSummaryList = dbreview.getReviewGenreSummary(userNo);
		request.setAttribute("genreList", reviewGenreSummaryList);
		int count=0;
		for (ReviewGenreDTO item : reviewGenreSummaryList) {
			System.out.println(count+"번째 리뷰장르 리스트 값 가져옴");
			switch(count) {
				case 0: request.setAttribute("genre1Name", item.getGenreName());
						request.setAttribute("genre1Avg", item.getAvgGradePoint());
						System.out.println("genre1Name"+ item.getGenreName());
						System.out.println("genre1Avg"+ item.getAvgGradePoint());
					break;
				case 1: request.setAttribute("genre2Name", item.getGenreName());
						request.setAttribute("genre2Avg", item.getAvgGradePoint());
					break;
				case 2: request.setAttribute("genre3Name", item.getGenreName());
						request.setAttribute("genre3Avg", item.getAvgGradePoint());
					break;
				default: request.setAttribute("genre1Name", 0);
						request.setAttribute("genre1Avg", 0);
						request.setAttribute("genre2Name", 0);
						request.setAttribute("genre2Avg", 0);
						request.setAttribute("genre3Name", 0);
						request.setAttribute("genre3Avg", 0);
					break;
			}
			count++;
		}
		
		return "/mypage/myreview.jsp";
	}

}
