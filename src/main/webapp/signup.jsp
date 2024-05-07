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

<div class="formContainer">

        <h1>KVFOODS</h1>

        <form action="registerServlet" method="post">
            <input type="text" name="userName" placeholder="userName">
            <input type="email" name="email" placeholder="email">
            <input type="password" name="password" placeholder="password">
            <input type="password" name="confirmPassword" placeholder="conirmPassword">
            <input type="submit">
          </form>

     </div>


</body>
</html>