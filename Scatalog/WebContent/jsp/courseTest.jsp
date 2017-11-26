<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="scatalogObjects.*, scatalogObjects.Course,scatalogObjects.Review, java.util.Vector,java.util.Date, java.util.ArrayList, database.*,java.text.DecimalFormat"%>
<%@ page import="java.io.IOException, java.io.ObjectInputStream, java.io.ObjectOutputStream, java.net.Socket" %>
<%
  //get our new reviews, store it into database and return it 
  String difficulty = request.getParameter("difficulty");
  String courseValue = request.getParameter("coursevalue");
  String enjoyment = request.getParameter("enjoyment");
  String workload = request.getParameter("workload");
  String term = request.getParameter("term");
  String year = request.getParameter("year");
  String professor = request.getParameter("professor");
  String review = request.getParameter("review");
  String currentUser = request.getParameter("currentUser");
  String currentCourseId = request.getParameter("courseid");
  String firstName = "";
  String lastName = "";
  
  Database db = new Database();
  DecimalFormat df = new DecimalFormat("#0.0");
  double diff = Double.parseDouble(difficulty);
  double value = Double.parseDouble(courseValue);
  double enjoy = Double.parseDouble(enjoyment);
  double work = Double.parseDouble(workload);
  int courseId = Integer.parseInt(currentCourseId);
  
  ArrayList<Review> myReviewList = new ArrayList<>();
  myReviewList = db.queryAllReview(courseId);
  Score s = new Score(enjoy, diff, value, work);
  Date d = new Date();
  
  /* /////////Test
  
  Review test1 = new Review("Hellooooooooo", new Score(2.0, 2.0, 3.0, 4.0), new Date(), "zhengxix", new CourseTime("Spring","2015"), null, new Name("Jeffrey","Miller"));
  Review test2 = new Review("Hiiiiii 201", new Score(4.0, 4.0, 3.0, 4.0), new Date(), "cote", new CourseTime("Spring","2017"), null, new Name("Aaron","Cote"));
  Review test3 = new Review("Trash course", new Score(1.0, 1.0, 2.0, 2.0), new Date(), "jeffrey", new CourseTime("Fall","2017"), null, new Name("Jeffrey","Miller"));
  myReviewList.add(test1);
  myReviewList.add(test2);
  myReviewList.add(test3); */
  
  System.out.println("Review is diff: " + diff + ";value: " + value + ";enjoy: " + enjoy + ";work: " + work + "Course ID: " + courseId);
  
  //////////////////delete things above!!!~
  
  CourseTime ct = new CourseTime(term,year);
  String[] nameSeparate = professor.split("\\s");
  
  if(nameSeparate.length >= 1){
    firstName = nameSeparate[0];
    
    for(int i=1;i < nameSeparate.length;i++){
      if(i != nameSeparate.length-1){
        lastName += nameSeparate[i] + " ";
      }else{
        lastName += nameSeparate[i];
      }
    }
  }
  
  System.out.println("Professor name: " + firstName + " " + lastName);
  Name professorName = new Name(firstName,lastName);
  Review r = new Review(review, s, d, currentUser, ct, null, professorName);
  db.insertReview(r,courseId);
  
  myReviewList = db.queryAllReview(courseId);
  
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

<%
try {
	Socket socket = (Socket) session.getAttribute("socket");
	if( socket == null) {
		 socket = new Socket("localhost", 6789); 
	}
	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); 
	ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); 
	oos.writeObject("");
} catch(IOException ioe){
	ioe.printStackTrace();
}
%>
