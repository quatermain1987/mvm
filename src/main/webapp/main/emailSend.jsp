<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.*" %>
<%@ page import="javax.mail.Authenticator" %>
<%@ page import="javax.mail.PasswordAuthentication" %>
<%@ page import="java.util.Properties" %>
<%@ page import="util.*" %>
<%
	String user_email=request.getParameter("user_email");
	AuthCode authcode=new AuthCode();	
	String newCode=authcode.CreateCode();
	
	//사용자 인증 이메일 발송 내용
	String from = "ed279332@gmail.com";
	String to = user_email;
	String subject = "Movivum 이메일 인증 확인"; 
	String content = "<h2 style='text-align:center'>이메일로 전송된 인증코드를 입력해주세요.</h2>"+
						"<p style='font-size:18px;lineheight:40px;text-align:center'>"+newCode+"</p>";
	System.out.println("from: "+from+" | to: "+to);
	System.out.println("인증코드 : "+newCode);
	
	// 이메일 전송 SMTP 이용을 위함
	Properties p = new Properties();
	p.put("mail.smtp.user", from);
	p.put("mail.smtp.host", "smtp.googlemail.com");
	p.put("mail.smtp.port", "465");
	p.put("mail.smtp.starttls.enable", "true");
	p.put("mail.smtp.auth", "true");
	p.put("mail.smtp.debug", "true");
	p.put("mail.smtp.socketFactory.port", "465");
	p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	p.put("mail.smtp.socketFactory.fallback", "false");
	p.put("mail.smtp.ssl.protocols", "TLSv1.2");
	p.put("mail.smtp.ssl.enable", "true");
	System.out.println(p);
	
	try{
		Authenticator auth = new Gmail();
	    Session ses = Session.getInstance(p,auth);
	    ses.setDebug(true);
	    MimeMessage msg = new MimeMessage(ses);//메일 제목
	    msg.setSubject(subject);
	    Address fromAddr = new InternetAddress(from);//보내는 사람 주소
	    msg.setFrom(fromAddr);
	    Address toAddr = new InternetAddress(to);//받는 사람 정보
	    msg.addRecipient(Message.RecipientType.TO, toAddr);//메세지에 보내는 사람 정보 추가
	    msg.setContent(content,"text/html;charset=UTF-8");//메일 본문 내용
	    Transport.send(msg);//메일 전송
		session.setAttribute("user_email",user_email);
		session.setAttribute("newCode", newCode);
		response.getWriter().write(newCode);//join.js에게 공유해주는 코드
	}catch(Exception e){
		e.printStackTrace();
	} 
%>