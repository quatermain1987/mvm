/* psh */

package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;

public class QnaWriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		// 넘겨줄 값 없어서 필요 없음. 폼에서 jsp로 바로 요청!
		// 근데 경로바꾸기 귀찮아서 일단 걍냅둠
		
		return "/mypage/qna_write_form.jsp";
	}

}
