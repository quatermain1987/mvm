package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.notice.noticeDAO;
import model.notice.noticeDTO;

public class NoticeUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		noticeDTO article = new noticeDTO();
		
		article.setNotice_no(Integer.parseInt(request.getParameter("notice_no")));
		article.setNotice_subject(request.getParameter("notice_subject"));
		article.setNotice_content(request.getParameter("notice_content"));
		
		noticeDAO dbPro = new noticeDAO();
		dbPro.updateArticle(article);
		request.setAttribute("pageNum", pageNum);
		
		return "admin/noticeUpdatePro.jsp";
	}

}
