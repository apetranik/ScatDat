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
			enjoyment += score.get
		}
	}
}
