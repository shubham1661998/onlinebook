<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>

  <meta charset="ISO-8859-1">

  <title>Login_Page</title>

    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />

</head>

<body>

  <html lang="en-US">
  <head>

    <meta charset="utf-8">

    <title>Login</title>

    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,700">

    <!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
 <![endif]-->

  </head>

  <body>
	
    <div class="container">

      <div id="login">

        <form action="loginservlet" method="post">

          <fieldset class="clearfix">
				<h1 class="heading">Online Book Store</h1>
            <p><span class="fontawesome-user"></span><input type="text" name="userid" placeholder="Username"  required></p> 
            <p><span class="fontawesome-lock"></span><input type="password"  name="pass" placeholder="********"  required></p> 
            <p><input type="submit" value="Sign In"></p>
            <%@include file="error.jsp" %>

          </fieldset>

        </form>
			
        <p>Not a member? <a href="registration.jsp">Sign up now</a><span class="fontawesome-arrow-right"></span></p>

      </div> <!-- end login -->

    </div>

  </body>
</html>

</body>

</html>