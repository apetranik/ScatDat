<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="scatalogObjects.User, scatalogObjects.Course, java.util.Vector"%>

<%
	User currentUser = (User)session.getAttribute("currentUser");
	if(currentUser == null) {
		//redirect to login page
		
	}
	Vector<User> users = (Vector<User>)session.getAttribute("users");
	if(users == null) {
		//redirect to login page
	}
	
	//delete lines below once we get login page working
	currentUser = new User("Apurva", "Gandhi", "Junior", "apurvaga@usc.edu", "Computer Engineering and Computer Science");
	users = new Vector<User>(); 
	users.add(new User("Aliya", "Petranik", "Sophomore", "petranik@usc.edu", "Computer Science"));
	users.add(new User("Kmoney", "Gopal", "Sophomore", "gopalk@usc.edu", "Computer Engineering and Computer Science")); 
	users.add(new User("Qiusi", "Li", "Sophomore", "qiusili@usc.edu", "Computer Science")); 
	users.add(new User("Zhengxi", "Xiao", "Senior", "zhengxix@usc.edu", "Electrical Engineering")); 
	users.add(currentUser); 
	//delete lines above once we get login page and queries working
	
	String searchText = request.getParameter("searchText"); 
	if(searchText != null)
		searchText = searchText.toLowerCase().trim(); 
%>	
			<table class ="table">
				
					
<% 
	for(User user: users) {
		if(searchText != null && searchText.length() > 0 && !user.equals(currentUser) && user.getName().getFullName().toLowerCase().contains(searchText)) {
%>
				
					<tr><td><a href="../jsp/otherDashBoard.jsp"><%=user.getName().getFullName()%></a></td><td><%=user.getClassStanding()%><td><%=user.getMajor() %></td></tr>

<%
		}
	}

%>
				
			</table>