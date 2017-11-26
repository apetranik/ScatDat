<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="database.Database, scatalogObjects.User" %>
    

  <%
  		Database db = new Database(); 
   		String email = request.getParameter("Email").trim(); 
   		User otherUser = db.queryUser(email); 
  		session.setAttribute("otherUser", otherUser);
 
   
  %>