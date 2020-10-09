<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post">
<input type="text" placeholder="Enter Name" name="projectName"/>
<input type="text" placeholder="Enter project Discription" name="projectDesc"/>
<input type="date" placeholder="select date" name="startDate"/>
  <select name="tester"  >
    <c:forEach items="${tester}" var="tester">
        <option value="${tester.userId}">${tester.userName}</option>
    </c:forEach>
</select>
<input type="submit" value="Submit"/>
</form>
</body>
</html>