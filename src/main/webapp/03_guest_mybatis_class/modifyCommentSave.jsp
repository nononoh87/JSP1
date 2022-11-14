<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="mybatis.guest.model.Comment" %>    
<%@ page import="mybatis.guest.service.CommentService" %>
<!-- viewComment.jsp를 참고 한다. -->

<% 
    int cId = Integer.parseInt (request.getParameter("cId"));
    CommentService.getInstance().deleteComment(cId);
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정하기</title>
</head>
<body>
수정되었습니다.
<a href="listComment.jsp">목록보기</a>
</body>
</html>