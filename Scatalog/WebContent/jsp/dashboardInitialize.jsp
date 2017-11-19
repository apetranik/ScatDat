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

	
	%><!--data-target="#mymodal"-->
	<!-- User info content -->
	<div class="container">
	   	<h2><%=currentUser.getName().getFullName() %></h2><button type ="button" data-toggle="modal"  data-target="#settingsModal" style="background:white; border:none; outline: 0"><i class="fa fa-cog" aria-hidden="true" style ="font-size: 28px"></i></button><button type="button" class="btn btn-link"></button>
	     	<div class="row">
	       		<div class="col-lg-12 mx-center">          
	         		<p class="lead"><%=currentUser.getClassStanding() + ", " +  currentUser.getMajor() + " | " + currentUser.getEmail()%></p>
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
									Vector<Course> coursesTaken = currentUser.getCourseList(); 
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
								<div class="col-md-2">
									<button style="float: right; background:white; border-radius:18px; border-style: solid; outline: 0" type="button" data-toggle="modal" data-target="#courseModal" id="addToCourses">
									+
									</button>
								</div>
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
									Vector<Course> wishlist = currentUser.getWishlist(); 
									for(Course course : wishlist) 
									{
									%>
				      					<%=course.getPrefix() + " " + course.getCourseId() + ": " + course.getCourseName()%><hr />
									<%
									}
									%>
								</div>
								<!-- Right side of wishlist +button -->
								<div class="col-md-2">
									<button style="float: right; background:white; border-radius:18px; border-style: solid; outline: 0" type="button" data-toggle="modal" data-target="#wishlistModal" id="addToWishlist">
									+
									</button>
								</div>
							</div>
						</div>
						<div class="col-md-2">
						</div>
					</div>
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
		
	<!-- Add Course Modal -->
	<div id="courseModal" class="modal fade" role="dialog" data-backdrop="static" data-keyboard="false">
  		<div class="modal-dialog">
   			<!-- Modal content-->  
			<div class="modal-content">
	      		<div class="modal-header">
	      			<h4 class="modal-title">Add Course</h4>
	        			<button type="button" class="close" data-dismiss="modal">&times;</button>
	      		</div> <!-- Close modal header -->
	      		
	      		<!-- Body of Modal -->
	      		<div class="modal-body">
	      			<div class="row">
	      				<div class="col-md-10">
			      			<form name="search">
			  					<div class="form-group">
			   						<label for="search"></label>
			    						<input id="searchText" type="text" class="form-control" aria-describedby="search" placeholder="Scat Dat Course!" oninput="srch();" autocomplete="off"  autofocus/>
			  					</div>
			  				</form>
			  			</div>
			  			<div class="col-md-2">
			  				<button style="float: right; background:white; border-radius:18px; border-style: solid; outline: 0" type="button" data-toggle="modal" data-target="#wishlistModal" id="addToWishlist">
									+
							</button>
			  			</div>
	  				</div>
				</div> <!-- Close modal body -->
				
	     		<div class="modal-footer">
	     			<button type="button" class="btn btn-secondary">Save</button>
	      		</div>
	    		</div>  <!-- Close modal content -->
   	 	</div>
	</div>
	
	<!-- Add To Wishlist Modal -->
	<div id="courseModal" class="modal fade" role="dialog" data-backdrop="static" data-keyboard="false">
  		<div class="modal-dialog">
   			<!-- Modal content-->  
			<div class="modal-content">
	      		<div class="modal-header">
	      			<h4 class="modal-title">Add To Wishlist</h4>
	        			<button type="button" class="close" data-dismiss="modal">&times;</button>
	      		</div> <!-- Close modal header -->
	      		
	      		<!-- Body of Modal -->
	      		<div class="modal-body">
	      			<form name="search">
	  					<div class="form-group">
	   						<label for="search"></label>
	    						<input id="searchText" type="text" class="form-control" aria-describedby="search" placeholder="Scat Dat Course!" oninput="srch();" autocomplete="off"  autofocus/>
	  					</div>
	  				</form>
				</div> <!-- Close modal body -->
				
	     		<div class="modal-footer">
	     			<button type="button" class="btn btn-secondary">Save</button>
	      		</div>
	    		</div>  <!-- Close modal content -->
   	 	</div>
	</div>