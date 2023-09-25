package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.notice.noticeDAO;
import model.notice.noticeDTO;

public class NoticeUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		System.out.println("NoticeUpdateFormAction parameter : "+num+", "+pageNum);
		
		noticeDAO dbPro = new noticeDAO();
		noticeDTO article = dbPro.getArticle(num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);
		return "admin/noticeUpdateForm.jsp";
	}

}
