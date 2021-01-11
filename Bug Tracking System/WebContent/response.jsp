<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Response page</title>
</head>
<body>
<!-- both failure and sucess or other exceptions messages can be displayed here -->
<h1>Status</h1>
<h2>${requestScope.message}</h2>
<a href="index.jsp">Home</a>
</body>
</html>