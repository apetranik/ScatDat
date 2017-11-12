package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

import scatalogObjects.Course;
import scatalogObjects.Name;
import scatalogObjects.ProfCourse;
import scatalogObjects.Review;
import scatalogObjects.Score;


public class Database {
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	public Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/scatdat?user=root&password=root&useSSL=false");
			st = conn.createStatement();
		}catch(SQLException sqle) {
			System.out.println (sqle.getMessage());
		}catch(ClassNotFoundException cnfe) {
			System.out.println (cnfe.getMessage());
		}
	}
	
	public String queryPassword(String username) {
		String name = username;
		PreparedStatement ps = null; 
		String password = "";
		try {
			ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM user WHERE username=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				password += rs.getString("password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // set first variable in prepared statement
		return password;
		
	}
	
	public String queryClassStanding(String username) {
		String name = username;
		PreparedStatement ps = null; 
		int classStanding = 0;
		try {
			ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM user WHERE username=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				classStanding = rs.getInt("classstanding");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // set first variable in prepared statement
		if(classStanding == 0) {
			return "Freshman";
		}
		else if(classStanding == 1) {
			return "Sophomore";
		}
		else if(classStanding == 2) {
			return "Junior";
		}
		else {
			return "Senior";
		}
	}
//	
//	public List<Course> queryCourseTaken(String username) {
//		// get the list of classID that this student has taken from the CourseTaken table
//			
//		// instantiate each class using queryCourse(int classID)
//		
//		// return the list of Course
//	}
//	
//	public ScoreMap queryScoreMap(String username) {
//		// get the 20 scores of the user
//		
//		// instantiate with ScoreMap with its constructor;
//	}
//	
//	public Course queryCourse(int classID) {
//		// call 
//		
//		// call score constructor
//	}
//	
//	public String queryCourseName(int classID) {
//		
//	}
//	
//	public String queryCourseDescription(int classID) {
//		
//	}
//	
//	public int queryNumberOfRatings(int classID) {
//		
//	}
//	
//	public int queryPrefix(int classID) {
//		
//	}
//	
//	public double queryOverallscore(int classID) {
//		
//	}
//	
//	public List<Review> queryReviews(int classID){
//		
//	}
//	
//	public int queryType(int classID) {
//		
//	}
//	
//	public Map<Name, ProfCourse> queryProfCourse(){
//		
//	}
//	
	
//	public Course(String courseName, int courseId, String description, int numRatings, String prefix,
//			Score overallScore, HashMap<Name, ProfCourse> profCourses, ArrayList<Review> reviews, int type,
//			int numUsers) {
//		this.courseName = courseName;
//		this.courseId = courseId;
//		this.description = description;
//		this.numRatings = numRatings;
//		this.prefix = prefix;
//		this.overallScore = overallScore;
//		this.profCourses = profCourses;
//		this.reviews = reviews;
//		this.type = type;
//		this.numUsers = numUsers;
//	}
	
	
	// IN PROGRESS
	public Vector<Course> returnCourses() {
		Vector<Course> courses = new Vector<Course>();
		
		PreparedStatement ps = null; 
		try {
			ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM course");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String courseName = rs.getString("name");
				int courseID = rs.getInt("number");
				String description = rs.getString("description");
				String prefix = rs.getString("prefix");
				
				int numRatings;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // set first variable in prepared statement
		
		return courses;
	}
	public static void main(String [] args) {
		Database db = new Database();
		String cs1 = db.queryClassStanding("gopalk");
		System.out.println(cs1);
		String cs2 = db.queryClassStanding("petranik");
		System.out.println(cs2);
		String cs3 = db.queryClassStanding("apurvaga");
		System.out.println(cs3);
	}
}
