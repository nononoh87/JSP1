<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<% 
request.setCharacterEncoding("utf-8");
int cId = Integer.parseInt( request.getParameter("cId"));
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제</title>
</head>
<body>
삭제하시겠습니까?
<form name="frm" action="deleteCommentSave.jsp" > 
<table>
   <tr>
   <td><a href="deleteCommentSave.jsp?cId=<%=cId%>"><input type='button' value='예'/></a></td>
   <td><a href="listComment.jsp"><input type='button' value='아니오'/></a></td>
   </tr>
</table>
</form>
</body>
</html>