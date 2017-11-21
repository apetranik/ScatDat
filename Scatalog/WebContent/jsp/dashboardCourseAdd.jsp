<%@page import="database.Database"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, scatalogObjects.*, java.text.DecimalFormat"  %>
<%@ page import="database.*" 
%>
<%
	Database db = new Database();
	String searchText = request.getParameter("newCourse").trim().toLowerCase(); 
	System.out.println(searchText);
	String[] parts = searchText.split("\\-");
	String prefix = parts[0];
	int number = Integer.parseInt(parts[1]);
	System.out.println(prefix);
	System.out.println(number);
	// Get course object from courses databases
	Course course = db.returnCourse(prefix, number);	
	User user = (User)session.getAttribute("currentUser");
	// Add to users wishlist
	db.addToUserCourselist(user, course);
	// re-query
	User currentUser = db.queryUser(user.getEmail());
	session.setAttribute("currentUser", currentUser);
	
%>