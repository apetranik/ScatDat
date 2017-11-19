<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="scatalogObjects.User, scatalogObjects.Course, java.util.Vector"%>

    
<%
	User otherUser = (User)session.getAttribute("otherUser");
	if(otherUser == null) {
		//redirect to login page
	    //response.sendRedirect(request.getContextPath() + "/html/dashboard.html");
	}
	//delete lines below once we get login page working
		otherUser = new User("Apurva", "Gandhi", "Junior", "apurvaga@usc.edu", "Computer Engineering and Computer Science");
		otherUser.addCourseTaken(new Course("Discrete Mathematics", 170, "Introduction to logic, proofs, runtime, probability, number theory and cryptography with computer science applications in mind", "CSCI",
				4, 5, 3, 3, 0)); 
		otherUser.addCourseTaken(new Course("Probability Theory", 407, "Discrete probability, distributions, random vairables, stochastic processes, law of large numbers", "MATH",
				5, 5, 1, 3, 0)); 
		otherUser.addToWishlist(new Course("Data Structures", 104, "Redekopp stuff", "CSCI",
				5, 5, 1, 1, 0)); 
		//delete above lines once we get login page and queries working
%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	
    <title>Scatalog</title>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
    
    <!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
	<!--<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">-->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	
   	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
   	
    <!-- <link href="style.css" rel="stylesheet"> -->
	<link href="../css/dashboard.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
	
    <!-- Custom styles for this template -->
    <script type="text/javascript" src="js/materialize.min.js"></script>
    
    </head>
    <body>
    <div class = "nav-bar">
    		<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top justify-content-center ">
        		<a href="/" class="navbar-brand d-flex w-20 mr-auto">Scatalog</a>
    			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsingNavbar3">
        			<span class="navbar-toggler-icon"></span>
    			</button>
    			<div class="navbar-collapse collapse" id="collapsingNavbar3">
        			<ul class="navbar-nav mr-auto w-100 justify-content-center">
            			<li class="nav-item">
                			<a class="nav-link" href="<%=request.getContextPath() + "/html/index.html"%>">HOME</a>
            			</li>
            			<li class="nav-item">
                			<a class="nav-link" href="<%=request.getContextPath() + "/html/explore.html"%>">EXPLORE</a>
            			</li>
            			<li class="nav-item">
               			<a class="nav-link" href="<%=request.getContextPath() + "/html/department.html"%>">BY DEPARTMENT</a>
            			</li>
        			</ul>
        			<ul class="nav navbar-nav ml-auto w-100 justify-content-end">
           			<li class="nav-item">
                			<a class="nav-link" href="<%=request.getContextPath() + "/html/dashboard.html"%>">User Dashboard</a>
           			</li>
            			<li class="nav-item">
                			<a class="nav-link" href="<%=request.getContextPath() + "/html/login.html"%>">Sign-In</a>
            			</li>  
        			</ul>
    			</div>
    		</nav>
	</div>
    	<!-- User Information -->
 	<section id="about">
 		<!-- Dashboard JSP content in here -->	
 		<div class="container">
	   	<h2><%=otherUser.getName().getFullName() %></h2>
	     	<div class="row">
	       		<div class="col-lg-12 mx-center">          
	         		<p class="lead"><%=otherUser.getClassStanding() + ", " +  otherUser.getMajor() + " | " + otherUser.getEmail()%></p>
					<!-- Courses Taken/Wishlist Body -->
					<div class="row">
						<!-- Courses Taken -->
						<div class="col-md-5">
							<div class="row">
								<!-- Left side -->
								<div class="col-md-10">
									<b>Courses Taken</b>
									<hr>
									<%
									Vector<Course> coursesTaken = otherUser.getCourseList(); 
									for(Course course : coursesTaken) 
									{
									%>
					      				<%=course.getPrefix() + " " + course.getCourseId() + ": " + course.getCourseName()%>
					      			<hr />
									<%
									}
									%>
								</div>
								<!-- Right side of courses taken + button -->
							</div>
						</div>
						<!-- Wishlist content -->
						<div class="col-md-5">
							<div class="row">
								<!-- Left side of wishlist -->
								<div class="col-md-10">
									<b>Wishlist</b>
									<hr>
									<%
									Vector<Course> wishlist = otherUser.getWishlist(); 
									for(Course course : wishlist) 
									{
									%>
				      					<%=course.getPrefix() + " " + course.getCourseId() + ": " + course.getCourseName()%><hr />
									<%
									}
									%>
								</div>
							</div>
						</div>
						<div class="col-md-2">
						</div>
					</div>
	       		</div>
	     	</div>
	</section>
    
    </body>
    