<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.IOException, java.io.ObjectInputStream, java.io.ObjectOutputStream, java.net.Socket" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
try {
	Socket s = (Socket) session.getAttribute("socket");
	if( s == null) {
		 s = new Socket("localhost", 6789); 
	}
	ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream()); 
	ObjectInputStream ois = new ObjectInputStream(s.getInputStream()); 
	ois.readObject();
} catch(IOException ioe){
	ioe.printStackTrace();
}

%>