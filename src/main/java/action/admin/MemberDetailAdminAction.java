package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class MemberDetailAdminAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int num=Integer.parseInt(request.getParameter("number"));
		String pageNum=request.getParameter("pageNum");
		int user_no=Integer.parseInt(request.getParameter("user_no"));
		System.out.println("MemberDetailAdminAction의 매개변수 확인 : num=>"+num+", pageNum=>"+pageNum+", user_no=>"+user_no);
		
		MemberDAO memberDao=new MemberDAO();
		MemberDTO user=memberDao.getUserDetail(user_no);
		int reportCount=memberDao.getUserReportCount(user_no);
		System.out.println("상태="+user.getUser_status()+", 사진="+user.getUser_pic());
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("user", user);
		request.setAttribute("reportCount", reportCount);

		return "/admin/memberDetailAdmin.jsp";
	}

}
