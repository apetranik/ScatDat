import java.util.ArrayList;
import java.util.HashMap;

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
	}
	
	public addReview(Review review) {
		reviews.add(review); 
		
		//// ADD TO DATABASE ////
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumRatings() {
		return numRatings;
	}

	public void setNumRatings(int numRatings) {
		this.numRatings = numRatings;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Score getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(Score overallScore) {
		this.overallScore = overallScore;
	}

	public HashMap<Name, ProfCourse> getProfCourses() {
		return profCourses;
	}

	public void setProfCourses(HashMap<Name, ProfCourse> profCourses) {
		this.profCourses = profCourses;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNumUsers() {
		return numUsers;
	}

	public void setNumUsers(int numUsers) {
		this.numUsers = numUsers;
	}
	
	
}
