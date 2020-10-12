<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main Page For Manager</title>
</head>
<body>
<h1>Main Page For Manager</h1>

<h3>Information to Display:</h3>
<h4><c:out value="Email:${userEmail}"/></h4>
<h4><c:out value="Role:${userType}"/></h4>
<h4><c:out value="Last Logged in:${timeStamp}"/></h4>


<h3>List of Projects:</h3>
<%-- <h4><c:out value="Projects:${ProjectList}"/></h4> --%>
<ol >
 <c:forEach items="${ProjectList}" var="ProjectName">
 <a href="showProject.jsp"><li style="list-style-type: disc;">${ProjectName.projectName}</li></a>
 </c:forEach>
</ol>

</body>
</html>