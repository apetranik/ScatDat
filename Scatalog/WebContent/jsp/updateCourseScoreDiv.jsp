<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="scatalogObjects.*,java.util.Vector,java.util.Date, java.util.ArrayList,database.*"%>


<%
	Database db = new Database();
	String prefix = request.getParameter("prefix");
	String numStr = request.getParameter("number");
	int number = Integer.parseInt(numStr);
	int courseId = db.returnCourseID(prefix, number);
	Course currCourse = db.returnCourse(prefix,number);
	ArrayList<Review> currList = db.queryAllReview(courseId);
	
%>


<b><%=prefix%> <%=number %></b> &nbsp;&nbsp; <%=currCourse.getOverallScore().getOverallRating() %> &nbsp;&nbsp; (<%=currList.size() %> reviews)