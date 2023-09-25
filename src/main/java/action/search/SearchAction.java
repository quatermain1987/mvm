/* psh */

package action.search;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;

public class SearchAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = null;
		session = request.getSession(false);
		if(session!=null) {
			System.out.println("session ID=>"+session.getId());
			
			int user_no = (int)session.getAttribute("user_no");
			System.out.println("searchController user_no => "+user_no);
			
		}
		
		
		System.out.println("서치페이지이동");
		return "/search/search.jsp";
	}

}
