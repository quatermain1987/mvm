package action.detail;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.detail.DetailDAO;
import model.detail.GradeDTO;
import model.detail.MovieDTO;
import model.detail.MovieGenreDTO;

public class GradeAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		
		DetailDAO detailDAO = new DetailDAO();
		GradeDTO grade = new GradeDTO();
		MovieDTO movie = new MovieDTO();
		MovieGenreDTO genre = new MovieGenreDTO();
		
		int mov_no = 0;
		int user_no = 0;

		if (request.getParameter("mov_no") != null) {
		    mov_no = Integer.parseInt(request.getParameter("mov_no"));
		}

		if (request.getParameter("user_no") != null) {
		    user_no = Integer.parseInt(request.getParameter("user_no"));
		}

		double grade_point = Double.parseDouble(request.getParameter("rating"));
		 
		// 별점넣기
		  grade.setMov_no(mov_no); 
		  grade.setUser_no(user_no);
		  grade.setGrade_point(grade_point);
			
			// 영화 제목 넣기
			movie.setMov_no(mov_no);
			movie.setMov_title(request.getParameter("mov_title"));
			
			
			
		
		if (grade_point <= 0) {
		    // 취소 메서드 호출
			detailDAO.deleteGrade(grade);
		} else {
		    detailDAO.insertGrade(grade, movie); 
		    //장르넣기
			String[] genreStrings = request.getParameterValues("genres");
			if(genreStrings != null){
			    for(int i=0; i < genreStrings.length; i++){ 
			        String genr = genreStrings[i];
			        int genre_no = Integer.parseInt(genr);
			        genre.setGenre_no(genre_no);
			        genre.setMov_no(mov_no);
			        detailDAO.insertGenres(genre); 
			    }
			}
		    
	
		}
		
		response.reset(); 
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("{\"result\": \"success\"}");
		out.flush();
		out.close();

		
		return null;
	}

}
