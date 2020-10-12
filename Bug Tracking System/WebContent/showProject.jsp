<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.project.entity.Project"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <a class="navbar-brand" href="#">Bug Tracking System</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
    </nav>
    <div>
        <!-- Button for report a bug-->
        <div style="display: flex; justify-content: space-between;">
            <div style="display: flex; flex-direction: column; padding-left: 50px; padding-top: 5px;">
            <h1>Project Name</h1>
            </div>
           
        </div>
    </div>
    <div style="display: flex; height: 100%; width: 100%; justify-content: space-between;">
        
           
        <div style="display: flex; flex-direction: column; justify-content: space-between; height: 100%; width: 100%; padding-bottom: 5% ; padding-left: 5%; padding-right: 5%; margin-top: 5%;">
            <div style="width: 25%;">
            </div>
            <div style="overflow-x: hidden; overflow-y: scroll;height:600px;">
                <table id="dtVerticalScrollExample" class="table table-striped table-bordered table-sm" cellspacing="0"
                  width="100%" >
                  <thead class="thead-dark" >
                    <tr>
                      <tr>
                      <th class="th-sm">Project ID
                      </th>
                      <th class="th-sm">Project Name
                      </th>
                      <th class="th-sm">Project Description
                      </th>
                      <th class="th-sm">Project StartDate
                      </th>
                      <th class="th-sm">Project Status
                      </th>
                     </tr>
                    </tr>
                  </thead>
                  <tbody >
                   <tr>
                      <td>${Id}</td>
  					  <td>${Name}</td>
   					  <td>${Description}</td>
  					  <td>${Date}</td>
  					  <td>${Status}</td>
    				  <td align=center style="border-right:hidden; border-top: hidden; border-bottom: hidden;">
          			  <form action="showBugs">
          			  <input type=hidden value="${Id}" name="projectId"/>
         			  <input type="submit" class="btn btn-primary" value="Show Bugs">
          			  </form></td>
                    </tr>
                   
                   
                  </tbody>
                </table>
            </div>
        </div>
    </div>


</body>
</html>