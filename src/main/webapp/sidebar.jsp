<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.kvfood.model.User"%>
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

User user=(User)session.getAttribute("user");

%>


<!-- side bar -->

         <%if(user==null){ %>
		<div class="sidebar">
			<h1 class="logo">KVFOODS</h1>

			<div class="sideBarMenu">
				<ul>
					<li><a href="index.jsp"><ion-icon name="storeFront-outline"></ion-icon>Home</a></li>
					<li><a href="#"><ion-icon name="information-outline"></ion-icon>About</a></li>
					<li><a href="#"><ion-icon name="chatbubbles-outline"></ion-icon>Contact</a></li>
					<li><a href="login.jsp"><ion-icon name="log-in-outline"></ion-icon>Login</a></li>
					<li><a href="signup.jsp"><ion-icon name="log-in-outline"></ion-icon>SignUp</a></li>
				</ul>
			</div>
		</div>
		<%}else{ %>
		
		<div class="sidebar">
			<h1 class="logo">KVFOODS</h1>

			<div class="sideBarMenu">
				<ul>
					<li><a href="index.jsp"><ion-icon name="storeFront-outline"></ion-icon>Home</a></li>
					<li><a href="#"><ion-icon name="information-outline"></ion-icon>About</a></li>
					<li><a href="#"><ion-icon name="chatbubbles-outline"></ion-icon>Contact</a></li>
					<li><a href="LoginServlet"><ion-icon name="log-in-outline"></ion-icon>Logout</a></li>
				</ul>
			</div>
		</div>
		<%} %>

</body>
</html>