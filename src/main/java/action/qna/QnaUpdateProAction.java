/* psh */

package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.qna.QnaDAO;
import model.qna.QnaDTO;

public class QnaUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		int qna_no = Integer.parseInt(request.getParameter("qna_no"));

		String qna_subject = request.getParameter("qna_subject");
		String qna_content = request.getParameter("qna_content");
		
		QnaDTO article = new QnaDTO();
		article.setQna_no(qna_no);
		article.setQna_subject(qna_subject);
		article.setQna_content(qna_content);
		
		QnaDAO dao = new QnaDAO();
		dao.updateQna(article);
		
		return "/qna_detail.do";
	}

}
