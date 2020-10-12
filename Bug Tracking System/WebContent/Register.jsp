<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Page</title>
    <link rel="stylesheet" href="styles/css/bootstrap.min.css">
    <script src="styles/js/jquery-3.5.1.js"></script>
    <script src="styles/js/bootstrap.min.js" ></script>
</head>
<body>
    
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Register Page</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
    </nav>
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
    <div style="display: flex; height: 100%; width: 100%; justify-content: center;">
    <div style="width: 400px; height: 600px; background-color: #f6f5f5; margin: 7%;" >
    <div class="btn-group-vertical" style="display: flex; flex-direction: column; justify-content: space-between; height: 100%; width: 100%; padding-bottom: 10% ; padding-left: 10%; padding-right: 10%; ">
        <div style="width: 100%; height: 100%; display: flex; flex-direction: column; justify-content: space-evenly;">
            <div style="width: 100%; display: flex; justify-content: center;">
                <h2>Register User</h2>
            </div>
        <form role="form" action="RegisterServlet" method="post">
            <div class="form-group">
              <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Email" name="userEmail" required>
            </div>
            <div class="form-group">
            <select class="form-control" aria-placeholder="Choose Role" name="userType">
            <option value="Manager">Manager</option>
			  <option value="Developer">Developer</option>
			  <option value="Tester">Tester</option>
            </select>
            </div>
            <div class="form-group">
              <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Enter Password" name="userPassword" required>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Confirm Password" name="cnfPassword" required>
            </div>
            <div style="display: flex; justify-content: center; margin-top: 15%;">
            <button type="submit" class="btn btn-secondary btn-lg">Submit</button>
            </div>
            <div style="display: flex; justify-content: center; margin-top: 5%;">
                <h6>Go Back</h6>
            </div>
          </form>
        </div>
    </div>
    </div>
    </div>
</body>
</html>