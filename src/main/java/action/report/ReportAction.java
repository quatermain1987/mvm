package action.report;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.report.reportDAO;
import model.report.reportDTO;

public class ReportAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String pageNum = request.getParameter("pageNum");
		String search = request.getParameter("search");
		String searchtext = request.getParameter("searchtext");
		System.out.println("reportAction pageNum : "+pageNum+", search : "+search+", searchtext : "+searchtext);
		int count = 0;
		List articleList = null;
		reportDAO dbPro = new reportDAO();
		count=dbPro.getArticleSearchCount(search, searchtext);
		System.out.println("reportAction count : "+count);
		
		
		Hashtable<String, Integer> pgList = dbPro.pageList(pageNum, count);
		if (0 < count) {
			articleList = dbPro.getBoardArticles(pgList.get("startRow"), pgList.get("endRow"), search, searchtext);
		}else {
			articleList = Collections.EMPTY_LIST;
		}
		
		request.setAttribute("search", search);
		request.setAttribute("searchtext", searchtext);
		request.setAttribute("pgList", pgList);
		request.setAttribute("articleList", articleList);
		
		
		return "admin/report.jsp";
	}

}
