<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@page import="com.project.entity.Bug" %>
<%@page import="com.project.entity.User" %>
<%@page import="com.project.entity.Project" %>
<%@ page import="java.util.List" %>

<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);

  if(session.getAttribute("sess")==null)
     // response.sendRedirect("/log");

  %> 
  
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta charset="ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>


<% List<Bug> bugList=(List<Bug>)session.getAttribute("bugList"); %>
<% List<User> userList=(List<User>)session.getAttribute("userList"); %>





<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bug Tracking System</title>
    <link rel="stylesheet" href="styles/css/bootstrap.min.css">
    <script src="styles/js/jquery-3.5.1.js"></script>
    <script src="styles/js/bootstrap.min.js" ></script>    
</head>
<body style="overflow-y: hidden;">
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#"><font size=5>Bug Tracking System</font></a>
    </nav>
    <div>
        <!-- Button for report a bug-->
        <div style="display: flex; justify-content: space-between;">
            <div style="display: flex; flex-direction: column; padding-left: 50px; padding-top: 5px;">
            
            </div>
        </div>
    </div>
     <h1>Bug List</h1>
                <table id="dtVerticalScrollExample" class="table table-striped table-bordered table-sm" cellspacing="0"
                  width="100%" >
                  <thead class="thead-dark" >
                    <tr>
                      <th class="th-sm">Bug ID
                      </th>
                      <th class="th-sm">Project ID
                      </th>
                      <th class="th-sm">Assigned To
                      </th>
                      <th class="th-sm">Title
                      </th>
                      <th class="th-sm">Description
                      </th>
                      <th class="th-sm">Open Date
                      </th>
                       <th class="th-sm">Marked For Closing
                      </th>
                       <th class="th-sm">Closed On
                      </th>
                       <th class="th-sm">Closed By
                      </th>
                       <th class="th-sm">Bug Status
                      </th>
                       <th class="th-sm">Severity Level
                      </th>
                      <th class="th-sm">Created By
                      </th>
                      <th class="th-sm">Assign Developer
                      </th>
                      <th class="th-sm">Close Bug
                      </th>
                    </tr>
                  </thead>
                  <tbody >
                  <c:forEach items="${bugList}" var="iter">
                    <tr height=50>
                      <td align=center><c:out value="${iter.bugId}"/></td>
          <td align=center><c:out value="${iter.projectId}"/></td>
          <td align=center><c:choose>
			  <c:when test="${iter.assignedTo==0}">
					<c:out value="NA"/>  
			  </c:when>
			  
			  <c:otherwise>
			    	<c:out value="${iter.assignedTo}"/>
			  </c:otherwise>
		</c:choose></td>
          <td align=center><c:out value="${iter.bugTitle}"/></td>
          <td align=center><c:out value="${iter.bugDescription}"/></td>
          <td align=center><c:out value="${iter.openDate}"/></td>
           <td align=center><c:out value="${iter.markedForClosing}"/></td>
            <td align=center><c:choose>
			  <c:when test="${iter.closedOn=='1990-01-01'}">
					<c:out value="NA"/>  
			  </c:when>
			  
			  <c:otherwise>
			    	<c:out value="${iter.closedOn}"/>
			  </c:otherwise>
		</c:choose></td>
             <td align=center> <c:choose>
			  <c:when test="${iter.closedBy==0}">
					<c:out value="NA"/>  
			  </c:when>
			  
			  <c:otherwise>
			    	<c:out value="${iter.closedBy}"/>
			  </c:otherwise>
		</c:choose></td>
              <td align=center><c:out value="${iter.bugStatus}"/></td>
               <td align=center><c:out value="${iter.severityLevel}"/></td>
                <td align=center><c:out value="${iter.createdBy}"/></td>
                 <td align=center><form action="<%=request.getContextPath()%>/showBugs/marked">

<input type=hidden value="${iter.bugId}" name="bugId"/>
<c:choose>  
<c:when test="${iter.assignedTo==0}">
<select name="devId">
    <c:forEach var="item" items="${userList}">
        <option value="${item.userId}">${item.userName}</option>
    </c:forEach>
</select>
<input type="submit" class="btn btn-primary" value="Assign">
</c:when>
<c:otherwise>
<select name="devId" disabled="disabled">
    <c:forEach var="item" items="${userList}">
        <option value="${item.userId}">${item.userName}</option>
    </c:forEach>
</select>
<input type="submit" class="btn btn-primary" value="Assign" disabled="disabled">

</c:otherwise>

</c:choose>

</form>
</td>


<td align=center><form action="<%=request.getContextPath()%>/showBugs/closed">

<input type=hidden value="${iter.bugId}" name="bugId"/>
<c:choose>  
<c:when test="${iter.markedForClosing==true}">

<input type="submit" class="btn btn-primary" value="Close">
</c:when>
<c:otherwise>

<input type="submit" class="btn btn-primary" value="Close" disabled="disabled">

</c:otherwise>

</c:choose>

</form>
</td>






 <!--         <td align=center style="border-right:hidden; border-top: hidden; border-bottom: hidden;"><form action="/showBugs">
          <input type=hidden value="${iter.projectId}" name="projectId"/>
          <input type="submit" class="btn btn-primary" value="Show Bugs">
          </form></td> -->
        </tr>
      </c:forEach>
                    
                  </tbody>
                </table>
            </div>
        </div>
    </div>


</body>
</html>






<%-- <table border=2 align=center width=800>
<tr height=50>
<td align=center>Bug ID</td>
<td align=center>Project ID</td>
<td align=center>Assigned To</td>
<td align=center>Title</td>
<td align=center>Description</td>
<td align=center>Open Date</td>
<td align=center>Marked For Closing</td>
<td align=center>Closed On</td>
<td align=center>Closed By</td>
<td align=center>Bug Status</td>
<td align=center>Severity Level</td>
<td align=center>Created By</td>
<td align=center>Assign Developer</td>
<td>Close Bug</td>
</tr>
<c:forEach items="${bugList}" var="iter">
        <tr height=50>
        <td align=center><c:out value="${iter.bugId}"/></td>
          <td align=center><c:out value="${iter.projectId}"/></td>
          <td align=center><c:choose>
			  <c:when test="${iter.assignedTo==0}">
					<c:out value="NA"/>  
			  </c:when>
			  
			  <c:otherwise>
			    	<c:out value="${iter.assignedTo}"/>
			  </c:otherwise>
		</c:choose></td>
          <td align=center><c:out value="${iter.bugTitle}"/></td>
          <td align=center><c:out value="${iter.bugDescription}"/></td>
          <td align=center><c:out value="${iter.openDate}"/></td>
           <td align=center><c:out value="${iter.markedForClosing}"/></td>
            <td align=center><c:choose>
			  <c:when test="${iter.closedOn=='1990-01-01'}">
					<c:out value="NA"/>  
			  </c:when>
			  
			  <c:otherwise>
			    	<c:out value="${iter.closedOn}"/>
			  </c:otherwise>
		</c:choose></td>
             <td align=center> <c:choose>
			  <c:when test="${iter.closedBy==0}">
					<c:out value="NA"/>  
			  </c:when>
			  
			  <c:otherwise>
			    	<c:out value="${iter.closedBy}"/>
			  </c:otherwise>
		</c:choose></td>
              <td align=center><c:out value="${iter.bugStatus}"/></td>
               <td align=center><c:out value="${iter.severityLevel}"/></td>
                <td align=center><c:out value="${iter.createdBy}"/></td>
                 <td align=center><form action="<%=request.getContextPath()%>/showBugs/marked">

<input type=hidden value="${iter.bugId}" name="bugId"/>
<c:choose>  
<c:when test="${iter.assignedTo==0}">
<select name="devId">
    <c:forEach var="item" items="${userList}">
        <option value="${item.userId}">${item.userName}</option>
    </c:forEach>
</select>
<input type="submit" class="btn btn-primary" value="Assign">
</c:when>
<c:otherwise>
<select name="devId" disabled="disabled">
    <c:forEach var="item" items="${userList}">
        <option value="${item.userId}">${item.userName}</option>
    </c:forEach>
</select>
<input type="submit" class="btn btn-primary" value="Assign" disabled="disabled">

</c:otherwise>

</c:choose>

</form>
</td>


<td align=center><form action="<%=request.getContextPath()%>/showBugs/closed">

<input type=hidden value="${iter.bugId}" name="bugId"/>
<c:choose>  
<c:when test="${iter.markedForClosing==true}">

<input type="submit" class="btn btn-primary" value="Close">
</c:when>
<c:otherwise>

<input type="submit" class="btn btn-primary" value="Close" disabled="disabled">

</c:otherwise>

</c:choose>

</form>
</td>






 <!--         <td align=center style="border-right:hidden; border-top: hidden; border-bottom: hidden;"><form action="/showBugs">
          <input type=hidden value="${iter.projectId}" name="projectId"/>
          <input type="submit" class="btn btn-primary" value="Show Bugs">
          </form></td> -->
        </tr>
      </c:forEach>
</table>
</body>
</html> --%>