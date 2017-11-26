<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="scatalogObjects.*,java.util.Vector,java.util.Date, java.util.ArrayList,database.*,java.text.DecimalFormat"%>


<%
	Database db = new Database();
	String prefix = request.getParameter("prefix");
	String numStr = request.getParameter("number");
	int number = Integer.parseInt(numStr);
	int courseId = (int)db.returnCourseID(prefix, number);
	Course currCourse = (Course)db.returnCourse(prefix,number);
	ArrayList<Review> currList = db.queryAllReview(courseId);
	currCourse.setReviews(currList);
	
	if(currList.size()>0){
		currCourse.updateOverallScore();
		Score overall = currCourse.getOverallScore();
		db.updateScore(prefix,number,overall);
		ArrayList<Course> courses = (ArrayList<Course>)session.getAttribute("courses");
		db = new Database();
	    if(courses == null) {
	      courses = db.queryCourses(); //SQL Query
	      session.setAttribute("courses", courses);
	    }
	}
	
	DecimalFormat df = new DecimalFormat("#0.0");
	
%>


<b><%=prefix%> <%=number %></b> &nbsp;&nbsp; <%=df.format(currCourse.getOverallScore().getOverallRating()) %> &nbsp;&nbsp; (<%=currList.size() %> reviews)