<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.IOException, java.io.ObjectInputStream, java.io.ObjectOutputStream, java.net.Socket" %>

<%
 ObjectOutputStream oos1 = (ObjectOutputStream) session.getAttribute("oos1"); 
 ObjectOutputStream oos2 = (ObjectOutputStream) session.getAttribute("oos2"); 
 ObjectInputStream ois1 = (ObjectInputStream) session.getAttribute("ois1");
 ObjectInputStream ois2 = (ObjectInputStream) session.getAttribute("ois2");
 Socket s1 =  (Socket) session.getAttribute("socket1"); 
 Socket s2 = (Socket) session.getAttribute("socket2");
 
 if(oos1 != null) {
	 oos1.close(); 
	 session.setAttribute("oos1", null); 
 }
 if(oos2 != null) {
		 try {
		 	oos2.writeObject("break");
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 oos2.close(); 
		 session.setAttribute("oos2", null); 
	 }
 if(ois1 != null) {
	 ois1.close(); 
	 session.setAttribute("ois1", null); 
 }
 if(ois2 != null) {
	 ois2.close(); 
	 session.setAttribute("ois2", null); 
 }
 if(s1 != null) {
	 s1.close(); 
	 session.setAttribute("socket1", null); 
 }
 if(s2 != null) {
	 s2.close(); 
	 session.setAttribute("socket2", null); 
 }
session.setAttribute("?", null); 
%>