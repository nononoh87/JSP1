<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 이정화면에서 사용자 입력값을 얻어와서
		-request.getParameter("")
		-request.getParameterValues("")
	화면출력-->
<%
	request.setCharacterEncoding("utf-8");
	String irum = request.getParameter("irum");
	String [] pet = request.getParameterValues("pet");
	String choosePet = "";
	for(int i=0; pet!=null && i<pet.length; i++){
		choosePet += pet[i];
	}
%> 

이름 : <%= irum %>
동물 : <%= choosePet %>

</body>
</html>