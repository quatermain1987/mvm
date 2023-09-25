package action.join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.member.LikeGenreDTO;
import model.member.LoginDAO;
import model.member.LoginDTO;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class JoinProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		System.out.println("session="+session);
		
		String user_email=request.getParameter("user_email");
		String user_password=request.getParameter("user_password");
		String user_name=request.getParameter("user_name");
		String user_birth=request.getParameter("user_birth");
		String user_nickname=request.getParameter("user_nickname");
		String user_pic=request.getParameter("user_pic");
		String user_genres[] = request.getParameterValues("user_genres");
		
		System.out.println(user_email+","+user_password+","+user_name+","+user_birth+","+user_nickname+","+user_pic);
		for(int i=0;user_genres.length>i;i++) {
			System.out.println(user_genres[i]);
		}
		
		MemberDTO member=new MemberDTO();
		member.setUser_email(user_email);
		member.setUser_password(user_password);
		member.setUser_name(user_name);
		member.setUser_birth(user_birth);
		member.setUser_nickname(user_nickname);
		member.setUser_pic(user_pic);
		
		//member테이블에 저장
		MemberDAO memberDao=new MemberDAO();
		boolean insertCheck= memberDao.userInsert(member);
		boolean loginResult=false;
		
		if(insertCheck) {
			LikeGenreDTO likeGenre = new LikeGenreDTO();
			for(int i=0; i<user_genres.length; i++){
				String genre = user_genres[i];
				int genre_no = Integer.parseInt(genre);
				likeGenre.setUser_genres(genre_no);
				memberDao.likeGenreInsert(likeGenre, user_email);
			}
			LoginDAO loginDao=new LoginDAO();
			LoginDTO login=new LoginDTO();
			
			boolean loginCheck=loginDao.Authenticate(user_email, user_password);
			System.out.println("LoginProcAction logincheck=>"+loginCheck);
			
			if(loginCheck) {
				login=loginDao.getMember(user_email, user_password);
				session.setAttribute("user_no",login.getUser_no());
				session.setAttribute("user_email",login.getUser_email());
				session.setAttribute("user_nickname",login.getUser_nickname());
				session.setAttribute("user_pic",login.getUser_pic());
				System.out.println("JoinProcAction => user_no:"+session.getAttribute("user_no"));
				loginResult=true;
			}else {
				loginResult=false;
			}
		}	
		
		System.out.println("JoinProcAction loginResult="+loginResult);
		session.setAttribute("loginResult", loginResult);
				
		return "/main/joinProc.jsp";
	}

}
