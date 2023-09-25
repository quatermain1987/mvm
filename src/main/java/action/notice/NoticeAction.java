package action.notice;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.notice.noticeDAO;


public class NoticeAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		Integer user_no = (Integer) session.getAttribute("user_no");
		
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum : "+pageNum);
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		int count = 0;
		List articleList = null;
		noticeDAO dbPro = new noticeDAO();
		count = dbPro.getArticleCount();
		System.out.println("Notice > ArticleCount : "+count);
		
		Hashtable<String, Integer> pgList = dbPro.pageList(pageNum, count);
		if (0 < count) {
			System.out.println(pgList.get("startRow")+", "+pgList.get("endRow"));
			articleList = dbPro.getBoardArticles(pgList.get("startRow"), pgList.get("endRow"));
		}else {
			articleList = Collections.EMPTY_LIST;
		}
		request.setAttribute("user_no", user_no);
		request.setAttribute("pgList", pgList);
		request.setAttribute("articleList", articleList);
		
		return "admin/notice.jsp";
	}

}
