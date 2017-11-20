<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.sql.Connection" %>
<%@ page import ="java.sql.DriverManager" %>
<%@ page import ="java.sql.PseudoColumnUsage" %>
<%@ page import ="java.sql.ResultSet" %>
<%@ page import ="java.sql.SQLException" %>
<%@ page import ="java.sql.Statement" %>
<%@ page import ="com.mysql.jdbc.PreparedStatement" %>
<%@ page import = "database.Database" %>
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
String password = "";
String email = request.getParameter("email");
try {
	ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM user WHERE email=?");
	ps.setString(1, email);
	rs = ps.executeQuery();
	boolean userFound = false;
	while (rs.next()) {
		userFound = true;
	}
	if(userFound) {
		System.out.println("Found user!");
		//Database db = new Database();
		User currentUser = Database.queryUser(email);
		System.out.println(currentUser.getName().getFname());
		session.setAttribute("currentUser", currentUser);
		
		%>0<%
	}
	else {
		%>1<%
		System.out.println("Did not find user :(");
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} // set first variable in prepared statement


%>