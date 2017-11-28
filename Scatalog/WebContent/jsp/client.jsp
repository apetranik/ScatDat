<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.IOException, java.io.ObjectInputStream, java.io.ObjectOutputStream, java.net.Socket" %>

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
	String read = ""; 
	if(session.getAttribute("?") == null)
	  read = (String) ois.readObject();
	if(read.length() > 0)
		session.setAttribute("?", "no"); 
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