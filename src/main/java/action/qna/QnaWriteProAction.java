/* psh */

package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.qna.QnaDAO;
import model.qna.QnaDTO;

public class QnaWriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		int user_no = (int)session.getAttribute("user_no");
		
		String qna_subject = request.getParameter("qna_subject");
		String qna_content = request.getParameter("qna_content");
		
		QnaDTO article = new QnaDTO();
		article.setUser_no(user_no);
		article.setQna_subject(qna_subject);
		article.setQna_content(qna_content);
		
		QnaDAO dao = new QnaDAO();
		dao.writeQna(article);
		
		return "/qna_list.do";
	}

}
