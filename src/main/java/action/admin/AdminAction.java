package action.admin;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.admin.adminDAO;


public class AdminAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int qnaCount = 0;
		int reportCount = 0;
		int regCount = 0;
		int rejectCount = 0;
		int documentCount = 0;
		adminDAO dbPro = new adminDAO();
		qnaCount = dbPro.QnaCount();
		reportCount = dbPro.ReportCount();
		regCount = dbPro.getRegCount();
		rejectCount = dbPro.getRejectCount();
		documentCount = dbPro.documentCount();
		
		
		List qnaList = null;
		List reportList = null;
		if (0 < qnaCount ) {
			qnaList = dbPro.getQnaArticles();
		} else {
			qnaList = Collections.EMPTY_LIST;
		}
		if (0 < reportCount) {
			reportList= dbPro.getReportArticles();
		}else {
			reportList = Collections.EMPTY_LIST;
		}
		
		request.setAttribute("documentCount", documentCount);
		request.setAttribute("regCount", regCount);
		request.setAttribute("rejectCount", rejectCount);
		request.setAttribute("qnaCount", qnaCount);
		request.setAttribute("reportCount", reportCount);
		request.setAttribute("qnaList", qnaList);
		request.setAttribute("reportList", reportList);
		return "admin/admin.jsp";
	}

}
