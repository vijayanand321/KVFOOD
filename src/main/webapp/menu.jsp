
<%@page import="com.kvfood.model.User"%>
<%@page import="com.kvfoods.util.CartItemCreator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List"%>
<%@page import="com.kvfood.model.Menu"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="master.css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>KVFOODS</title>
</head>
<body>

<%

User user=(User)session.getAttribute("user");

%>
<%String restaurantName= (String)request.getAttribute("restaurantName"); %>

<div class="container">

		
		<jsp:include page="sidebar.jsp" />
           

		<div class="main">
			
			<jsp:include page="header.jsp" />
			
			<h1 style="margin:8px;">${restaurantName }</h1>

             <div class="restaurants">
			
			<%
			  List<Menu> menuList =(List<Menu>)request.getAttribute("menuList");
			 
			   session.setAttribute("cartItemCreator", CartItemCreator.getCartItemCreator());
			if(menuList!=null && menuList.size()>0){
			for(Menu menu : menuList){ %>	
			
			   <div class="restaurant">
			   
			   <div class="restaurantImage">
						<img src="<%= menu.getMenuImagePath() %>" alt="biriyani">
					</div>
					
					<div class="header">
						<h1><%= menu.getItemName()  %></h1>
						<p><%= menu.getDescription() %></p>
					</div>
					<div class="rating">
						<ion-icon name="cash"></ion-icon>
						<%= menu.getPrice() %>
					</div>

					<div class="addButton">
						<button type="submit">
						<%if(user!=null) {%>
					    <a href="carts?menuId=<%=menu.getMenuId()%>&operator=1">ADD</a>
					    <%}else {%>
					    <a href="login.jsp">ADD</a>
					    <%} %>
						</button>
					</div>
				</div>
		     <% }
			}
			else{
				out.println("<h1 style=\"display:block;\">no menu found for restaurant</h1>");
			}
			%>
		     				
			</div>	
			
		</div>
	</div>

</body>

<script type="module"
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</html>