package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.notice.noticeDAO;

public class NoticeDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		System.out.println("NoticeDeleteProAction pageNum @@ : "+pageNum);
		
		noticeDAO dbPro = new noticeDAO();
		dbPro.deleteArticle(num);
		request.setAttribute("pageNum", pageNum);
		
		return "admin/noticeDeletePro.jsp";
	}

}
