<%@page import="javax.management.Query"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="member.beans.MemberDao" %>

<% String id = request.getParameter("id"); %>
<% System.out.print(id); %>
<% 
   MemberDao dao = MemberDao.getInstance();
   boolean result = dao.isDuplicatedId(id);
   if( result )   out.print("사용중인 아이디입니다.");
   else         out.print("사용 가능한 아이디입니다.");
   
%>