package model.detail;

public class MovieGenreDTO {
	int movie_ganre_no,mov_no,genre_no;

	public int getMovie_ganre_no() {
		return movie_ganre_no;
	}

	public void setMovie_ganre_no(int movie_ganre_no) {
		this.movie_ganre_no = movie_ganre_no;
		System.out.println(movie_ganre_no+"set movie_ganre_no 호출됨");
	}

	public int getMov_no() {
		return mov_no;
	}

	public void setMov_no(int mov_no) {
		this.mov_no = mov_no;
		System.out.println(mov_no+"set mov_no 호출됨");
	}

	public int getGenre_no() {
		return genre_no;
	}

	public void setGenre_no(int genre_no) {
		this.genre_no = genre_no;
		System.out.println(genre_no+"set setGenre_no 호출됨");
	}
	
	
}
