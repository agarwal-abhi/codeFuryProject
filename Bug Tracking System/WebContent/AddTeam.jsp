<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="styles/css/bootstrap.min.css">
    <title>Add team member</title>
   
    
</head>
<body>

    <script src="styles/js/jquery-3.5.1.js"></script>
    <script src="styles/js/bootstrap.min.js" ></script>
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Add teams</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
    </nav>

    <div style="display: flex; height: 100%; width: 100%; justify-content: center;">
    <div style="width: 400px; height: 600px; background-color: #f6f5f5; margin: 7%;" >
    <div class="btn-group-vertical" style="display: flex; flex-direction: column; justify-content: space-between; height: 100%; width: 100%; padding-bottom: 10% ; padding-left: 10%; padding-right: 10%; ">
        <div style="width: 100%; height: 100%; display: flex; flex-direction: column; justify-content: space-evenly;">
            <div style="width: 100%; display: flex; justify-content: center;">
                <h2>Assign Team </h2>
            </div>
        <form action="AddTeamMember" method="POST"><br><br>
        <h2>Choose testers here !</h2><br><br>
            <select class="form-control" name="tester" aria-placeholder="Choose Tester">
                <c:forEach items="${testerList}" var="testerList">
        		<option value="${testerList.userId}">${testerList.userName}</option>
   				 </c:forEach>
          </select>
          <br><br>
<!--             <select class="form-control" aria-placeholder="Choose Developer" style="margin-top: 15px;"> -->
                <h2>Choose developers here !</h2><br><br>
                <c:forEach items="${developerList}" var="developerList">
      				 <input type="checkbox"  name="developer" value= "${developerList.userId}">
					<label > ${developerList.userName}</label><br>
       
   					 </c:forEach>
            </select>
            <div style="display: flex; justify-content: center; margin-top: 15%;">
<!--             <button type="submit" class="btn btn-secondary btn-lg">Submit</button> -->
            <input type="submit" class="btn btn-secondary btn-lg" value="Assign">
            </div>
            <div style="display: flex; justify-content: center; margin-top: 5%;">
               <a href="index.jsp" ><h6>Go Back</h6></a> 
            </div>
          </form>
        </div>
    </div>
    </div>
    </div>
</body>
</html>