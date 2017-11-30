<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
  import="scatalogObjects.*,java.util.Vector,java.util.Date, java.util.ArrayList, database.Database, java.text.DecimalFormat"
  
%>

<% 
  
  //remove comment below later
   
  User currentUser = (User)session.getAttribute("currentUser");
  session.setAttribute("orderStyle", 0);
  session.setAttribute("sortStyle", 0);
  
  String username = "";
  if(currentUser != null){
  	username = currentUser.getUsername();
  }
  
  String prefix = (String) session.getAttribute("currCoursePrefix");
  int number = Integer.parseInt((String) session.getAttribute("currCourseNumber"));
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
  currCourse.setReviews((currReviewList == null ? new ArrayList<Review>() : currReviewList));
  if(currReviewList.size() > 0){
    currCourse.updateOverallScore();
    Score overall = currCourse.getOverallScore();
    db.updateScore(prefix,number,overall);
    courses = (ArrayList<Course>)session.getAttribute("courses"); 
    if(courses == null) {
      courses = db.queryCourses(); //SQL Query
      session.setAttribute("courses", courses);
    }
  }
  
  //get sort list
  currReviewList = db.getSortList(prefix,number,0,0);
  

  
  //variables
  String fullCourseName = currCourse.getPrefix() + " " + currCourse.getCourseId();
  
  

  db.close(); 


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
   <!--   <script type="text/javascript" src="../js/course.js"></script> -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
     	<meta name="google-signin-client_id" content="647589413183-bdfcadf4bm2vugreeo6a8n4hj3ath8rg.apps.googleusercontent.com">
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	
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
          if("<%=username%>" == ""){
        	  	document.getElementById("modalEntry").disabled = true;
        	  	
        	  	//alert("log in first")
          }else{
        	  	document.getElementById("modalEntry").disabled = false;
        	  	var username = "<%=username%>";
        	  	//alert(username);
          }
          window.onbeforeunload = function(){
        	  	var xhttp = new XMLHttpRequest(); 
  			xhttp.open("GET", "../jsp/clientCleanup.jsp", false); 
  			xhttp.send();
          };
          clientRead();
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
    //alert(sendStr);
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
    //location.reload();
    
      
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
    
    function sortReview(text){
    		var xhttp = new XMLHttpRequest(); 
        var sendStr = "../jsp/sortReview.jsp?";
        sendStr += "prefix=" + "<%=prefix%>";
        sendStr += "&number=" + "<%=number%>"
        
        
        //$(document).ready.(function(){
        
        	if($("input[name='options']:checked").val() == 0){
        		sendStr += "&order=" + 0;
        }else if($("input[name='options']:checked").val() == 1){
            	sendStr += "&order=" + 1;
        }

        //alert("final str: " + sendStr);
        xhttp.open("GET", sendStr, false);
		xhttp.send();
		document.getElementById("reviewlist").innerHTML = xhttp.responseText;
      
    	
    }
    
    
    function sortReview2(){
    		var xhttp = new XMLHttpRequest(); 
        var sendStr = "../jsp/sortReview2.jsp?";
        sendStr += "prefix=" + "<%=prefix%>";
        sendStr += "&number=" + "<%=number%>";
        $('.dropdown-menu').one('click', 'a', function(e){
    	  		var text = this.text;  
    	  		if(text == "Term"){
    	  			sendStr += "&sort=" + 0;
    	  		}else if(text == "Overall Rating"){
    	  			sendStr += "&sort=" + 1;  
    	  		}else if(text == "Difficulty"){
    	  			sendStr += "&sort=" + 2;  
    	  		}else if(text == "Value"){
    	  			sendStr += "&sort=" + 3;  
    	  		}else if(text == "Enjoyment"){
    	  			sendStr += "&sort=" + 4;  
    	  		}else if(text == "Workload"){
    	  			sendStr += "&sort=" + 5;  
    	  		}else if(text == "Professor"){
    	  			sendStr += "&sort=" + 6;
    	  		}
    	  		
    	  		xhttp.open("GET", sendStr, false);
    			xhttp.send();
    			document.getElementById("dropdownMenuButton").innerHTML = text;
    			document.getElementById("reviewlist").innerHTML = xhttp.responseText;
    	  	
    		});
    	
    	
    }
    
    function clientRead() {
    		var xhttp = new XMLHttpRequest(); 
    		xhttp.open("GET", "../jsp/client.jsp", true); 
    		xhttp.send();
    		xhttp.onreadystatechange = function read() {
    			if(this.readyState == 4 && this.status == 200) {
    				if(xhttp.responseText.trim().length < 3) {
	    				//alert("notification");
	    				//location.reload();
	    				sortReview(); 
    				}	
    			}
    		};
    }
    
    function closeSocket() {
    		var xhttp = new XMLHttpRequest(); 
			xhttp.open("GET", "../jsp/clientCleanup.jsp", false); 
			xhttp.send();
    }
    
    function onSignIn(googleUser) {
		var profile = googleUser.getBasicProfile();
		document.getElementById("userHello").innerHTML = "Hello, "+ profile.getName()+ "!";
		console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
		console.log('Name: ' + profile.getName());
		console.log('Image URL: ' + profile.getImageUrl());
		console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
	  	var xhttp = new XMLHttpRequest();
		xhttp.open("GET", "../jsp/authUser.jsp?email=" +  profile.getEmail(), true); 
		xhttp.send();
		xhttp.onreadystatechange = function updateResults() {
				if(this.readyState == 4 && this.status == 200) {
					var result = xhttp.responseText.trim();
					
					if(result == '1') {
						
						window.location.href = "../html/login.html";
					}
					else {
					
					}
				}
		};
		return true;
	}
    
	function signOut() {
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	      console.log('User signed out.');
	    });
	    var xhttp = new XMLHttpRequest();
		xhttp.open("GET", "../jsp/signOutUser.jsp", false); 
		xhttp.send();
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

  <body onload="initialize()">

    <!-- Navigation -->
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top justify-content-center ">
        <a href="/" class="navbar-brand d-flex w-20 mr-auto">SCatalog</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsingNavbar3">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar-collapse collapse" id="collapsingNavbar3">
        <ul class="navbar-nav mr-auto w-100 justify-content-center">
            <li class="nav-item">
                <a class="nav-link" href="../html/index.html">HOME</a>
            </li>
            
            <li class="nav-item active">
                <a class="nav-link" href="#">ALL COURSES</a>
            </li>
        </ul>
        <ul class="nav navbar-nav ml-auto w-100 justify-content-end">
        		<li class="nav-item">
        			<a class="nav-link" id="userHello"></a>
        		</li>
            <li class="nav-item">
                <a class="nav-link" href="../html/dashboard.html">User Dashboard</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../html/login.html">Sign-In</a>
            </li>
            <li class="nav-item">
                			<div class="g-signin2" data-onsuccess="onSignIn"></div>
            </li> 
            	<li class="nav-item">
				<a class="nav-link" href="#" onclick="signOut();">Sign Out</a>
			</li> 
            
        </ul>
    </div>
    </nav>

    <!-- Page Content -->
   <div class="container">
    <div class="row">
      <div class="col-lg-2" id="courseInfo"> 
      	<span><h3><%=fullCourseName %></h3> <p> &nbsp;&nbsp; <%=df.format(currCourse.getOverallScore().getOverallRating())%> &nbsp;&nbsp; (<%=currReviewList.size() %> reviews)</p></span>
      </div>
      <div class="col-lg-10" id="info">
      	<p><%=currCourse.getDescription() %></p>
      </div>
     </div><!-- close row -->
     <br />
     <div class="row">
  
	 	<div class="col-lg-2">
	   		<button type="button" class="btn btn-secondary btn-sm"  id="modalEntry" data-toggle="modal" data-target="#myModal">Submit Review</button>     
	  	</div>
	  	<div class="col-lg-4">
	  	<!-- Empty space -->
	  	</div>
      	<div class="col-lg-2" id="info">        
       		<div class="dropdown">
          		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              		Sorting by
          		</button>
	          	<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
	              <a id="Rating" class="dropdown-item" name="sort" value="0" onclick="sortReview2()" href="#">Term</a>
	              <a id="Term" class="dropdown-item" name="sort" value="1" onclick="sortReview2()" href="#">Overall Rating</a>
	              <a id="Date" class="dropdown-item" name="sort" value="2" onclick="sortReview2()" href="#">Difficulty</a>
	              <a id="Date" class="dropdown-item" name="sort" value="2" onclick="sortReview2()" href="#">Value</a>
	              <a id="Date" class="dropdown-item" name="sort" value="2" onclick="sortReview2()" href="#">Enjoyment</a>
	              <a id="Date" class="dropdown-item" name="sort" value="2" onclick="sortReview2()" href="#">Workload</a>
	              <a id="Date" class="dropdown-item" name="sort" value="2" onclick="sortReview2()" href="#">Professor</a>
	          	</div>
      		</div>
      	</div><!-- close col -->
          
      	<div class="col-lg-4 mx-right" id="info">
        		<div class="btn-group" data-toggle="buttons">
	      			<!-- Descending Option -->
					<label class="btn btn-secondary active" onchange="sortReview()">
				    		<input type="radio" value="0" name="options" id="des" autocomplete="off" checked> Descending
				  	</label>
				  	<!-- Ascending Option -->
				  	<label class="btn btn-secondary" onchange="sortReview()">
				   		<input type="radio" value="1" name="options" id="asc" autocomplete="off" > Ascending
				  	</label>
			</div>    
     	 </div><!-- close col -->
	</div><!-- close row -->
	<hr>
    <div class="row">
      <div class="col-lg-3" id="info">
      
      <!-- Modal -->
     
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
            <div class="col-lg-6"><h5><%=currReview.getUsername()%></h5></div>
            <div class="col-lg-6 text-right">Total: <%=df.format(currScore.getOverallRating())%></div>
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
        <div class= "col-lg-1">
        </div>
        <div class="col-lg-8" id="review">
          <p><%=currReview.getReview() %></p>   
        </div>
      </div>
      <hr>
<%      }  %>  
    </section>
    
    
    
    
    
  </div>

  </body>

</html>
