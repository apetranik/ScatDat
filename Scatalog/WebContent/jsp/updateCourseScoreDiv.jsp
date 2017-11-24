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
	currCourse.updateOverallScore();
	DecimalFormat df = new DecimalFormat("#0.0");
	
%>


<b><%=prefix%> <%=number %></b> &nbsp;&nbsp; <%=df.format(currCourse.getOverallScore().getOverallRating()) %> &nbsp;&nbsp; (<%=currList.size() %> reviews)