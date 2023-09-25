package model.mypage;

public class ReviewGenreDTO {
	private String genreName;
	private int sumGradePoint, countGenreNo, avgGradePoint;

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = convert(genreName);
	}

	public int getSumGradePoint() {
		return sumGradePoint;
	}

	public void setSumGradePoint(int sumGradePoint) {
		this.sumGradePoint = sumGradePoint;
	}

	public int getCountGenreNo() {
		return countGenreNo;
	}

	public void setCountGenreNo(int countGenreNo) {
		this.countGenreNo = countGenreNo;
	}

	public int getAvgGradePoint() {
		return avgGradePoint;
	}

	public void setAvgGradePoint(int avgGradePoint) {
		this.avgGradePoint = avgGradePoint;
	}

	// 이 클래스에서만 사용하기위해서 접근지정자 private <,>,(,)=>변경메서드
	private static String convert(String name) {
		if (name != null) {
			// 2.입력받은 문자열중에서 자바스크립트 구문을 실행시킬수 있는 특수기호를 입력X(<,>)
			// 문자열메서드->replaceAll(1.변경전문자열,2.변경후 문자열)

			name = name.replaceAll("<", "&lt");
			name = name.replaceAll(">", "&gt");
			// 추가 eval(" " or ' ')
			name = name.replaceAll("\\(", "&#40");
			name = name.replaceAll("\\)", "&#41");
			// "test" 'test'
			name = name.replaceAll("\"", "&quot");
			name = name.replaceAll("\'", "&apos");
		} else { // name==null
			return null; // 입력을 하지 않았다면 더 이상 실행X
		}
		return name;
	}
}
