
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.kvfood.model.*"%>
<%@ page import="com.kvfood.service.*"%>
<%@page import="java.util.Date"%>

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

	<div class="container">
	
       
		<jsp:include page="sidebar.jsp" />
              
       		<div class="main">

			<jsp:include page="header.jsp" />
			
			<%if(user!=null){ %>
			<h1>welcome <%=user.getUserName() %></h1>
                 <%}%>
                 
			<div class="restaurants">
                 
				<%
				String all="all";
				request.setAttribute("all",all );
				List<Restaurant> allRestaurant = (List<Restaurant>) request.getAttribute("allRestaurant");
				if (allRestaurant == null) {
					try {
						RequestDispatcher dispatcher = request.getRequestDispatcher("/getRestaurant");
						dispatcher.include(request, response);
						allRestaurant = (List<Restaurant>) request.getAttribute("allRestaurant");
					} catch (Exception e) {
						out.println("<h1>Error : " + e.getMessage() + "</h1>");
					}
				}
				%>
				<%
				if (allRestaurant != null) {
					for (Restaurant restaurant : allRestaurant) {
				%>

				<a href="menus?restaurantId=<%=restaurant.getRestaurantId()%>"
					class="restaurantClick" style="text-decoration: none;">
					<div class="restaurant">
						<div class="restaurantImage">
							<img src="<%=restaurant.getImagePath()%>" alt="firewood">
						</div>
						<div class="header">
							<h1><%=restaurant.getRestaurantName()%></h1>
							<p>amazing restaurant</p>
						</div>
						<div class="rating">
							<ion-icon name="star-outline"></ion-icon>
							<%=restaurant.getRating()%>
						</div>
					</div>
				</a>

				<%
				}
				} else {
				out.println("<h1>no restaurants found</h1>");
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