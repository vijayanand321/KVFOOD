<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>KVFOODS</title>
</head>
<body>

<%
String resp=(String)request.getAttribute("resp");
%>
   <% if(resp==null) {%>
     <form action="PasswordReset" method="post">
     <input type="email" name="email" placeholder="enter your email" />
     <input  type="submit"/>
     </form>
     
     <%}else if(resp.equals("forgotPasswordReset")){ %>
         
     <form action="PasswordReset?req="newPassword"" method="post">
     <input type="password" name="password" placeholder="enter your new password" />
     <input type="password" name="confirmPassword" placeholder="enter confirmPassword" />
     <input  type="submit"/>
     </form>
     
    <%} %>
     
</body>
</html>