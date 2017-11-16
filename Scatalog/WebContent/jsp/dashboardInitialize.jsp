<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="scatalogObjects.User, scatalogObjects.Course, java.util.Vector"%>

    
<%
	User currentUser = (User)session.getAttribute("currentUser");
	if(currentUser == null) {
		//redirect to login page
		
	}
	
	//delete lines below once we get login page working
	currentUser = new User("Apurva", "Gandhi", "Junior", "apurvaga@usc.edu", "Computer Engineering and Computer Science");
	currentUser.addCourseTaken(new Course("Discrete Mathematics", 170, "Introduction to logic, proofs, runtime, probability, number theory and cryptography with computer science applications in mind", "CSCI",
			4, 5, 3, 3, 0)); 
	currentUser.addCourseTaken(new Course("Probability Theory", 407, "Discrete probability, distributions, random vairables, stochastic processes, law of large numbers", "MATH",
			5, 5, 1, 3, 0)); 
	currentUser.addToWishlist(new Course("Data Structures", 104, "Redekopp stuff", "CSCI",
			5, 5, 1, 1, 0)); 
	//delete above lines once we get login page and queries working

	
%>
	<div class="container">
	   		<h2><%=currentUser.getName().getFullName() %></h2><button type ="button" style="background:white; border:none; outline: 0"><i class="fa fa-cog" aria-hidden="true" style ="font-size: 28px"></i></button><button type="button" class="btn btn-link"></button>
	     		<div class="row">
	       		<div class="col-lg-12 mx-center">          
	         			<p class="lead"><%=currentUser.getClassStanding() + ", " +  currentUser.getMajor() + " | " + currentUser.getEmail()%></p>
			<table class="table">
				<tr>
					<td>
						<table>
							<tr><b>Courses Taken</b></tr><hr />
<%
						Vector<Course> coursesTaken = currentUser.getCourseList(); 
						for(Course course : coursesTaken) {
%>
		      				<tr><%=course.getPrefix() + " " + course.getCourseId() + ": " + course.getCourseName()%></tr><hr />
<%
						}
						
%>						
							
						</table>
					</td>
					<td>
						<table>
							<tbody>
							<tr><b>Wishlist</b></tr><hr />

<%
						Vector<Course> wishlist = currentUser.getWishlist(); 
						for(Course course : wishlist) {
%>
	      					<tr><%=course.getPrefix() + " " + course.getCourseId() + ": " + course.getCourseName()%></tr><hr />
<%
						}
%>
						</tbody>
						</table>
					</td>
				</tr>
			</table>
	       		</div>
	     		</div>

	 			<!-- Search Users -->
	  		<div class="row">
	  			<!-- Main section -->
		<div class="col-md-8">
			<form name="search">
	  			<div class="form-group row">
	  				<div class="col-xs-4">
	   					<label for="search"></label>
	    					<input id="searchText" type="text" class="form-control" aria-describedby="search" placeholder="Search Users" oninput="srch();"/>
	    					<div id="searchResults"></div>
	  				</div>
	  			</div>
	  		</form>	
		</div>
		<!-- Side Col Right -->
			<div class="col-md-4"></div>	
		</div>
	</div>