package database;

import java.awt.RadialGradientPaint;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.naming.spi.DirStateFactory.Result;

import com.mysql.jdbc.PreparedStatement;

import scatalogObjects.Badge;
import scatalogObjects.Course;
import scatalogObjects.Name;
import scatalogObjects.ProfCourse;
import scatalogObjects.Review;
import scatalogObjects.Score;
import scatalogObjects.User;
import tools.ScoreMap;


public class Database {
	private Connection conn = null;
	public Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/scatdat?user=root&password=root&useSSL=false");
		}catch(SQLException sqle) {
			System.out.println (sqle.getMessage());
		}catch(ClassNotFoundException cnfe) {
			System.out.println (cnfe.getMessage());
		}
	}
	
	public void insertUser(User user) {
		PreparedStatement ps = null;
		//int rs;
		try {
			ps = (PreparedStatement) conn.prepareStatement("INSERT INTO user (email, password, username, classstanding, fname, lname) VALUES (?,?,?,?,?,?)");
			String email = user.getEmail();
			String username = user.getUsername();
			String password = user.getUsername();
			String classstanding = user.getClassStanding();
			Name name = user.getName();
			String fname = name.getFname();
			String lname = name.getLname();
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, username);
			ps.setString(4, classstanding);
			ps.setString(5, fname);
			ps.setString(6, lname);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String queryPassword(String username) {
		String name = username;
		PreparedStatement ps = null; 
		ResultSet rs = null;
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
		ResultSet rs = null;
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
	public List<Course> queryCourseTaken(String username) {
		try {
			Statement st = conn.createStatement();
			ResultSet rs;
			rs = st.executeQuery("SELECT * FROM user u, coursetaken ct, course c WHERE username='" + username + 
					"' AND u.userID=ct.userID AND ct.courseID=c.courseID");
			while(rs.next()) {
				int courseID = rs.getInt("courseID");
//				System.out.println(courseID);
				String courseName = rs.getString("name");
//				System.out.println(courseName);
				String description = rs.getString("description");
//				System.out.println(description);
				int numRatings = queryNumOfRatings(courseID);
//				System.out.println(numRatings);
				String prefix = rs.getString("prefix");
				double enjoyment = rs.getDouble("enjoyment");
				double difficulty = rs.getDouble("difficulty");
				double value = rs.getDouble("value");
				double workload = rs.getDouble("workload");
				//Score score = new Score(enjoyment, difficulty, value, workload);
				
				
			}
			
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		
		
		// get the list of classID that this student has taken from the CourseTaken table
			
		// instantiate each class using queryCourse(int classID)
		
		// return the list of Course
	}

	private Map<Name, ProfCourse> queryProfCourseMap(int courseID){
		try {
			Map<Integer, ProfCourse> buffer = new HashMap<Integer, ProfCourse>();
			Statement st = conn.createStatement();
			ResultSet rs;
			rs = st.executeQuery("SELECT * FROM review r, professor p WHERE courseID='" + courseID + "' AND r.professorID=p.professorID");
			while(rs.next()) {
				if(!buffer.containsKey(rs.getInt("professorID"))) {
					
				}
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				Name name = new Name(fname, lname);
			}
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
	
	}
	
	private ProfCourse querySingleProfCourse(int professorID, int courseID) {
		try {
			Statement st = conn.createStatement();
			// get the name of the professor
			ResultSet rs1 = st.executeQuery("SELECT * FROM professor WHERE professorID=" + professorID + "'");
			rs1.next();
			Name name = new Name(rs1.getString("fname"), rs1.getString("lname"));
			// get all reviews
			ResultSet rs;
			rs = st.executeQuery("SELECT * FROM review r, user u WHERE courseID='" + courseID + "' AND professorID=" + professorID + "'");
			Score overallScore = new Score();
			List<Review> reviews = new ArrayList<Review>();
			long enjoyment = 0; 
			long value = 0; 
			long difficulty = 0; 
			long workload = 0;
			int count = 0;
			while(rs.next()) {
				// compute score
				enjoyment += rs.getInt("enjoyment");
				value += rs.getInt("value");
				difficulty += rs.getInt("difficulty");
				workload += rs.getInt("workload");
				count++;
				// construct review
				reviews.add(new Review(rs.getString("comment"), score, rs.getDate("date"), , courseTime, emoji, syllabus, professor))
			}
			overallScore.setEnjoyment(enjoyment/((double) count));
			overallScore.setValue(value/((double) count));
			overallScore.setDifficulty(difficulty/((double) count));
			overallScore.setWorkload(workload/((double) count));
			ScoreMap calculator = new ScoreMap(); 
			overallScore.setOverallRating(calculator.computeOverallScore(overallScore)); 
			
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		
		
	}
	
	private int queryNumOfRatings(int classID) {
		int num = 0;
		try {
			ResultSet rs;
			Statement st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM review WHERE courseID='" + classID + "'");
			rs.last();
			num = rs.getRow();
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return num;
	}
	
	
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
		ResultSet rs = null;
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
//		String cs1 = db.queryClassStanding("gopalk");
//		System.out.println(cs1);
//		String cs2 = db.queryClassStanding("petranik");
//		System.out.println(cs2);
//		String cs3 = db.queryClassStanding("apurvaga");
//		System.out.println(cs3);
		Name name = new Name("Quisi", "Li");
		User user = new User(name, "abcde", "2", "quisili@usc.edu", null,
				null, null, "quisili", null,
				null, null);
		db.insertUser(user);
		//db.queryCourseTaken("gopalk");
	}
}
