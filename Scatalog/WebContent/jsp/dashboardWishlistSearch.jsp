<%@page import="database.Database"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, scatalogObjects.Course, java.text.DecimalFormat"  %>

<%
	
	
	// get all the courses from the database
	ArrayList<Course> courses = (ArrayList<Course>)session.getAttribute("courses"); 
	Database db = new Database();
	if(courses == null) {
		courses = db.queryCourses(); //SQL Query
		
	}
	// Truncates decimal if too long
	DecimalFormat df = (DecimalFormat) session.getAttribute("decimalFormatter"); 
	if(df == null ) {
		df = new DecimalFormat("#0.0");
		session.setAttribute("decimalFormatter", df);
	}
	
	// Get what the user searched for
	String searchText = request.getParameter("searchTexting").trim().toLowerCase(); 
	int count = 0; 
	for(Course course : courses) 
	{
		count++; 
		String abbreviated = course.getPrefix() + " " + course.getCourseId(); 
		abbreviated = abbreviated.toLowerCase(); 
		String name = course.getCourseName().toLowerCase(); 
		if((searchText != null) && (searchText.length() > 0) && (abbreviated.contains(searchText) || name.contains(searchText))) 
		{
%>
		<!-- Search Results are below -->
		<div class="panel panel-default">
  			<div class="panel-body" data-toggle="collapse" data-parent="#accordion2<%=count%>">
  				<div class ="row">
  					<div class = "col-md-10">
	   			 		<%=course.getPrefix() + " " + course.getCourseId() + ": " + course.getCourseName()  %>
	   			 		<%=" Score: " + df.format(course.getOverallScore().getOverallRating()) %>
	   			 		<hr>
	  				</div>
	  				<div class="col-md-2">
	  					<button class="btn btn-outline-secondary left-align" type="button" aria-pressed="true">
	        					<i class="fa fa-plus" onclick="test();"></i>
	        				</button>
	        			
	        			</div>
        			</div>
  			</div>
		</div>

			
<% 
		}
	}
%>
	
<%
	
%>
