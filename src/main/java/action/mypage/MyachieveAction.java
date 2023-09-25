package action.mypage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.mypage.AchieveDAO;
import model.mypage.AchieveDTO;

public class MyachieveAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//세션에서 유저식별번호 받아옴
		HttpSession session=request.getSession();
		int userNo =(int) session.getAttribute("userNo");
		
		//최근 완료된 도전과제
		List<AchieveDTO> RecentachieveList;
		AchieveDAO dbachieve = new AchieveDAO();
		RecentachieveList = dbachieve.getRecentAchieve(userNo);
		request.setAttribute("RecentachieveList", RecentachieveList);

		//완료된 도전과제
		List<AchieveDTO> achieveList;
		achieveList = dbachieve.getAchieve(userNo);
		request.setAttribute("achieveList", achieveList);
		
		//도전과제 통계내기
		int max_num=6;
		int count_num=achieveList.size();
		int count_rate = (int)Math.round((double)count_num/max_num*100);
		request.setAttribute("count_num", count_num);
		request.setAttribute("count_rate", count_rate);
		
		//진행중 도전과제(가장 마지막 실행되야함)
		//1.꽉찬 리스트 생성
		List<AchieveDTO> notAchieveList = new ArrayList<AchieveDTO>();
		for (int i = 0; i < 6; i++) {
		    AchieveDTO inst = new AchieveDTO();
		    inst.setAchieveNo(i);
		    inst.setUserNo(userNo);
		    notAchieveList.add(inst);
		}
		//2.완료된 도전과제랑 비교해서 중복된 도전과제 객체제거(남는건 완료안된 도전과제뿐)
		Iterator<AchieveDTO> iterator = notAchieveList.iterator();//notAchieveList를 반복할 iterator객체생성
		while (iterator.hasNext()) {//iterator가 다음으로 넘어갈 수 있을때까지
		    AchieveDTO dto = iterator.next();//dto는 iterator(notAchieveList)의 다음값이다
		    for (AchieveDTO item : achieveList) {//achieveList의 객체와 비교하며 리스트 만큼 반복
		        if (dto.getAchieveNo() == item.getAchieveNo()) {//dto와notAchieveList의 Achieve_no값이 같으면
		            iterator.remove();//해당되는 객체를 제거하고 반복종료
		            break;
		        }
		    }
		}
		request.setAttribute("notAchieveList", notAchieveList);

		return "/mypage/achievement.jsp";
	}

}
