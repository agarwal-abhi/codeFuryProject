<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bug Tracking System</title>
    <link rel="stylesheet" href="styles/css/bootstrap.min.css">
    
</head>
<body style="overflow-y: hidden;">
<script src="styles/js/jquery-3.5.1.js"></script>
    <script src="styles/js/bootstrap.min.js" ></script>   
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Bug Tracking System</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
    </nav>
    <div>
        <!-- Button for report a bug-->
        <div style="display: flex; justify-content: space-between;">
            <div style="display: flex; flex-direction: column; padding-left: 50px; padding-top: 5px;">
            <h1>Username:${userName}</h1>
            <h6>User Email:${userEmail}</h6>
            </div>
            
            <form action="addBug.jsp">
            	<button type="submit" class="btn btn-secondary btn-lg" style="margin: 2%; margin-bottom: 0%;">Report a Bug</button>
            </form>
            
        </div>
    </div>
    <div style="display: flex; height: 100%; width: 100%; justify-content: space-between;">
        <div style="width: 10%; height: 600px; margin-top: 7%; padding-left: 2%;" >
            <div style="display: flex; flex-direction: column;">
                <div style="margin: 5px;">
                	<h4><label>Projects</label></h4>
                	   <%--  <c:forEach var="item" items="${projectList}">
                			<h6>Project Id:${item}</h6>
        				</c:forEach> --%>
                
                
                
                </div>
               </div>
            </div>
            
            
            <span style="height: 600px; border-left: 1px solid black; margin-top: 4%;"></span>
        <div style="display: flex; flex-direction: column; justify-content: space-between; height: 100%; width: 100%; padding-bottom: 5% ; padding-left: 5%; padding-right: 5%; margin-top: 5%;">
            <div style="width: 25%;">
            </div>
            <div style="overflow-x: hidden; overflow-y: scroll;height:600px;">
                <table id="dtVerticalScrollExample" class="table table-striped table-bordered table-sm" cellspacing="0"
                  width="100%" >
                  <thead class="thead-dark" >
                  
	                <tr>
						<th class="th-sm">Bug Id</th>
						<th class="th-sm">Project Id</th>
						<th class="th-sm">Assigned To</th>
						<th class="th-sm">Title</th>
						<th class="th-sm">Description</th>
						<th class="th-sm">Open Date</th>
						<th class="th-sm">Marked For Closing</th>
						<th class="th-sm">Closed On</th>
						<th class="th-sm">Closed By</th>
						<th class="th-sm">Bug Status</th>
						<th class="th-sm">Severity Level</th>
						<th class="th-sm">Created By</th>
					
					</tr>
	                  
                </thead>
            

		<tbody>
			<c:forEach var="bug" items="${listOfBugs}">
			<tr>
				<td><c:out value="${bug.bugId}" /></td>
				<td><c:out value="${bug.projectId}" /></td>
				
				<c:if test="${bug.assignedTo==0}">
					<td><c:out value="NA"/></td>
				</c:if>
				
				<c:if test="${bug.assignedTo!=0}">
					<td><c:out value="${bug.assignedTo}"/></td>
				</c:if>
				
				
		
				<td><c:out value="${bug.bugTitle}" /></td>
				<td><c:out value="${bug.bugDescription}" /></td>
				<td><c:out value="${bug.openDate}" /></td>
				<td><c:out value="${bug.markedForClosing}" /></td>
				
				
				
				 <c:choose>
						
					  <c:when test="${bug.closedOn=='1990-01-01'}">
							<td><c:out value="NA"/></td>  
					  </c:when>
					  
					  <c:otherwise>
					    	<td><c:out value="${bug.closedOn}"/></td>
					  </c:otherwise>
					  
				</c:choose>
				
				
				
			 	 <c:choose>
					  <c:when test="${bug.closedBy==0}">
							<td><c:out value="NA"/></td>  
					  </c:when>
					  
					  <c:otherwise>
					    	<td><c:out value="${bug.closedBy}"/></td>
					  </c:otherwise>
				</c:choose>
				
				<td><c:out value="${bug.bugStatus}" /></td>
				<td><c:out value="${bug.severityLevel}" /></td>
				<td><c:out value="${bug.createdBy}" /></td>
			</tr>
			
			
		</c:forEach>
      	</tbody>           
                </table>
            </div>
        </div>
    </div>


</body>
</html>