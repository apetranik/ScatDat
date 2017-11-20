<%@page import="database.Database"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, scatalogObjects.Course, java.text.DecimalFormat"  %>

<%
	//testing purposes
	/*Course course1 = new Course("Discrete Mathematics", 170, "Introduction to logic, proofs, runtime, probability, number theory and cryptography with computer science applications in mind", "CSCI",
			4, 5, 3, 3, 0);  
	Course course2 = new Course("Probability Theory", 407, "Discrete probability, distributions, random vairables, stochastic processes, law of large numbers", "MATH",
			5, 5, 1, 3, 0); 
	Course course3 = new Course("Data Structures", 104, "Redekopp stuff", "CSCI",
			5, 5, 1, 1, 0);
	ArrayList<Course> courses = new ArrayList<Course>(); 
	courses.add(course1);
	courses.add(course2); 
	courses.add(course3); 
	*/
	
	
	//delete the above after testing 
	
	//uncomment the following after testing
	ArrayList<Course> courses = (ArrayList<Course>) session.getAttribute("courses"); 
	Database db = new Database();
	if(courses == null) {
		courses = db.queryCourses(); //SQL Query
		
	}
	DecimalFormat df = (DecimalFormat) session.getAttribute("decimalFormatter"); 
	if(df == null ) {
		df = new DecimalFormat("#0.0");
		session.setAttribute("decimalFormatter", df);
	}
	
	String searchText = request.getParameter("searchText").trim().toLowerCase(); 
	int count = 0; 
	for(Course course : courses) {
		count++; 
		String abbreviated = course.getPrefix() + " " + course.getCourseId(); 
		abbreviated = abbreviated.toLowerCase(); 
		String name = course.getCourseName().toLowerCase(); 
		if((searchText != null) && (searchText.length() > 0) && (abbreviated.contains(searchText) || name.contains(searchText))) {
%>
		<div class="card">
			<div class="card-header" role="tab">
				      		<a class="card-link" id="cards" data-toggle="collapse" data-parent="#accordion<%=count%>"  data-target="#collapse<%=count%>" href="javascript:void(0);" aria-expanded="false" aria-controls="collapse<%=count%>" >
								<%=course.getPrefix() + " " + course.getCourseId() + ": " + course.getCourseName()  %>
				      		</a>
				      		<a class="card-link" id="cards-stars" data-toggle="collapse" data-parent="#accordion<%=count%>" data-target="#collapse<%=count%>" href="javascript:void(0);" aria-expanded="false" aria-controls="collapse<%=count%>">
							<!-- This is where the stars goes -->
								<%=" Score: " + df.format(course.getOverallScore().getOverallRating()) %>
				      		</a>
		   </div>
				  		<div id="collapse<%=count%>" class="collapse">
				    			<div class="card-body">
				        		<!-- Description goes here -->
				        			<%=course.getDescription() %>
				    			</div>
				    		</div>
		</div>
<% 
		}
	}
%>
	
<%
	
%>
