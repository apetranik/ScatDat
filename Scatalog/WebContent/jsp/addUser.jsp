<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.sql.Connection" %>
<%@ page import ="java.sql.DriverManager" %>
<%@ page import ="java.sql.PseudoColumnUsage" %>
<%@ page import ="java.sql.ResultSet" %>
<%@ page import ="java.sql.SQLException" %>
<%@ page import ="java.sql.Statement" %>
<%@ page import ="com.mysql.jdbc.PreparedStatement" %>
<%@ page import = "database.*" %>
<%@ page import = "scatalogObjects.*" %>
<% 

Connection conn = null;
try {
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://localhost/scatdat?user=root&password=root&useSSL=false");
}catch(SQLException sqle) {
	System.out.println (sqle.getMessage());
}catch(ClassNotFoundException cnfe) {
	System.out.println (cnfe.getMessage());
}

PreparedStatement ps = null; 
ResultSet rs = null;
String email = request.getParameter("email");
System.out.println(email);
String username = email.substring(0, email.indexOf('@'));
String classstanding = request.getParameter("classStanding");
String fname = request.getParameter("fname");
String lname = request.getParameter("lname");
//int ratingStyle = Integer.parseInt(request.getParameter("ratingStyle"));
String major = request.getParameter("major");
try {
	ps = (PreparedStatement) conn.prepareStatement("INSERT INTO user (email, username, classstanding, fname, lname, major) VALUES (?,?,?,?,?,?)");
	ps.setString(1, email);
	ps.setString(2, username);
	ps.setString(3, classstanding);
	ps.setString(4, fname);
	ps.setString(5, lname);
	ps.setString(6, major);
	ps.executeUpdate();

} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} // set first variable in prepared statement
Database db = new Database();
User currentUser = db.queryUser(email);
session.setAttribute("currentUser", currentUser);
%>