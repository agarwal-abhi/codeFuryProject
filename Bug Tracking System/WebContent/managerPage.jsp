<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.project.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manager Page</title>
    <link rel="stylesheet" href="styles/css/bootstrap.min.css">
    
</head>
<body>
<script src="styles/js/jquery-3.5.1.js"></script>
    <script src="styles/js/bootstrap.min.js" ></script>
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Bug Tracking System</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
    </nav>
    <div>
        <div style="display: flex; justify-content: space-between;">
            <div style="display: flex; flex-direction: column; padding-left: 50px; padding-top: 5px;">
            <h1>${userName}</h1>
            <h6>${userEmail}</h6>
            <h6>${userType}</h6>
            <h6>${timeStamp}</h6>
            </div>
            <a href="AddProject.jsp" class="btn btn-secondary btn-lg" style="margin: 2%; margin-bottom: 0%; height: 50px;">Add New Project</a>
        </div>
    </div>
    <div>
        <div class="row" style="margin-left: 50px; margin-right: 50px; margin-top: 100px;">
            <div class="col">
                <div class="card" style="width: 90%;height: 500px; ">
                    <div class="card-body" style="display: flex; flex-direction: column;justify-content:space-between ;">
                      <h5 class="card-title"><a href="ProjectServlet">Show list of all Projects</a></h5>
                      <p class="card-text">
                      <ol >
                       <c:forEach items="${ProjectList}" var="ProjectName">

               				 <li ><form action="<%=request.getContextPath()%>/ProjectServlet/projectDetails">
       						 <input type=hidden value="${ProjectName.projectId}" name="projectId"/>
          					 <input type="submit" class="btn btn-secondary btn-lg" style="margin: 2%; margin-bottom: 0%; height: 50px;" value="${ProjectName.projectName}">
         					 </form></li>
 							</c:forEach>
						</ol></p>
						<br><br>
                       <a href="Login.jsp" class="btn btn-secondary btn-lg" style="margin: 2%; margin-bottom: 0%; height: 50px;">Back</a>
                      
                    </div>
                  </div>
            </div>
            
            
            </div>
        </div>
    </div>
</body>
</html>


