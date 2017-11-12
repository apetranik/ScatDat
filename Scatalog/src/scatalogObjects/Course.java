package scatalogObjects;
import java.util.ArrayList;
import java.util.HashMap;

import tools.ScoreMap;

public class Course {
	private String courseName; 
	private int courseId; 
	private String description; 
	private int numRatings; 
	private String prefix; 
	private Score overallScore; 
	private HashMap<Name, ProfCourse> profCourses; 
	private ArrayList<Review> reviews; 
	private int type; 
	private int numUsers;
	
	public Course() {
		
	}
	
	public Course(String courseName, int courseId, String description, int numRatings, String prefix,
			Score overallScore, HashMap<Name, ProfCourse> profCourses, ArrayList<Review> reviews, int type,
			int numUsers) {
		this.courseName = courseName;
		this.courseId = courseId;
		this.description = description;
		this.numRatings = numRatings;
		this.prefix = prefix;
		this.overallScore = overallScore;
		this.profCourses = profCourses;
		this.reviews = reviews;
		this.type = type;
		this.numUsers = numUsers;
	}

	public void updateOverallScore() { 
		long enjoyment = 0; 
		long value = 0; 
		long difficulty = 0; 
		long workload = 0; 
		for(Review review : reviews) {
			Score score = review.getScore(); 
			enjoyment += score.getEnjoyment(); 
			difficulty += score.getDifficulty(); 
			value += score.getValue(); 
			workload += score.getWorkload(); 
		}
		overallScore.setWorkload(workload/((double) reviews.size()));
		overallScore.setEnjoyment(enjoyment/((double) reviews.size()));
		overallScore.setValue(value/((double) reviews.size()));
		overallScore.setDifficulty(difficulty/((double) reviews.size()));
		ScoreMap calculator = new ScoreMap(); 
		overallScore.setOverallRating(calculator.computeOverallScore(overallScore)); 
		
		//// UPDATE IN DATABASE ////
	}
	
	public void addReview(Review review) {
		reviews.add(review); 
		Name profName = review.getProfessor(); 
		
		//// ADD TO DATABASE ////
		
		if(profCourses.containsKey(profName)) {
			ProfCourse reviewCourse = profCourses.get(profName);
			reviewCourse.addReview(review);
		}
		else {
			profCourses.put(profName, new ProfCourse(profName)); 
			
			//// UPDATE IN DATABASE ////
			
			profCourses.get(profName).addReview(review);
		}
			
	
		
		updateOverallScore();
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
		
		//// DATABASE ////
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
		
		//// DATABASE ////
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		
		//// DATABASE ////
	}

	public int getNumRatings() {
		return numRatings;
	}

	public void setNumRatings(int numRatings) {
		this.numRatings = numRatings;
		
		//// DATABASE ////
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
		
		//// DATABASE ////
	}

	public Score getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(Score overallScore) {
		this.overallScore = overallScore;
		
		//// DATABASE ////
	}

	public HashMap<Name, ProfCourse> getProfCourses() {
		return profCourses;
	}

	public void setProfCourses(HashMap<Name, ProfCourse> profCourses) {
		this.profCourses = profCourses;
		
		//// DATABASE ////
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
		
	//// DATABASE ////
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		
	//// DATABASE ////
	}

	public int getNumUsers() {
		return numUsers;
	}

	public void setNumUsers(int numUsers) {
		this.numUsers = numUsers;
		
	//// DATABASE ////
	}
	
	
}
