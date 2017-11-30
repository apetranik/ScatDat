<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="database.Database, scatalogObjects.User"%>

<%
	Database db = new Database();
	User currentUser = (User)session.getAttribute("currentUser");
	String email = currentUser.getEmail();
	
	String username = currentUser.getUsername();
	String fname = (String)request.getParameter("fname");
	String lname = (String)request.getParameter("lname");
	String classStanding = (String)request.getParameter("standing");
	String major = (String)request.getParameter("major");
	System.out.println(classStanding);
	int classNum = 0;
	if(classStanding.equals("1")) classNum = 0;
	else if(classStanding.equals("2")) classNum = 1;
	else if(classStanding.equals("3")) classNum = 2;
	else classNum = 3;
	System.out.println(classNum);
	
	
	System.out.println("before");
	db.updateUserSettings(username, fname, lname, classNum, major);
	// Get the updated user and set it as the session attribute
	currentUser = db.queryUser(email);
	session.setAttribute("currentUser", currentUser);
	System.out.println("after");
%>