<%@page import="mvc.guest.model.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   
	Message vo = (Message)request.getAttribute("param");
   
//    BoardVO vo = ViewArticleService.getInstance().getArticleById(seq);
%>   
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 수정하기</title>
</head>
 <body>
   <h4> 게시판 글 수정하기 </h4><br/>
   <form name='frm' method='get' action="GuestControl">
   패스워드(수정/삭제시 필요) :
         <input type='password' name = "password"><br/><br/>
   내  용 : <textarea name='content' rows='10' cols='40'><%= vo.getMessage() %></textarea><br/><br/>
   <input type="hidden" name = "id" value = "<%= vo.getId() %>"> 
   <input type="hidden" name ="cmd" value ="modify-do">
   <input type='submit' value='수정하기'>
   <input type='button' value='목록보기' onclick="window.location='BoardList.jsp'">
   </form>

</body>
</html>