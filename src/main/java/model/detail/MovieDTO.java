package model.detail;

public class MovieDTO {
	
	private int mov_no;
	private String mov_title;
	
	public int getMov_no() {
		return mov_no;
	}
	public void setMov_no(int mov_no) {
		this.mov_no = mov_no;
		System.out.println("MovieDTO()호출함(setMov_no)=>"+mov_no);
	}
	public String getMov_title() {
		return mov_title;
	}
	public void setMov_title(String mov_title) {
		this.mov_title = mov_title;
		System.out.println("mov_title()호출함(mov_title)=>"+mov_title);
	}
	
	
	
}
