<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Integer result = (Integer)request.getAttribute("result");
%>     
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 메세지 삭제 확인 </title>
</head>
<body>
	<% if( result == 0  ) { %>
		수정 실패
	<% } else { %>
		에츠기륏!
	<% } %>
	
	<br/><br/>
	<a href="GuestControl?cmd=list-page"> [ 목록보기 ] </a>
</body>
</html>