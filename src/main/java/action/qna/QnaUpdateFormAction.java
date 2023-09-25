/* psh */

package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.qna.QnaDAO;
import model.qna.QnaDTO;

public class QnaUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int qna_no = Integer.parseInt(request.getParameter("qna_no"));
		
		QnaDAO dao = new QnaDAO();
		QnaDTO article = dao.getQnaArticle(qna_no);
		request.setAttribute("article", article);
		
		return "/mypage/qna_update_form.jsp";
	}

}
