package database;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class Database {
	
	public String queryPassword(String username) {
		
	}
	
	public String queryClassStanding(String username) {
		
	}
	
	public List<Course> queryCourseTaken(String username) {
		// get the list of classID that this student has taken from the CourseTaken table
			
		// instantiate each class using queryCourse(int classID)
		
		// return the list of Course
	}
	
	public ScoreMap queryScoreMap(String username) {
		// get the 20 scores of the user
		
		// instantiate with ScoreMap with its constructor;
	}
	
	public Course queryCourse(int classID) {
		// call 
		
		// call score constructor
	}
	
	public String queryCourseName(int classID) {
		
	}
	
	public String queryCourseDescription(int classID) {
		
	}
	
	public int queryNumberOfRatings(int classID) {
		
	}
}
