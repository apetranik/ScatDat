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

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;

import scatalogObjects.Course;
import tools.ScoreMap;


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
		// rs = st.executeQuery("SELECT * from Student where fname='" + name + "'");
		PreparedStatement ps = null; 
		String fullName = "";
		try {
			ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM user WHERE username=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				fullName += rs.getString("fname");
				fullName += " ";
				fullName += rs.getString("lname");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // set first variable in prepared statement
		return fullName;
		
	}
	public static void main(String [] args) {
		Database db = new Database();
		String name = db.queryPassword("gopalk");
		System.out.println(name);
		String otherName = db.queryPassword("petranik");
		System.out.println(otherName);
	}
	
//	public String queryClassStanding(String username) {
//		
//	}
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
}
