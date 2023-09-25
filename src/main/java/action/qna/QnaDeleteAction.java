/* psh */

package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.qna.QnaDAO;

public class QnaDeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int qna_no = Integer.parseInt(request.getParameter("qna_no"));
		
		QnaDAO dao = new QnaDAO();
		dao.deleteQna(qna_no);
		
		return "/qna_list.do";
	}

}
