package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import scatalogObjects.Course;
import tools.ScoreMap;

public class Database {
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	
	public Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/studentgrades?user=root&password=root&useSSL=false");
			st = conn.createStatement();
		}catch(SQLException sqle) {
			System.out.println (sqle.getMessage());
		}catch(ClassNotFoundException cnfe) {
			System.out.println (cnfe.getMessage());
		}
	}
	
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
	
	public int queryPrefix(int classID) {
		
	}
	
	public double queryOverallscore(int classID) {
		
	}
	
	public List<Review> queryReviews(int classID){
		
	}
	
	public int queryType(int classID) {
		
	}
	
	public Map<Name, ProfCourse> queryProfCourse(){
		
	}
	
}
