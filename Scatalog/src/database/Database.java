package database;

import java.awt.RadialGradientPaint;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.naming.spi.DirStateFactory.Result;

import com.mysql.jdbc.PreparedStatement;

import scatalogObjects.Badge;
import scatalogObjects.Course;
import scatalogObjects.CourseTime;
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
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
	}

	// Insert user into database
	public void insertUser(User user) {
		PreparedStatement ps = null;
		// Try to insert user into user database table
		try {
			ps = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO user (email, username, classstanding, fname, lname, preferredRatingStyle, major) VALUES (?,?,?,?,?,?,?)");
			String email = user.getEmail();
			String username = user.getUsername();
			String password = user.getUsername();
			String classstanding = user.getClassStanding();

			Name name = user.getName();
			String fname = name.getFname();
			String lname = name.getLname();
			String major = user.getMajor();

			ps.setString(1, email);

			ps.setString(2, username);
			int classNum = 0;
			if (classstanding.trim().toLowerCase().equals("freshman")) {
				classNum = 0;
			} else if (classstanding.trim().toLowerCase().equals("sophomore")) {
				classNum = 1;
			} else if (classstanding.trim().toLowerCase().equals("junior")) {
				classNum = 2;
			} else {
				classNum = 3;
			}
			ps.setInt(3, classNum);
			ps.setString(4, fname);
			ps.setString(5, lname);
			ps.setString(7, major);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addToUserCourselist(User user, Course course) {

		// Select user from database
		PreparedStatement ps = null;
		ResultSet rs = null;
		User currentUser = null;
		int userID = 0;
		// get the course ID based on the course inputted
		int courseID = returnCourseID(course.getPrefix(), course.getCourseId());

		// Get the user and their id
		try {
			ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM user WHERE email=?");
			ps.setString(1, user.getEmail());
			rs = ps.executeQuery();
			while (rs.next()) {
				userID = rs.getInt("userID");
			}

			// Insert course into coursesTaken table
			try {
				ps = (PreparedStatement) conn
						.prepareStatement("INSERT INTO courseTaken (userID, courseID) VALUES (?,?)");

				ps.setInt(1, userID);
				ps.setInt(2, courseID);

				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			finally {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
	}

	public  void insertReview(Review review, int courseID) {
		Score score = review.getScore();
		double overallrating = score.getOverallRating();
		double enjoyment = score.getEnjoyment();
		double difficulty = score.getDifficulty();
		double value = score.getValue();
		double workload = score.getWorkload();
		Date date = (Date)review.getDate();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		System.out.println(sqlDate.toString());
		String username = review.getUsername();
		int userID = 0;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM user WHERE username='" + username + "'");
			rs.next();
			userID = rs.getInt("userID");
			System.out.println(userID);

		} catch (SQLException sqle) {
			System.out.println("Get userid: "+sqle.getMessage());
		}
		CourseTime courseTime = review.getCourseTime();
		String term = courseTime.getTerm();
		int year = Integer.parseInt(courseTime.getYear());
		Name name = review.getProfessor();
		String fname = name.getFname();
		String lname = name.getLname();
		int professorID = 0;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT * FROM professor WHERE fname='" + fname + "' AND lname='" + lname + "'");
			Boolean empty = true; 
			if(rs.next()) {
				empty = false;
			}
			if(!empty) {
				professorID = rs.getInt("professorID");
				System.out.println(fname);
				System.out.println(lname);
				System.out.println("professor id is " + professorID);
			}
			else {
				System.out.println("add a new professor");
				try {
					PreparedStatement ps = (PreparedStatement) conn.prepareStatement("INSERT INTO professor (fname, lname) VALUES (?,?)");
					ps.setString(1, fname);
					ps.setString(2, lname);
					ps.execute();
					rs = st.executeQuery("SELECT * FROM professor WHERE fname='" + fname + "' AND lname='" + lname + "'");
					rs.next();
					professorID = rs.getInt("professorID");
				}catch(SQLException sqle) {
					System.out.println("Get Professor insert professor problem: " +sqle.getMessage());
				}
			}
		} catch (SQLException sqle) {
			System.out.println("Get Professor: " +sqle.getMessage());
		}
		String emoji = review.getEmoji();
		String comment = review.getReview();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO review (userID, courseID, term, year, professorID, overallScore, enjoyment, difficulty, value, workload, emoji, comment, date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, userID);
			ps.setInt(2, courseID);
			ps.setString(3, term);
			ps.setInt(4, year);
			ps.setInt(5, professorID);
			ps.setDouble(6, overallrating);
			ps.setDouble(7, enjoyment);
			ps.setDouble(8, difficulty);
			ps.setDouble(9, value);
			ps.setDouble(10, workload);
			ps.setString(11, emoji);
			ps.setString(12, comment);
			ps.setDate(13, sqlDate);
			ps.execute();
		} catch (SQLException sqle) {
			System.out.println("INSERT COURSE ERROR: " + sqle.getMessage());
		}
	}

	// public String queryPassword(String username) {
	// String name = username;
	// PreparedStatement ps = null;
	// ResultSet rs = null;
	// String password = "";
	// try {
	// ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM user WHERE
	// username=?");
	// ps.setString(1, name);
	// rs = ps.executeQuery();
	//
	// while (rs.next()) {
	// password += rs.getString("password");
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } // set first variable in prepared statement
	// return password;
	//
	// }

	public  User queryUser(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		User currentUser = null;
		int userID = 0;
		try {
			ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM user WHERE email=?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				userID = rs.getInt("userID");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				Name name = new Name(fname, lname);
				int classNum = rs.getInt("classstanding");
				String major = rs.getString("major");
				String username = rs.getString("username");
				int preferredRatingStyle = rs.getInt("preferredRatingStyle");
				Vector<Course> courseList = queryCourseTaken(username);
				Set<Course> coursesTaken = new HashSet<Course>();
				for (int i = 0; i < courseList.size(); i++) {
					coursesTaken.add(courseList.get(i));
				}

				Vector<Course> coursesEvaluated = queryCourseEvaluated(userID);
				Vector<Course> wishlist = queryWishlist(userID);
				ScoreMap scoreMap = new ScoreMap(preferredRatingStyle);
				Vector<Badge> badges = new Vector();
				badges.add(new Badge(preferredRatingStyle));
				String classStanding = "";
				if (classNum == 0) {
					classStanding = "Freshman";
				} else if (classNum == 1) {
					classStanding = "Sophomore";
				} else if (classNum == 2) {
					classStanding = "Junior";
				} else {
					classStanding = "Senior";
				}
				currentUser = new User(name, classStanding, email, badges, wishlist, coursesEvaluated, username,
						coursesTaken, courseList, scoreMap, major);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return currentUser;
	}

	public  Vector<Course> queryWishlist(int userID) {
		Vector<Course> courses = new Vector<Course>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs;
			rs = st.executeQuery(
					"SELECT c.courseID, c.name, c.number, c.description, c.prefix, c.enjoyment, c.difficulty, c.value, c.workload, c.type, c.numRegistered FROM wishlist w, course c\n"
							+ "	WHERE c.courseID = w.courseID AND w.userID =" + userID);
			while (rs.next()) {
				int courseID = rs.getInt("courseID");
				String courseName = rs.getString("name");
				int number = Integer.parseInt(rs.getString("number"));
				String description = rs.getString("description");
				int numRatings = queryNumOfRatings(courseID);
				String prefix = rs.getString("prefix");
				double enjoyment = rs.getDouble("enjoyment");
				double difficulty = rs.getDouble("difficulty");
				double value = rs.getDouble("value");
				double workload = rs.getDouble("workload");
				Score overallScore = new Score(enjoyment, difficulty, value, workload);
				HashMap<Name, ProfCourse> profCourses = queryProfCourseMap(courseID);
				ArrayList<Review> reviews = queryAllReview(courseID);
				int type = Integer.parseInt(rs.getString("type"));
				int numUsers = rs.getInt("numRegistered");
				courses.add(new Course(courseName, number, description, prefix, enjoyment, value, workload, difficulty,
						type));
			}

		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return courses;
	}

	public  Vector<Course> queryCourseEvaluated(int userID) {
		Vector<Course> courses = new Vector<Course>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs;
			rs = st.executeQuery(
					"SELECT c.courseID, c.name, c.number, c.description, c.prefix, c.enjoyment, c.difficulty, c.value, c.workload, c.type, c.numRegistered FROM review r, user u, course c\n"
							+ "	WHERE r.userID = u.userID AND c.courseID = r.courseID AND u.userID =" + userID);
			while (rs.next()) {
				int courseID = rs.getInt("courseID");
				String courseName = rs.getString("name");
				int number = Integer.parseInt(rs.getString("number"));
				String description = rs.getString("description");
				int numRatings = queryNumOfRatings(courseID);
				String prefix = rs.getString("prefix");
				double enjoyment = rs.getDouble("enjoyment");
				double difficulty = rs.getDouble("difficulty");
				double value = rs.getDouble("value");
				double workload = rs.getDouble("workload");
				Score overallScore = new Score(enjoyment, difficulty, value, workload);
				HashMap<Name, ProfCourse> profCourses = queryProfCourseMap(courseID);
				ArrayList<Review> reviews = queryAllReview(courseID);
				int type = Integer.parseInt(rs.getString("type"));
				int numUsers = rs.getInt("numRegistered");
				courses.add(new Course(courseName, number, description, prefix, enjoyment, value, workload, difficulty,
						type));
			}

		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return courses;
	}

	public  String queryClassStanding(String username) {
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
		if (classStanding == 0) {
			return "Freshman";
		} else if (classStanding == 1) {
			return "Sophomore";
		} else if (classStanding == 2) {
			return "Junior";
		} else {
			return "Senior";
		}
	}

	//
	public  Vector<Course> queryCourseTaken(String username) {
		Vector<Course> courses = new Vector<Course>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs;
			rs = st.executeQuery("SELECT * FROM user u, coursetaken ct, course c WHERE username='" + username
					+ "' AND u.userID=ct.userID AND ct.courseID=c.courseID");
			while (rs.next()) {
				int courseID = rs.getInt("courseID");
				// System.out.println(courseID);
				String courseName = rs.getString("name");
				int number = Integer.parseInt(rs.getString("number"));
				// System.out.println(courseName);
				String description = rs.getString("description");
				// System.out.println(description);
				int numRatings = queryNumOfRatings(courseID);
				// System.out.println(numRatings);
				String prefix = rs.getString("prefix");
				double enjoyment = rs.getDouble("enjoyment");
				double difficulty = rs.getDouble("difficulty");
				double value = rs.getDouble("value");
				double workload = rs.getDouble("workload");
				// Score score = new Score(enjoyment, difficulty, value, workload);
				Score overallScore = new Score(enjoyment, difficulty, value, workload);
				HashMap<Name, ProfCourse> profCourses = queryProfCourseMap(courseID);
				ArrayList<Review> reviews = queryAllReview(courseID);
				int type = Integer.parseInt(rs.getString("type"));
				int numUsers = rs.getInt("numRegistered");
				courses.add(new Course(courseName, number, description, prefix, enjoyment, value, workload, difficulty,
						type));
			}

		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return courses;
	}

	public  ArrayList<Course> queryCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM course");
			rs = ps.executeQuery();
			while (rs.next()) {
				int courseID = rs.getInt("courseID");
				String courseName = rs.getString("name");
				int number = Integer.parseInt(rs.getString("number"));
				String description = rs.getString("description");
				String prefix = rs.getString("prefix");
				int numRatings = queryNumOfRatings(courseID);
				double enjoyment = rs.getDouble("enjoyment");
				double difficulty = rs.getDouble("difficulty");
				double value = rs.getDouble("value");
				double workload = rs.getDouble("workload");
				int type = Integer.parseInt(rs.getString("type"));
				// System.out.println("CourseID: " + courseID);
				// System.out.println("Prefix: " + prefix);
				// System.out.println("Number: " + number);
				// System.out.println("CourseName: " + courseName);
				// System.out.println("Description: " + description);
				// System.out.println("Num of Ratings: " + numRatings);
				// System.out.println("Enjoyment: " + enjoyment);
				// System.out.println("Difficulty: " + difficulty);
				// System.out.println("Value: " + value);
				// System.out.println("Workload: " + workload);
				// System.out.println("Type: " + type);
				// Score overallScore = new Score(enjoyment, difficulty, value, workload);
				// HashMap<Name, ProfCourse> profCourses = queryProfCourseMap(courseID);
				// ArrayList<Review> reviews = queryAllReview(courseID);
				// int numUsers = rs.getInt("numRegistered");
				// courses.add(new Course(courseName, number, description, numRatings, prefix,
				// overallScore, profCourses, reviews, type, numUsers));
				courses.add(new Course(courseName, number, description, prefix, enjoyment, value, workload, difficulty,
						type));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courses;
	}

	private  HashMap<Name, ProfCourse> queryProfCourseMap(int courseID) {
		HashMap<Name, ProfCourse> result = new HashMap<Name, ProfCourse>();
		try {
			Set<Integer> finishedProfessor = new HashSet<Integer>();
			Statement st = conn.createStatement();
			ResultSet rs;
			rs = st.executeQuery("SELECT * FROM review r, professor p WHERE courseID='" + courseID
					+ "' AND r.professorID=p.professorID");
			while (rs.next()) {
				int professorID = rs.getInt("professorID");
				if (!finishedProfessor.contains(professorID)) {
					String fname = rs.getString("fname");
					String lname = rs.getString("lname");
					Name name = new Name(fname, lname);
					ProfCourse singleProfCourse = querySingleProfCourse(professorID, courseID);
					result.put(name, singleProfCourse);
					finishedProfessor.add(professorID);
				}
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return result;
	}

	private  ProfCourse querySingleProfCourse(int professorID, int courseID) {
		ProfCourse profCourse = null;
		try {
			Statement st = conn.createStatement();
			// get the name of the professor
			ResultSet rs1 = st.executeQuery("SELECT * FROM professor WHERE professorID='" + professorID + "'");
			rs1.next();
			Name name = new Name(rs1.getString("fname"), rs1.getString("lname"));
			// get all reviews
			ResultSet rs;
			rs = st.executeQuery("SELECT * FROM review r, user u WHERE courseID='" + courseID + "' AND professorID='"
					+ professorID + "' AND r.userID=u.userID");
			ArrayList<Review> reviews = new ArrayList<Review>();
			long enjoyment = 0;
			long value = 0;
			long difficulty = 0;
			long workload = 0;
			int count = 0;
			while (rs.next()) {
				// compute score
				enjoyment += rs.getInt("enjoyment");
				value += rs.getInt("value");
				difficulty += rs.getInt("difficulty");
				workload += rs.getInt("workload");
				count++;
				// construct review
				reviews.add(new Review(rs.getString("comment"),
						new Score(rs.getInt("enjoyment"), rs.getInt("difficulty"), rs.getInt("value"),
								rs.getInt("workload")),
						rs.getDate("date"), rs.getString("username"),
						new CourseTime(rs.getString("term"), rs.getString("year")), rs.getString("emoji"), name));
			}
			Score overallScore = new Score();
			overallScore.setEnjoyment(enjoyment / ((double) count));
			overallScore.setValue(value / ((double) count));
			overallScore.setDifficulty(difficulty / ((double) count));
			overallScore.setWorkload(workload / ((double) count));
			ScoreMap calculator = new ScoreMap();
			overallScore.setOverallRating(calculator.computeOverallScore(overallScore));
			profCourse = new ProfCourse(name, overallScore, reviews);

		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return profCourse;

	}

	private  int queryNumOfRatings(int classID) {
		int num = 0;
		try {
			ResultSet rs;
			Statement st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM review WHERE courseID='" + classID + "'");
			rs.last();
			num = rs.getRow();
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return num;
	}

	public  ArrayList<Review> queryAllReview(int classID) {
		ArrayList<Review> reviews = new ArrayList<Review>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs;
			rs = st.executeQuery("SELECT * FROM review r, user u, professor p WHERE courseID='" + classID
					+ "' AND r.professorID=p.professorID AND r.userID=u.userID");
			while (rs.next()) {
				reviews.add(new Review(rs.getString("comment"),
						new Score(rs.getInt("enjoyment"), rs.getInt("difficulty"), rs.getInt("value"),
								rs.getInt("workload")),
						rs.getDate("date"), rs.getString("username"),
						new CourseTime(rs.getString("term"), rs.getString("year")), rs.getString("emoji"),
						new Name(rs.getString("p.fname"), rs.getString("p.lname"))));
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return reviews;
	}

	public  int returnCourseID(String prefix, int number) {
		int courseID = 0;
		try {
			Statement st = conn.createStatement();
			ResultSet rs;
			rs = st.executeQuery("SELECT * FROM course WHERE prefix='" + prefix + "' AND number=" + number);
			while (rs.next()) {
				courseID = rs.getInt("courseID");
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}

		return courseID;
	}

	public Course returnCourse(String prefix, int number) {
		ArrayList<Course> courses = queryCourses();
		Course currCourse = null;
		for (int i = 0; i < courses.size(); i++) {
			currCourse = courses.get(i);
			if (currCourse.getPrefix().equals(prefix) && currCourse.getCourseId() == number) {
				return currCourse;
			}
		}
		return currCourse;

	}
	//
	// public ScoreMap queryScoreMap(String username) {
	// // get the 20 scores of the user
	//
	// // instantiate with ScoreMap with its constructor;
	// }
	//

	// public int queryPrefix(int classID) {
	//
	// }
	//
	// public double queryOverallscore(int classID) {
	//
	// }
	//
	// public List<Review> queryReviews(int classID){
	//
	// }
	//
	// public int queryType(int classID) {
	//
	// }
	//
	// public Map<Name, ProfCourse> queryProfCourse(){
	//
	// }
	//

	// public Course(String courseName, int courseId, String description, int
	// numRatings, String prefix,
	// Score overallScore, HashMap<Name, ProfCourse> profCourses, ArrayList<Review>
	// reviews, int type,
	// int numUsers) {
	// this.courseName = courseName;
	// this.courseId = courseId;
	// this.description = description;
	// this.numRatings = numRatings;
	// this.prefix = prefix;
	// this.overallScore = overallScore;
	// this.profCourses = profCourses;
	// this.reviews = reviews;
	// this.type = type;
	// this.numUsers = numUsers;
	// }

	public void main(String[] args) {
		Database db = new Database();
		// db.queryCourses();

		String prefix = "ALI";
		int number = 254;
		int courseID = db.returnCourseID(prefix, number);
		System.out.println("Course ID: " + courseID);
	}
}