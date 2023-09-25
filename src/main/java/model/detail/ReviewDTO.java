package model.detail;

public class ReviewDTO {


		private int review_no,mov_no,user_no,grade_no,recomm_count;
		private String review_content,review_date;
		private double grade_point;
		
		public int getReview_no() {
			return review_no;
		}
		public void setReview_no(int review_no) {
			this.review_no = review_no;
		}
		public int getMov_no() {
			return mov_no;
		}
		public void setMov_no(int mov_no) {
			this.mov_no = mov_no;
			
		}
		public int getUser_no() {
			return user_no;
		}
		public void setUser_no(int user_no) {
			this.user_no = user_no;
			
		}
		public int getGrade_no() {
			return grade_no;
		}
		public void setGrade_no(int grade_no) {
			this.grade_no = grade_no;
		}
		public double getGrade_point() {
			return grade_point;
		}
		public void setGrade_point(double grade_point) {
			this.grade_point = grade_point;
		}
		public String getReview_content() {
			return review_content;
		}
		public void setReview_content(String review_content) {
			this.review_content = convert(review_content);
		}
		public String getReview_date() {
			return review_date;
		}
		public void setReview_date(String review_date) {
			this.review_date = review_date;
		}
		public int getRecomm_count() {
			return recomm_count;
		}
		public void setRecomm_count(int recomm_count) {
			this.recomm_count = recomm_count;
		}
		
		private static String convert(String name) {
			if(name!=null){
		    	//2.입력받은 문자열중에서 자바스크립트 구문을 실행시킬수 있는 특수기호를 입력X(<,>)
		    	//문자열메서드->replaceAll(1.변경전문자열,2.변경후 문자열)
		    	
		    	name=name.replaceAll("<","&lt");
		    	name=name.replaceAll(">","&gt");
		    	//추가 eval(" " or ' ')
		    	name=name.replaceAll("\\(","&#40");
		    	name=name.replaceAll("\\)","&#41");
		    	//"test"  'test'
		    	name=name.replaceAll("\"","&quot");
		    	name=name.replaceAll("\'","&apos");
		    }else{ //name==null
		    	return null; //입력을 하지 않았다면 더 이상 실행X
		    }
			return name;
		}
		
	}

