package action.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.report.reportDAO;

public class ReportClearAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

		int report_no = Integer.parseInt(request.getParameter("report_no"));
		int review_no = Integer.parseInt(request.getParameter("review_no"));
		int reply_no = Integer.parseInt(request.getParameter("reply_no"));
		int condition = Integer.parseInt(request.getParameter("condition"));
		reportDAO dbPro = new reportDAO();

		if (condition == 1) {
			// 삭제
			if (reply_no != 0) {
				dbPro.replyDelete(reply_no);
			} else if (reply_no == 0) {
				dbPro.reviewDelete(review_no);
			}
			dbPro.reportCondition(report_no);
		} else if (condition == 0) {
			// 유지
			dbPro.reportCondition(report_no);
		}

		return "/admin/reportUpdatePro.jsp";
	}

}
