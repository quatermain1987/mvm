package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.notice.noticeDAO;
import model.notice.noticeDTO;


public class NoticeContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		Integer user_no = (Integer) session.getAttribute("user_no");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		noticeDAO dbPro = new noticeDAO();
		noticeDTO article = dbPro.getArticle(num);
		boolean nextCheck = dbPro.pageCheck(num+1);
		boolean preCheck = dbPro.pageCheck(num-1);
		
		request.setAttribute("user_no", user_no);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);
		request.setAttribute("nextCheck", nextCheck);
		request.setAttribute("preCheck", preCheck);
		
		return "admin/noticeContent.jsp";
	}

}
