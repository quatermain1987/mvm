/* psh */

package action.qna;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.qna.QnaDAO;
import model.qna.QnaDTO;

public class QnaListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		int user_no = (int)session.getAttribute("user_no");
		
		String pageNum = request.getParameter("pageNum");
		
		int count = 0;
		List<QnaDTO> articleList = null;
		
		QnaDAO dao = new QnaDAO();
		count = dao.getQnaArticleCount(user_no);
		
		Hashtable<String,Integer> pgList = dao.pageList(pageNum,count);
		
		if(count>0) {
			articleList =  dao.getQnaArticleList(user_no,pgList.get("startRow"),pgList.get("endRow"));
		}else {
			articleList = Collections.EMPTY_LIST; //비어있는 List객체 반환
		}
			
		request.setAttribute("pgList", pgList);
		request.setAttribute("articleList", articleList);
		
		return "/mypage/qna_my_list.jsp";
		}

}
