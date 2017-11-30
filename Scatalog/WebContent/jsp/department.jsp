<%@page import="database.Database"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.List, scatalogObjects.*, java.text.DecimalFormat"  %>

<%

	List<Department> departments = null;
	
	    QueryDepartments departmentRequest = new QueryDepartments(); 
		//departments = departmentRequest.getAllDepartments();
		session.setAttribute("departments", departments); 
	
	ArrayList<School> schools = departmentRequest.getAllSchools();
	System.out.println("schools: " + schools.get(0).getDepts().get(57).getName());
	
	if(departments == null) System.out.println("hmmm");
	List<Course> courses = null;
	int count = 0; 
	// Loop thru each department
	System.out.println("but why");
	for(Department dept : departments)
	{
		System.out.println("hi" + dept.getCourses().size());
		
		courses = dept.getCourses(); // set current dept's courselist
		%>
		<div class="card">
		<div class="card-header" role="tab">
			<a class="card-link" id="cards" data-toggle="collapse" data-parent="#accordion<%=count%>"  data-target="#collapse<%=count%>" href="javascript:void(0);" aria-expanded="false" aria-controls="collapse<%=count%>" >
				<%=dept.getPrefix() %>
			</a>
		</div>	
		<%
		
		for(Course course: courses)
		{
			//System.out.println("cousename: " + course.getCourseName());
			count++; // for keeping track of num courses 
			%>
			<!-- Parent = accordion in department.html -->
			
				<div id="collapse<%=count%>" class="collapse">
					<div class="card-body">
						<!-- Description goes here -->
					    <%=course.getPrefix() + " " + course.getCourseId() + ": " + course.getCourseName()  %>
					</div>
				</div>
			</div>
			
			<%
		}
	}
	
%>

<%
	
%>
	

