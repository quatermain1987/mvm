package action.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.report.reportDAO;
import model.report.reportDTO;

public class ReportInsertAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		reportDTO article = new reportDTO();
		article.setUser_no(Integer.parseInt(request.getParameter("user_no")));
		article.setReview_no(Integer.parseInt(request.getParameter("review_no")));
		article.setReply_no(Integer.parseInt(request.getParameter("reply_no")));
		article.setReport_option(Integer.parseInt(request.getParameter("report_option")));
		article.setReport_content(request.getParameter("report_content"));
		article.setReport_type(Integer.parseInt(request.getParameter("report_type")));
		System.out.println("@@ " + request.getParameter("user_no") + request.getParameter("review_no")
				+ request.getParameter("reply_no") + request.getParameter("report_option")
				+ request.getParameter("report_content") + request.getParameter("report_type"));
		reportDAO dbPro = new reportDAO();
		dbPro.insertArticle(article);
		request.setAttribute("review_no", request.getParameter("review_no"));
		return "review/reviewInsertPro.jsp";
	}

}
