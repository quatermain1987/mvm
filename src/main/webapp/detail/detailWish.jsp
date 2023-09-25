<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.io.PrintWriter,model.detail.*"
    pageEncoding="UTF-8"%>

 <% 
	request.setCharacterEncoding("utf-8");

	DetailDAO detailDAO=new DetailDAO();
	MovieDTO movie=new MovieDTO ();
	MovieGenreDTO genre = new MovieGenreDTO();

	int mov_no=Integer.parseInt(request.getParameter("mov_no"));
	int user_no=Integer.parseInt(request.getParameter("user_no"));
	String mov_title=request.getParameter("mov_title");

	
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

	//영화제목
	movie.setMov_no(mov_no);
	movie.setMov_title(mov_title);
	
	//wish 데이터 넣기
	detailDAO.insertWish(user_no,mov_no,movie); 

	
	request.setAttribute("mov_no", mov_no);
	
	
	response.reset(); 
	response.setContentType("application/json;charset=UTF-8");
	response.getWriter().print("{\"result\": \"success\"}");
	
	
	%>