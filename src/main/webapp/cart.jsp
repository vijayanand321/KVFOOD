<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.kvfood.model.User"%>
<%@page import="java.util.Map"%>
<%@page import="com.kvfood.model.CartItem"%>
<%@page import="com.kvfoods.util.CartItemCreator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			
			<h1 style="margin:7px;">Your orders</h1>

    <div class="restaurants">
    
    <%
  
CartItemCreator cartItemCreator=(CartItemCreator)session.getAttribute("cartItemCreator");
    
    Map<Integer, CartItem> cart =cartItemCreator.getCart();
    
    for(CartItem cartItem : cart.values()){
%>
     
            <div class="restaurant">
                <div class="cartItemImage">
                    <img src="images/burger.jpg" />
                </div>
                <div class="cartItemDetails">
                    <div class="cartItemName">
                        <%= cartItem.getItemName() %>
                    </div>
                    <div class="cartItemPrice">
                       <%= cartItem.getPrice() %>
                    </div>
                    <div class="cartItemQuantity">
                        <%=cartItem.getQuantity() %>
                    </div>
                </div>
                <div class="quantityButtonContainer">
                    <button></button>
                    <button></button>
                </div>
                <div class="RemoveButton">
                    <button>
                    <a href="carts?menuId=<%=cartItem.getItemId()%>&operator=-1">Remove</a>
                    </button>
                </div>
            </div>
            
            <% } %>
            
           <button><a href="getRestaurant">Add menu</a></button>
   
    </div>
    
    </div>
    </div>

</body>

<script type="module"
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</html>