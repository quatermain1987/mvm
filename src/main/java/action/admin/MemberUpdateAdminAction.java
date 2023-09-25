package action.admin;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import model.member.MemberDAO;
import model.member.MemberDTO;
import util.FileUtil;

public class MemberUpdateAdminAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int sizeLimit = 100*1024*1024; //100MB 제한
	
		try{
			MultipartRequest multi = new MultipartRequest(request, FileUtil.UPLOAD_PATH, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
			String filePath="";
			String fileName=multi.getFilesystemName("user_pic");
			String originFileName=multi.getOriginalFileName("user_pic");
			int user_no=Integer.parseInt(multi.getParameter("user_no"));
			int num=Integer.parseInt(multi.getParameter("num"));
			int pageNum=Integer.parseInt(multi.getParameter("pageNum"));
			String user_email=multi.getParameter("user_email");
			String user_name=multi.getParameter("user_name");
			String user_nickname=multi.getParameter("user_nickname");
			String user_password=multi.getParameter("user_password");
			String[] user_status = multi.getParameterValues("user_status");
			String userstatus = user_status[0];
			
			
			MemberDAO memberDao=new MemberDAO();
	
			String oldFileName=fileName;
			if(oldFileName!=null) {
		  		FileUtil.removeFile(oldFileName);
		  		//File file=new File(FileUtil.UPLOAD_PATH+"/"+fileName);
			}
			//
			
			MemberDTO user=null;
			user=new MemberDTO();
			user.setUser_no(user_no);
	    	user.setUser_email(user_email);
	    	user.setUser_password(user_password);
	    	user.setUser_name(user_name);
	    	user.setUser_nickname(user_nickname);
	    	user.setUser_pic(oldFileName);
	    	user.setUser_status(userstatus);
	    	
			int updateResult=memberDao.updateUser(user);
			
			System.out.println("user_email="+user_email+", user_password="+user_password+", user_name"+user_name+", user_name="+user_name+", user_status="+userstatus);
			System.out.println("memberUpdatePro.jsp의 회원수정유무(updateResult)=>" + updateResult);
			
			
			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("user", user);
			request.setAttribute("updateResult", updateResult);
			
         }catch (Exception e) {
        	 	System.out.println("파일업로드 중 에러발생=>"+e.getMessage());
                e.printStackTrace();
         }	
		return "/admin/memberUpdateAdminProc.jsp";
	}
}
