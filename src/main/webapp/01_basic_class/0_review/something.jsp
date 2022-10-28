<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="member.dao1.*" %> 
 
 
   
<%
//0. 한글처리
request.setCharacterEncoding("utf-8");

// 1. 이전 폼에 입력값 얻어오기
	String realname = request.getParameter("realname");
	String nickname = request.getParameter("nickname");
	String email = request.getParameter("myemail");
	int age = Integer.parseInt(request.getParameter("mynumber"));
 	
	System.out.println(request.getParameter("mynumber") + ">");
	
//2. VO객체에 저장하기
	MemberVO v = new MemberVO();
	v.setRealname(realname);
	v.setNickname(nickname);
	v.setEmail(email);
	v.setAge(age); 
//3. DB에 저장하기
	MemberDAO dao = MemberDAO.getInstance();
    dao.insert(v);
//4. 출력은 알아서
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 성공적으로 입력되었지 DB에서 확인합니다.
	 <hr/>
	 <!-- 1-2) 얻어온 입력값 출력 -->
	 <h2>폼의 입력값 넘겨받아 처리</h2>
	 입력한 이름 : <%= realname  %><br/>
	 입력한 별명 : <%= nickname %><br/>
	 입력한 메일 : <%= email %><br/>
	 입력한 나이 : <%= age %><br/>
	 
</body>
</html>