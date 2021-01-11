<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Bug Page</title>
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

    <div style="display: flex; height: 100%; width: 100%; justify-content: center;">
    <div style="width: 400px; height: 600px; background-color: #f6f5f5; margin: 7%;" >
    <div class="btn-group-vertical" style="display: flex; flex-direction: column; justify-content: space-between; height: 100%; width: 100%; padding-bottom: 10% ; padding-left: 10%; padding-right: 10%; ">
        <div style="width: 100%; height: 100%; display: flex; flex-direction: column; justify-content: space-evenly;"></div>
            <div style="width: 100%; display: flex; justify-content: center;">
                <h2>Add a Bug</h2>
            </div>
            
            
            
            
            
            
            
 <form action="<%=request.getContextPath()%>/tester/addBug" method="post">

							<div class="form-group">
							<label>ProjectId:</label>
							
							<select name="projectId" class="form-control">
							    <c:forEach var="item" items="${projectList}">
							        <option  value="${item}">${item}</option>
							    </c:forEach>
							</select>
							</div>
							
							
							<div class="form-group">
							<label>Bug Title:</label>
							<input type="text" id="bugTitle" name="bugTitle" required="required" placeholder="Bug Title" class="form-control">
							
							</div>
							
							<div class="form-group">
							<label>Bug Description:</label>
							<input type="text" id="bugDescription" name="bugDescription" required="required" placeholder="Bug Description" class="form-control">
							</div>
							
							<div class="form-group">
							
							<label>Severity Level:</label>
							
								<select id="severityLevel" name="severityLevel" class="form-control">
								  <option value="trivial">Trivial</option>
								  <option value="minor">Minor</option>
								  <option value="major">Major</option>
								  <option value="critical">Critical</option>
								</select>
							</div>
							
							
							
							<div style="display: flex; justify-content: center; margin-top: 15%;">
							<input type="submit" value="SUBMIT"  class="btn btn-secondary btn-lg">
							
							</div>





</form>
 </div>
    </div>
    </div>
    
</body>
</html>