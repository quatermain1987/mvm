package action.review;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.detail.DetailDAO;

public class ReviewAllAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		
				int mov_no=Integer.parseInt(request.getParameter("mov_no"));
				String pageNum=request.getParameter("pageNum");
				String select = request.getParameter("select");
				if (select == null) {
				    select = "fastest";
				}
				
				System.out.println("listAction의매개변수확인"+"pageNum=>"+pageNum+"select=>"+select+"mov"+mov_no);
				
				int count=0;//총레코드수
				List reviewList=null; //화면에 출력할 레코드 저장
				
				DetailDAO detailDAO=new DetailDAO();
				count=detailDAO.getReviewCount(mov_no);
				System.out.println("ListAtcion의 현재레코드수(count)=>"+count);
				
				//1.화면에 출력할 페이지번호, 2.출력할 레코드 갯수
				Hashtable<String,Integer>pgList=detailDAO.pageList(pageNum, count);
				
				if(count > 0) {
					System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
					reviewList=detailDAO.getSelectedReviews(select,mov_no,pgList.get("endRow"),pgList.get("startRow"));
					System.out.println("listAction의 articlelist=>"+reviewList);
				}else { //count=0
					reviewList=Collections.EMPTY_LIST;//비어있는 List객체반환
				}
				
				//영화 별점 평균
				double avgrating = Math.round(detailDAO.averageRating(mov_no));
				
				//2.처리한 결과를 공유(서버메모리에 저장)->이동할 페이지에 공유해서 사용
				request.setAttribute("mov_no", mov_no); 
				request.setAttribute("select",select);
				request.setAttribute("pgList",pgList);
				request.setAttribute("reviewList", reviewList);//${articleList}
				request.setAttribute("avgrating",avgrating);
				
				return "/review/review_all.jsp";
	}

}
