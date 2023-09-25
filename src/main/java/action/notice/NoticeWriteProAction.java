package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.notice.noticeDAO;
import model.notice.noticeDTO;

public class NoticeWriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		noticeDTO article = new noticeDTO();
		article.setNotice_subject(request.getParameter("notice_subject"));
		article.setNotice_content(request.getParameter("notice_content"));
		System.out.println("noticeWritePro에 request값 >> " + request.getParameter("notice_subject")
				+ request.getParameter("notice_content"));
		noticeDAO dbPro = new noticeDAO();
		dbPro.insertArticle(article);

		return "admin/noticeWritePro.jsp";
	}

}
