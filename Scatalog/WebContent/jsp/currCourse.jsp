<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="scatalogObjects.*,java.util.Vector,java.util.Date, java.util.ArrayList, database.Database, java.text.DecimalFormat"
	
%>

<% 
	
	//remove comment below later
	 
	User currentUser = (User)session.getAttribute("currentUser");
	String username = "zhengxix";
	//String currentUsername = currentUser.getUsername();
	
	String prefix = "CSCI";
	int number = 100;
	int courseId;
	ArrayList<Course> courses = (ArrayList<Course>)session.getAttribute("courses"); 
	Database db = new Database();
	if(courses == null) {
		courses = db.queryCourses(); //SQL Query
		session.setAttribute("courses", courses);
	}
	
	courseId = db.returnCourseID(prefix, number);
	System.out.println(courseId);
	DecimalFormat df = new DecimalFormat("#0.0");
	
	Course currCourse = null;
	for(Course c: courses){

		if(c.getPrefix().equals(prefix)){
			
			if(c.getCourseId() == number){
				System.out.println(c.getCourseId());
				
				currCourse = c;
				break;
			}
		}
	}
	
	
	
	
	ArrayList<Review> currReviewList = new ArrayList<>();

	//currReviewList = currCourse.getReviews();
	currReviewList = db.queryAllReview(courseId);
	currCourse.setReviews(currReviewList);
	currCourse.updateOverallScore();
	
	 
	//test course	
	/* String courseName = "Principles of Software Development";
	int courseId = 201;
	String description = "";
	String prefix = "CSCI";
	double enjoyment = 0.0;
	double value = 0.0;
	double workload = 0.0;
	double difficulty = 0.0;
	int type = 1;
	
	Course currCourse = new Course(courseName,courseId ,description ,prefix , enjoyment, value, workload, difficulty, 1);
	ArrayList<Review> currReviewList = new ArrayList<>(); //TEST
	Date d = new Date();
	Review test1 = new Review("Hellooooooooo", new Score(2.0, 2.0, 3.0, 4.0), new Date(), "zhengxix", new CourseTime("Spring","2015"), null, new Name("Jeffrey","Miller"));
	Review test2 = new Review("Hiiiiii 201", new Score(4.0, 4.0, 3.0, 4.0), new Date(), "cote", new CourseTime("Spring","2017"), null, new Name("Aaron","Cote"));
	Review test3 = new Review("Trash course", new Score(1.0, 1.0, 2.0, 2.0), new Date(), "jeffrey", new CourseTime("Fall","2017"), null, new Name("Jeffrey","Miller"));

	currReviewList.add(test1);
	currReviewList.add(test2);
	currReviewList.add(test3);
	currCourse.setReviews(currReviewList);
	currCourse.updateOverallScore(); */
	
	
	//variables
	String fullCourseName = currCourse.getPrefix() + " " + currCourse.getCourseId();
	
	




%>


<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Scatalog</title>

    <!-- Bootstrap core CSS -->
   <!--  	<script type="text/javascript" src="../js/course.js"></script> -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
    
     <!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
	<!--<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">-->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
   	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
    <link href="../css/course.css" rel="stylesheet">
    
    
    <!-- script -->
    <script>
    function initialize(){
         	
    	
      	var xhttp = new XMLHttpRequest(); 
		var sendStr = "../jsp/courseTest.jsp";
		xhttp.open("GET", sendStr, false); 
		xhttp.send(); 
		//if(this.responseText.trim() != "")
		document.getElementById("reviewlist").innerHTML = xhttp.responseText;
        
    	  
    }
    
    function submitReview(){
    	
    		
    		var xhttp = new XMLHttpRequest(); 
		var sendStr = "../jsp/courseTest.jsp?";
		sendStr += "difficulty=" + document.myform.difficulty.value;
		sendStr += "&coursevalue=" + document.myform.coursevalue.value;
		sendStr += "&enjoyment=" + document.myform.enjoyment.value;
		sendStr += "&workload=" + document.myform.workload.value;
		sendStr += "&term=" + document.myform.term.value;
		sendStr += "&year=" + document.myform.year.value;
		sendStr += "&professor=" + document.myform.professor.value;
		sendStr += "&review=" + document.myform.reviewbox.value;
		sendStr += "&currentUser=" + "<%=username%>";
		sendStr += "&courseid=" + "<%=courseId%>";
		alert(sendStr);
		xhttp.open("GET", sendStr, false); 
		xhttp.send();
		document.getElementById("reviewlist").innerHTML = xhttp.responseText;
		updateNum(); //change the number of review
		document.getElementById("closeModal").click();
		document.getElementById("difficulty").value = 0;
		document.getElementById("coursevalue").value = 0;
		document.getElementById("enjoyment").value = 0;
		document.getElementById("workload").value = 0;
		document.getElementById("term").value = "";
		document.getElementById("year").value = "";
		document.getElementById("professor").value = "";
		document.getElementById("reviewbox").value = "";
		
		
    	
    }
    
    function updateNum(){
    		var xhttp = new XMLHttpRequest(); 
		var sendStr = "../jsp/updateCourseScoreDiv.jsp?";
		sendStr += "&prefix=" + "<%=prefix%>";
		sendStr += "&number=" + "<%=number%>";
		xhttp.open("GET", sendStr, false);
		xhttp.send();
		document.getElementById("courseInfo").innerHTML = xhttp.responseText;
		
    		return;
    }
    
    
    
    </script>
    

    <!-- Custom styles for this template -->
    <style>
      body {
        padding-top: 54px;
      }
      @media (min-width: 992px) {
        body {
          padding-top: 56px;
        }
      }

    </style>

  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top justify-content-center ">
        <a href="/" class="navbar-brand d-flex w-20 mr-auto">Scatalog</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsingNavbar3">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar-collapse collapse" id="collapsingNavbar3">
        <ul class="navbar-nav mr-auto w-100 justify-content-center">
            <li class="nav-item">
                <a class="nav-link" href="../html/index.html">HOME</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../html/explore.html">EXPLORE</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="#">BY DEPARTMENT</a>
            </li>
        </ul>
        <ul class="nav navbar-nav ml-auto w-100 justify-content-end">
            <li class="nav-item">
                <a class="nav-link" href="../html/dashboard.html">User Dashboard</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../html/login.html">Sign-In</a>
            </li>
            
        </ul>
    </div>
    </nav>

    <!-- Page Content -->
   <div class="container">
		<div class="row">
			<div class="col-lg-6" id="courseInfo"> <b><%=fullCourseName %></b> &nbsp;&nbsp; <%=df.format(currCourse.getOverallScore().getOverallRating())%> &nbsp;&nbsp; (<%=currReviewList.size() %> reviews)</div>

			<div class="col-lg-3" id="info">				
				<div class="dropdown">
  				<button class="btn btn-sm dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    					Dropdown
  				</button>
  				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    					<a class="dropdown-item" href="#">Sort by Rating</a>
    					<a class="dropdown-item" href="#">Sort by Term</a>
   				 	<a class="dropdown-item" href="#">Sort by Date</a>
  				</div>
  				</div>
			</div>
					
			<div class="col-lg-3" id="info">
				<div class="btn-group">
 					 <button type="button" class="btn btn-default btn-sm">Ascending</button>
 					 <button type="button" class="btn btn-default btn-sm">Descending</button>
				</div>			
			</div>
			
			
		</div>
		<div class="row">
			<div class="col-lg-3" id="info">
			
			<!-- Modal -->
			<button type="button" class="btn btn-default btn-sm"  data-toggle="modal" data-target="#myModal">Submit Review</button>			
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  				<div class="modal-dialog modal-lg" role="document">
    					<div class="modal-content">
      					 <div class="modal-header">
      					    <h4 class="modal-title" id="myModalLabel">Tell us about <%=fullCourseName %>..</h4>
        						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        						
     					 </div>
      					 <div class="modal-body">			 
       					 	<form name= "myform" class="form-horizontal">
    								<div class="form-group row">
       					 			<label class="col-lg-4 col-form-label">Difficulty:</label>
       					 			<div class="col-lg-2"></div>
    									<div class="col-lg-6">
      									<select id="difficulty" name="difficulty" class="form-control">
  										<option value=0>Select</option>
										<option value=1 >1</option>
										<option value=2 >2</option>
										<option value=3 >3</option>
										<option value=4 >4</option>
										<option value=5 >5</option>									
									</select>
    									</div>
    								</div>
    								<div class="form-group row">
       					 			<label class="col-lg-4 col-form-label">Value: </label>
       					 			<div class="col-lg-2"></div>
    									<div class="col-lg-6">
      									<select id="coursevalue" name="coursevalue" class="form-control">
  										<option value=0>Select</option>
										<option value=1 >1</option>
										<option value=2 >2</option>
										<option value=3 >3</option>
										<option value=4 >4</option>
										<option value=5 >5</option>									
									</select>
    									</div>
    								</div>
    								<div class="form-group row">
       					 			<label class="col-lg-4 col-form-label">Enjoyment: </label>
       					 			<div class="col-lg-2"></div>
    									<div class="col-lg-6">
      									<select id="enjoyment" name="enjoyment" class="form-control">
  										<option value=0>Select</option>
										<option value=1 >1</option>
										<option value=2 >2</option>
										<option value=3 >3</option>
										<option value=4 >4</option>
										<option value=5 >5</option>									
										</select>
    									</div>
    								</div>
    								<div class="form-group row">
       					 			<label class="col-lg-4 col-form-label">Workload: </label>
       					 			<div class="col-lg-2"></div>
    									<div class="col-lg-6">
      									<select id="workload" name="workload" class="form-control">
  										<option value=0>Select</option>
										<option value=1 >1</option>
										<option value=2 >2</option>
										<option value=3 >3</option>
										<option value=4 >4</option>
										<option value=5 >5</option>									
										</select>
    									</div>
								</div>
								<div class="form-group row">
       					 			<label class="col-lg-4 col-form-label">Term: </label>
       					 			<div class="col-lg-4">
      									<select id="term" name="term" class="form-control">
  										<option value="0">Select Term</option>
  										<option value="0" >Spring</option>
						　　				<option value="1" >Summer</option>
										<option value="2" >Fall</option>									
										</select>
    									</div>
    									<div class="col-lg-4">
      									<select id="year" name="year" class="form-control">
  										<option value="0">Select Term</option>
  										<option value="2017" >2017</option>
  										<option value="2016" >2016</option>
  										<option value="2015" >2015</option>
										<option value="2014" >2014</option>
										<option value="2013" >2013</option>									
										</select>
    									</div>
								</div>
								<div class="form-group row">
       					 			<label class="col-lg-4 col-form-label">Professor: </label>
       					 			<div class="col-lg-4"></div>
    									<div class="col-lg-4">
      									<input type="text" class="form-control" id="professor" name="professor" placeholder="Enter your professor's name..">
    									</div>
								</div>
								<div class="form-group row">
									<label class="col-lg-4 col-form-label">Review: </label>								
								</div>
								<div class="form-group row">
									<div class="col-lg-12">
										<textarea class="form-control" id="reviewbox" name="reviewbox" placeholder="Enter you reivew.."></textarea>
									</div>															
								</div>
								<div class="form-group row">
									<div class="col-lg-10">
      									<button type="button" class="btn btn-secondary" onclick="submitReview()">Submit</button>
   			 						</div>
								</div>
												 											
       					 	</form>
       					 
     					 </div>
     					 <div class="modal-footer">
        					 	<button id="closeModal" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      					</div>
   				 </div>
  			</div>
			</div>
		</div>
			
		
			
			
		</div>
		<div class="row" id= "description">
			<div class="col-lg-12" id="info">
			<p><%=currCourse.getDescription() %></p>
			</div>
		</div>
		
		<!-- rows below can be regarded as a block for a single review, put it in a loop to print all reviews. Please don't forget to change the 
			hard-coded things to SQL variables -->
		<section id="reviewlist">
			<%for(Review currReview: currReviewList){ 
				Score currScore = currReview.getScore();
				Name currProf = currReview.getProfessor();
				CourseTime currCT = currReview.getCourseTime();
				String termstr = "";
				String termInt = currCT.getTerm();
				if(termInt.equals("0")){
					termstr = "Spring";
				}else if(termInt.equals("1")){
					termstr = "Summer";
				}else if(termInt.equals("2")){
					termstr = "Fall";
				}
	 
%>
			<div class="row" id= "reviewerSection">
				<div class="col-lg-3" id="reviewer">
					<div class = "row">
						<div class="col-lg-6"><b><%=currReview.getUsername()%></b></div>
						<div class="col-lg-6 text-right"><b>Total: <%=df.format(currScore.getOverallRating())%></b></div>
					</div>		
				</div>
				<div class="col-lg-5"></div>
				<div class="col-lg-4 " id="instrutor">
					<p class="text-center"><%=currProf.getFname() + " " + currProf.getLname() %> | <%=termstr + " " + currCT.getYear()%></p>
				</div>
			</div>
		
			<div class="row" id= "reviewSection">
				<div class="col-lg-3" id="points">
					<p class="text-right">Difficulty:  <%=currScore.getDifficulty()%></p>
					<p class="text-right">Value:      <%=currScore.getValue()%></p>
					<p class="text-right">Enjoyment:  <%=currScore.getEnjoyment()%></p>
					<p class="text-right">Workload:   <%=currScore.getWorkload()%></p>			
				</div>
				<div class= "col-lg-1"></div>
				<div class="col-lg-8" id="review">
					<p><%=currReview.getReview() %></p>		
				</div>
			</div>
<% 			}  %>  
		</section>
		
		
		
		
		
	</div>

  </body>

</html>
