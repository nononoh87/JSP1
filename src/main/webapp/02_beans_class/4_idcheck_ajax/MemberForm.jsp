<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 회원가입  </title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">


$(function(){
      function selectId(data){
         $("#result").text("");
         $("#result").text(data);
      }
      
      $(".dupChk").click(function(){
         $.ajax({
            type      : "get",
            url         : "selectId.jsp",
            data        : { "id" : $("input[name='id']").val() },
            dataType   : "text",
            success    : selectId
         });
      });
   })
   
</script>
</head>
<body>

<h1>회원가입서 작성하기</h1>
 
   <form action="InsertMember.jsp" method="post" name="frm">
      <table>
         <tr>
            <td width="100">
            <font color="blue">아이디</font>
            </td>
            <td width="100">
            <input type="text" name="id" pattern="[A-Za-z]{3,}" required ="required">
            <input type="button" value="중복확인" class ="dupChk">
            <div id = 'result'></div>
            </td>
         </tr>
         <tr>
            <td width="100">
            <font color="blue">비밀번호</font>
            </td>
            <td width="100">
            <input type="password" name="password" required ="required"/><br/>
            </td>
         </tr>
         <tr>
            <td width="100">
            <font color="blue">비밀번호학인</font>
            </td>
            <td width="100">
            <input type="password" name="repassword" required ="required"/><br/>
            </td>
         </tr>
         <tr>
            <td width="100">
            <font color="blue">이름</font>
            </td>
            <td width="100">
            <input type="text" name="name" required ="required"/><br/>
            </td>
         </tr>
         <tr>
            <td width="100">
            <font color="blue">전화번호</font>
            </td>
            <td>
            <input type="text" size="15" name="tel" required ="required"/>
            <br/>
            </td>
         </tr>
         <tr>
            <td width="100">
            <font color="blue">주소</font>
            </td>
            <td>
            <input type="text" size="50" name="addr" required ="required"/><br/>
            </td>
         </tr>
         <tr>
            <td width="100">
             <!--로그인 버튼-->
             <input type="submit" value="회원가입" onclick="return chk()">
            </td>
            <td width="100">
            <input type="reset" name="cancel" value="취소"><br/>
            </td>
         </tr>
      </table>
   </form>



 </body>
</html>
    