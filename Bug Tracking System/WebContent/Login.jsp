<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="styles/css/bootstrap.min.css">
    
</head>

<body>

    <script src="styles/js/jquery-3.5.1.js"></script>
    <script src="styles/js/bootstrap.min.js" ></script>
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Login Page</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
    </nav>

    <div style="display: flex; height: 100%; width: 100%; justify-content: center;">
    <div style="width: 400px; height: 600px; background-color:#f6f5f5; margin: 7%;" >
    <div class="btn-group-vertical" style="display: flex; flex-direction: column; justify-content: space-between; height: 100%; width: 100%; padding-bottom: 10% ; padding-left: 10%; padding-right: 10%; ">
        <div style="width: 100%; height: 100%; display: flex; flex-direction: column; justify-content: space-evenly;">
            <div style="width: 100%; display: flex; justify-content: center;">
                <h2>Login</h2>
            </div>
        <form action="LoginServlet" method="post">
            <div class="form-group">
              <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Email" name="enteredEmail" required>
            </div>
            <div class="form-group">
              <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Enter Password" name="enteredPassword" required>
            </div>
            <div style="display: flex; justify-content: center; margin-top: 15%;">
            <button type="submit" value="Login" id="subButton" class="btn btn-secondary btn-lg">login</button>
            </div>
            <div style="display: flex; justify-content: center; margin-top: 5%;">
                <h6><a href="#">Forgot Password?</a></h6>
            </div>
          </form>
        </div>
    </div>
    </div>
    </div>
</body>
</html>