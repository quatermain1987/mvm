package action.join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.member.LikeGenreDTO;
import model.member.MemberDTO;

public class JoinAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		
		String user_email=(String)session.getAttribute("user_email");
		if (user_email == null) { user_email = "none"; }
		
		String user_name=request.getParameter("user_name");
		if (user_name == null) { user_name = "none"; }
		
		String user_birth=request.getParameter("user_birth");
		if (user_birth == null) { user_birth = "none"; }
		
		String user_password=request.getParameter("user_password");
		if (user_password == null) { user_password = "none"; }
		
		String user_nickname=request.getParameter("user_nickname");
		if (user_nickname == null) { user_nickname = "none"; }
		
		String user_pic=request.getParameter("user_pic");
		if (user_pic == null) { user_pic = "none"; }
		
		String[] user_genres = request.getParameterValues("user_genres");
		if(user_genres != null){
			for(int i=0; i<user_genres.length; i++){
			String genre = user_genres [i];
			int genre_no = Integer.parseInt (genre);
			LikeGenreDTO likeGenre = new LikeGenreDTO();
			likeGenre.setUser_genres(genre_no);
			} 
		} else {
			user_genres=new String[0];
		}
		
		System.out.println(user_email+" | "+user_name+" | "+user_birth+" | "+user_password+" | "+user_nickname+" | "+user_pic);
		for(int i=0;user_genres.length>i;i++){
				System.out.println(user_genres[i]+" ");		
			}
					
		MemberDTO member=new MemberDTO();
		member.setUser_email(user_email);
		member.setUser_password(user_password);
		member.setUser_name(user_name);
		member.setUser_birth(user_birth);
		member.setUser_pic(user_pic);
		member.setUser_nickname(user_nickname);
				
		return "/main/join.jsp";
	}

}
