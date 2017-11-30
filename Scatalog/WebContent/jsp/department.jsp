<%@page import="database.Database"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.List, scatalogObjects.*, java.text.DecimalFormat"  %>

<%
	DecimalFormat df = (DecimalFormat) session.getAttribute("decimalFormatter"); 
	if(df == null ) {
		df = new DecimalFormat("#0.0");
		session.setAttribute("decimalFormatter", df);
	}
	List<Department> departments = null;
	
	    departments = (List<Department>) session.getAttribute("departments");
	    if(departments == null) {
	    	 	QueryDepartments departmentRequest = new QueryDepartments(); 
			departments = departmentRequest.getAllDepartments();
			session.setAttribute("departments", departments); 
	    }
	//ArrayList<School> schools = departmentRequest.getAllSchools();
	//System.out.println("schools: " + schools.get(0).getDepts().get(57).getName());
	
	if(departments == null) System.out.println("hmmm");
	List<Course> courses = null;
	int count = 0; 
	// Loop thru each department
	System.out.println("but why");
%>
	<div class="panel panel-default" id="accordion" role="tablist" aria-multiselectable="true"> 
	<!-- THINGS GO HERE -->
<%
	int count2 = 10000; 
	for(Department dept : departments) {
%>
	<div class="card">
				<div class="card-header" role="tab">
					      		<a class="card-link" id="cards" data-toggle="collapse" data-parent="#accordion<%=count%>"  data-target="#collapse<%=count%>" href="javascript:void(0);" aria-expanded="false" aria-controls="collapse<%=count%>" >
									<%=dept.getName()%>
					      		</a>
			   </div>
					  		<div id="collapse<%=count%>" class="collapse">
					    			<div class="card-body">
					        		<!-- Description goes here -->
<% 
									for(Course course : dept.getCourses()) {
%>
										<div class="card">
												<div class="card-header" role="tab">
													      		<a class="card-link" id="cards" data-toggle="collapse" data-parent="#accordion<%=count2%>"  data-target="#collapse<%=count2%>" href="javascript:void(0);" aria-expanded="false" aria-controls="collapse<%=count2%>" >
																	<%=course.getPrefix() + " " + course.getCourseId() + ": " + course.getCourseName()  %>
													      		</a>
													      		<a class="card-link" id="cards-stars" data-toggle="collapse" data-parent="#accordion<%=count2%>" data-target="#collapse<%=count2%>" href="javascript:void(0);" aria-expanded="false" aria-controls="collapse<%=count2%>">
																<!-- This is where the stars goes -->
																	<%=" Score: " + df.format(course.getOverallScore().getOverallRating()) %>
													      		</a>
											   </div>
													  		<div id="collapse<%=count2%>" class="collapse">
													    			<div class="card-body">
													        		<!-- Description goes here -->
													        			<%=course.getDescription() %><hr />
													        			<a style="color: gray" onclick="goToCoursePage(this.id)" href="javascript:void(0)" id ="<%=course.getPrefix() + "-" + course.getCourseId()%>" >Go to course page!</a>
													    			</div>
													    		</div>
											</div>
										
<% 
									count2++; 
									}
%>
					    			</div>
					    		</div>
			</div>
<% 
	count++; 
	}
%>
</div>




	


	

