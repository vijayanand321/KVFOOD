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

<form action="getRestaurant" method="get">
 <header class="headerContainer" >
  
				<div class="dropDownCity">
					<h1>
						<ion-icon name="location-outline"></ion-icon>
					</h1>
					
					   <select name="city" id="city" >
						<ion-icon name="location-outline"></ion-icon>
						<option value="Bengaluru" selected="selected">Bengaluru</option>
					</select>
					   
					
			     </div>

				<div class="searchContainer" >
					<input type="search" placeholder="search restaurant,dish...."
						name="search">
					<button type="submit">
						<ion-icon name="search-circle-outline"></ion-icon>
					</button>
				</div>
			</header>

</form>
 

</body>

<script type="text/javascript">


   
   
   function getValue() {
	
}
   
</script>
</html>