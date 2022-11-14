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
<title>수정</title>
</head>
<body>
수정하시겠습니까?
<form name="frm" action="modifyCommentSave.jsp" > 
<table>
   <tr>
   <td><a href="modifyCommentSave.jsp?cId=<%=cId%>"><input type='button' value='예'/></a></td>
   <td><a href="listComment.jsp"><input type='button' value='아니오'/></a></td>
   </tr>
</table>
</form>
</body>
</html>