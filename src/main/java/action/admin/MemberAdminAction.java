package action.admin;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class MemberAdminAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
			}
		String searchOption=request.getParameter("searchOption");
		String searchText=request.getParameter("searchText");
		System.out.println("MemberAdminAction의 매개변수 확인 pageNum=> "+pageNum+", searchOption=> "+searchOption+", searchtext=> "+searchText);
		
		Map<MemberDTO, Integer> userMap=new HashMap<>();
		MemberDAO memberDao=new MemberDAO();
			
		int count=0;
		count=memberDao.getUserSearchCount(searchOption, searchText);
		System.out.println("MemberAdminAction의 현재 레코드 수(count)=> "+count);
		
		Hashtable<String,Integer> pgList=memberDao.pageList(pageNum, count);
		if(count > 0) {
			System.out.println("startRow="+pgList.get("startRow")+", endRow="+pgList.get("endRow"));
			userMap=memberDao.getUserSearchResult(pgList.get("startRow"),pgList.get("endRow"),searchOption, searchText);
			System.out.println("MemberAdminAction의 userList=>"+userMap);
		}else {
			userMap = new HashMap<MemberDTO, Integer>();
		}
		
		request.setAttribute("searchOption", searchOption);
		request.setAttribute("searchText", searchText);
		request.setAttribute("pgList", pgList);
		request.setAttribute("userMap", userMap);

		return "/admin/memberAdmin.jsp";
	}

}
