<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Registration</title> 
</head>
<body>

<% if(request.getAttribute("userAlreadyExistsError")!=null){ %>
	<div style="text-align:center"><strong>User already Registered!</strong></div>
<%} %>
<%request.setAttribute("userAlreadyError",null); %>

<% if(request.getAttribute("userDoesNotExistsError")!=null){ %>
	<div style="text-align:center"><strong>No such user exists!</strong></div>
<%} %>
<%request.setAttribute("userDoesNotError",null); %>

<% if(request.getAttribute("passwordError")!=null){ %>
<div style="text-align:center"><strong>Passwords do not match</strong></div>
<%} %>
<%request.setAttribute("passwordError",null); %>

<form role="form" action="RegisterServlet" method="post">
            <input placeholder="Email Address" type="email" name="userEmail" required><br>
            <label for="cars">Role</label>
			<select name="userType">
			  <option value="Manager">Manager</option>
			  <option value="Developer">Developer</option>
			  <option value="Tester">Tester</option>
			</select><br>
            <input placeholder="Password" type="password" name="userPassword" required><br>
            <input placeholder="Re-enter Password" type="password" name="cnfPassword" required><br>
        	<button type="submit">Sign in</button>
</form>

</body>
</html>