
import java.util.Vector;


public class Server {
	private Vector<User> users; 
	private Vector<Course> courses; 
	public void createUser(String fname, String lname, String password, String classStanding, String email) {
		
		///// ALSO ADD TO DATABASE! /////
		
		users.add(new User(fname, lname, password, classStanding, email))); 
		
	}
	
	public void addReview(int difficulty, int workload, int enjoyment, int value, String review, 
			User user, String professor, String syllabus) {
		
		///// ALSO ADD TO DATABASE! /////
		
		Score score = new Score(difficulty, workload, enjoyment, value); 
		Review newReview = new Review(score, review, user, professor, syllabus); 
		
	}

}
