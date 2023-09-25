package action.detail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.detail.DetailDAO;
import model.detail.MovieDTO;
import model.detail.MovieGenreDTO;
import model.detail.ReviewDTO;

public class WriteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");


		DetailDAO detailDAO=new DetailDAO();
		ReviewDTO review = new ReviewDTO();
		MovieDTO movie = new MovieDTO();
		MovieGenreDTO genre = new MovieGenreDTO();


		int mov_no=Integer.parseInt(request.getParameter("mov_no"));
		System.out.println("write mov_no=>"+mov_no);
		int user_no=Integer.parseInt(request.getParameter("user_no"));
		System.out.println("write user_no=>"+user_no);

		//영화 제목 넣기

		movie.setMov_no(mov_no);
		movie.setMov_title(request.getParameter("mov_title"));


		//리뷰 넣기
		review.setMov_no(mov_no); 
		review.setUser_no(user_no);
		review.setReview_content(request.getParameter("review_content"));

		detailDAO.insertReview(review,movie); 


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
		 request.setAttribute("mov_no", mov_no);
		 
		 	/*
			response.reset(); 
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("{\"result\": \"success\"}");
			out.flush();
			out.close();
			*/
			
			return "/detail/detailWrite.jsp";
	}

}
