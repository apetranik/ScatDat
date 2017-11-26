<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="scatalogObjects.*, scatalogObjects.Course,scatalogObjects.Review, java.util.Vector,java.util.Date, java.util.ArrayList, database.*,java.text.DecimalFormat"%>

<%
  //get our new reviews, store it into database and return it 
  String prefix = request.getParameter("prefix");
  String number = request.getParameter("number");
  String ord = request.getParameter("order");
  Database db = new Database();
  DecimalFormat df = new DecimalFormat("#0.0");
  int num = Integer.parseInt(number);
  int order = Integer.parseInt(ord);
  
  session.setAttribute("orderStyle", order);
  int sort = (int)session.getAttribute("sortStyle");
  
  
  
  ArrayList<Review> myReviewList = new ArrayList<>();
  myReviewList = db.getSortList(prefix,num,order,sort);
  //myReviewList.add(r);


%>
<%for(Review currReview: myReviewList){ 
  Score currScore = currReview.getScore();
  Name currProf = currReview.getProfessor();
  CourseTime currCT = currReview.getCourseTime();
  String termstr = "";
	String termInt = currCT.getTerm();
	if(termInt.equals("0")){
		termstr = "Spring";
	}else if(termInt.equals("1")){
		termstr = "Summer";
	}else if(termInt.equals("2")){
		termstr = "Fall";
	}
	//
	
   
%>
<div class="row" id= "reviewerSection">
      <div class="col-lg-3" id="reviewer">
        <div class = "row">
          <div class="col-lg-6"><b><%=currReview.getUsername()%></b></div>
          <div class="col-lg-6 text-right"><b>Total: <%=df.format(currScore.getOverallRating())%></b></div>
        </div>    
      </div>
      <div class="col-lg-5">
      </div>
      <div class="col-lg-4 " id="instrutor">
        <p class="text-center"><%=currProf.getFname() + " " + currProf.getLname() %> | <%=termstr + " " + currCT.getYear()%></p>
      </div>
    </div>
    
    <div class="row" id= "reviewSection">
      <div class="col-lg-3" id="points">
        <p class="text-right">Difficulty:  <%=currScore.getDifficulty()%></p>
        <p class="text-right">Value:      <%=currScore.getValue()%></p>
        <p class="text-right">Enjoyment:  <%=currScore.getEnjoyment()%></p>
        <p class="text-right">Workload:   <%=currScore.getWorkload()%></p>      
      </div>
      <div class= "col-lg-1">
      </div>
      <div class="col-lg-8" id="review">
        <p><%=currReview.getReview() %></p>   
      </div>
</div>
<% }  %>  
