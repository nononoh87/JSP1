<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="guest.service.WriteMessageService"%>    
    
<%
	//0. 넘겨받는 데이타의 한글처리
	request.setCharacterEncoding("utf-8"); 
%>
 <!--  1. 화면의 입력값을 Message 클래스로 전달-->
<jsp:useBean id="m" class="guest.model.Message"> <!--useBean은 자바빈 객체를 생성하는 액션태그,  id 속성에서 지정한 이름의 속성 값이 있을 경우 그 객체를 그대로 사용하고, 없을 경우 새로운 객체를 생성  -->
	<jsp:setProperty name ='m' property='*'/>	 <!--자바빈 파일의 setter 메서드를 사용하기 위해, 즉 데이터의 값을 설정할 때 사용 -->
</jsp:useBean>	

<%
	//2. Service 클래스의 함수 호출
	WriteMessageService service =
			WriteMessageService.getInstance(); //객체를 반환
	service.write(m);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 방명록 남김 </title>
</head>
<body>
	<font size="3" color="#bb44cc">
		<%= m.getGuestName() %>님이 방명록에 메세지를 남겼습니다. 
	</font><br/><br/><br/>
	 <a href ="listMessage.jsp">[ 목록보기 ]</a> 
</body>
</html>