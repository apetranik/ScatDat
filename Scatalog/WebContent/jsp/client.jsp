<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.IOException, java.io.ObjectInputStream, java.io.ObjectOutputStream, java.net.Socket" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
//while(true) {
try {
	Socket s = (Socket) session.getAttribute("socket1");
	if( s == null) {
		 s = new Socket("localhost", 6789); 
		 session.setAttribute("socket1", s); 
	}
	ObjectOutputStream oos = (ObjectOutputStream) session.getAttribute("oos1"); 
	if(oos == null) {
		oos = new ObjectOutputStream(s.getOutputStream()); 
		session.setAttribute("oos1", oos);
	}
	ObjectInputStream ois = (ObjectInputStream) session.getAttribute("ois1"); 
	if(ois == null) {
		ois = new ObjectInputStream(s.getInputStream()); 
		session.setAttribute("ois1", ois); 
	}
	//if(s.isClosed())
		//break; 
	String read = (String) ois.readObject();
%>
	<%=read%>
<%
	
	//break; 
} catch(IOException ioe){
	ioe.printStackTrace();
	session.setAttribute("socket1", null); 
}
//}

%>