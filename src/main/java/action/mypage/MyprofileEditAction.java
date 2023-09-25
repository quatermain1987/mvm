package action.mypage;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import action.CommandAction;
import model.mypage.UserDAO;

public class MyprofileEditAction implements CommandAction {
	
    private static final String CHARSET = "utf-8";
    private static final String ATTACHES_DIR = "C:\\webtest\\jsp\\sou\\Movivum\\src\\main\\webapp\\user_image";
    private static final int LIMIT_SIZE_BYTES = 1024*10; //10kb가 최대 사이즈
    Date birth;

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub//세션에서 유저식별번호 받아옴
		HttpSession session=request.getSession();
		int userNo =(int) session.getAttribute("userNo");
		
		response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding(CHARSET);
        //우선 이전 파일을 삭제
        File dir = new File(ATTACHES_DIR);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith(userNo+".");
            }
        });
        for (File file : files) {
            if (file.delete()) {
                System.out.println(file.getName() + " 삭제성공");
            } else {
                System.out.println("삭제실패 " + file.getName());
            }
        }
        
        File attachesDir = new File(ATTACHES_DIR);
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();//임시파일 생성을 위한 팩토리객체 생성(임시파일로 크기를 가늠해서 분류함)
        fileItemFactory.setRepository(attachesDir);//파일을 저장할 위치지정
        fileItemFactory.setSizeThreshold(LIMIT_SIZE_BYTES);//파일 최대크기
        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);//파싱하기쉽도록 ServletFileUpload클래스에 저장할예정
        //꺼내온 값을 map에 저장 폼태그의 인코딩타입이 multipart/form-data인경우 getParameter로 값을 얻어올수 없으므로 file 객체로 파싱한후 다시 해시맵에 저장해야함
        Map<String, String> profileMap = new HashMap<>();
        
        try {
            List<FileItem> items = fileUpload.parseRequest(request);//받아온 정보를 파싱해서 list에 담음(multipart/form-data 인코딩형식으로 받아와서 일반적으로 사용할수없음 일단 파싱해서 담아놓고 다시 변환예정)
            for (FileItem item : items) {
                if (item.isFormField()) {//isFormField() 이 메서드가 참이면 text나 combobox등의 input 형식의 text 파라미터이고 false면 바이너리 형식(file은 바이너리 형식임으로)의 파라미터임
                    System.out.printf("파라미터 명 : %s, 파라미터 값 :  %s \n", item.getFieldName(), item.getString(CHARSET));//input 태그의 name속성=getFieldName(), input 태그로 받은 text들은 다시 String으로 인코딩해서 사용해야함
                    //얻어온 파라미터 정보를 hash맵에 저장
                    profileMap.put(item.getFieldName(), item.getString(CHARSET));
                } else {
                    System.out.printf("파라미터 명 : %s, 파일 명 : %s,  파일 크기 : %s bytes \n", item.getFieldName(),//input 태그의 name속성=getFieldName()
                            item.getName(), item.getSize());//input 태그에 입력된 file이름=getName() ,input 태그에 입력된 file크기=getSize()
                    if (item.getSize() > 0 
                    && (item.getContentType()).startsWith("image/")) {//이미지형식의 파일인지(MIME은 파일을 전송할때 적용되며 헤더에 기록되어 파일의 유형을 알수있음)
                        String separator = File.separator;//separator는 C:\file.text에서 "\"를 뜻하고 이뒤에 문자가 실제파일 이름임 File.separator를 통해서 저장할경우 윈도우는 \,리눅스는 / 범용으로 사용가능
                        //int index =  item.getName().lastIndexOf(separator);
                        //String fileName = item.getName().substring(index  + 1);
                    	String fileName = item.getName();
                    	int index = fileName.lastIndexOf('.');
                    	String extension = fileName.substring(index + 1);
                        File uploadFile = new File(ATTACHES_DIR + separator+userNo+"."+extension);
                        item.write(uploadFile);
                        //이미지 경로를 실제 로컬 파일경로가 아닌 서버경로로 지정
                        profileMap.put(item.getFieldName(),"user_image"+separator+userNo +"."+ extension);
                    	System.out.println("파일업로드 성공");
                    }else {
                    	//조건에 맞지 않더라도 일단 매서드 매개변수에 넣어야하므로 빈문자열을 넣음
                        profileMap.put("file1","");
                    	System.out.println("업로드 파일없음");
                    }
                }
            }
            
            //자바에서 공백은 포함할수없다 아스키코드32에러 발생을 방지하려면 유니코드로 인코딩해야함(꺼낼때는 당연히 디코딩이 필요)
            String cookieValue = URLEncoder.encode("프로필 정보가 수정되었습니다", "UTF-8");
            Cookie cookie = new Cookie("popupContent", cookieValue);
            cookie.setPath("/Movivum");
            cookie.setMaxAge(60);
            response.addCookie(cookie);
        } catch (Exception e) {
            // 파일 업로드 처리 중 오류가 발생하는 경우
        	System.out.println("파일업로드 실패"+e);
            String cookieValue = URLEncoder.encode("프로필 정보가 수정되었습니다", "UTF-8");
            Cookie cookie = new Cookie("popupContent", cookieValue);
            cookie.setPath("/Movivum");
            cookie.setMaxAge(60);
            response.addCookie(cookie);
            
        }finally {
    		//날짜값-을.으로 바꾸기
//    		profileMap.replace("birth",profileMap.get("birth").replace("-", "."));
        	//파일업로드 여부와 상관없이 DAO실행
        	UserDAO userdb = new UserDAO();
        	//파일을 업로드 하지 않았을때
        	if (profileMap.get("file1")=="") {
        		userdb.updateUserProfile(userNo, profileMap.get("nick"), profileMap.get("name"), profileMap.get("introduce"), profileMap.get("passwd"));
        	}else {//파일을 업로드했을때
        		userdb.updateUserProfile(userNo, profileMap.get("nick"),profileMap.get("file1"), profileMap.get("name"), profileMap.get("introduce"), profileMap.get("passwd"));
        	}
        }
        return"/mypage/mypageIndex.jsp";
	}

}
