package scatalogObjects;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import tools.ScoreMap;

public class User {
	private Name name;
	private String classStanding;
	private String major; 
	private String email;
	private Vector<Badge> badges;
	private Vector<Course> wishlist;
	private Vector<Course> coursesEvaluated;
	private String username;
	private Set<Course> coursesTaken;
	private Vector<Course> courseList;
	private ScoreMap preferredRatingStyle;
	public User(Name name, String classStanding, String email, Vector<Badge> badges,
			Vector<Course> wishlist, Vector<Course> coursesEvaluated, String username, Set<Course> coursesTaken,
			Vector<Course> courseList, ScoreMap preferredRatingStyle, String major) {
		super();
		this.name = name;
		this.classStanding = classStanding;
		this.email = email;
		this.badges = badges;
		this.wishlist = wishlist;
		this.coursesEvaluated = coursesEvaluated;
		this.username = username;
		this.coursesTaken = coursesTaken;
		this.courseList = courseList;
		this.preferredRatingStyle = preferredRatingStyle;
		this.major = major; 
	}
	public User() {
		// DATA BASE
	}
	
	//constructor for hardcoding testing purposes. Does not initialize all data members.
	public User(String fname, String lname, String classStanding, String email, String major) {
		this.name = new Name(fname, lname);
		this.classStanding = classStanding;
		this.email = email;
		badges = new Vector<Badge>();
		wishlist = new Vector<Course>();
		coursesEvaluated = new Vector<Course>();
		username = email.substring(0, email.indexOf('@')); 
		coursesTaken = new HashSet<Course>();
		courseList = new Vector<Course>(); 
		preferredRatingStyle = new ScoreMap(); 
		this.major = major; 
		
	}
	public void addToWishlist(Course course) {
		this.wishlist.add(course);
		// DATA BASE
	}
	public void addToCoursesTaken(Course course) {
		this.coursesTaken.add(course);
		// DATA BASE
	}
	public void addToCourseList(Course course) {
		this.courseList.add(course);
		// DATA BASE
	}
	public void addBadge(Badge badge) {
		this.badges.add(badge);
		// DATA BASE
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
		// DATA BASE
	}
	public String getClassStanding() {
		return classStanding;
	}
	public void setClassStanding(String classStanding) {
		this.classStanding = classStanding;
		// DATA BASE
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
		// DATA BASE
	}
	public Vector<Badge> getBadges() {
		return badges;
	}
	public void setBadges(Vector<Badge> badges) {
		this.badges = badges;
		// DATA BASE
	}
	public Vector<Course> getWishlist() {
		return wishlist;
	}
	public void setWishlist(Vector<Course> wishlist) {
		this.wishlist = wishlist;
		// DATA BASE
	}
	public Vector<Course> getCoursesEvaluate() {
		return coursesEvaluated;
	}
	public void setCoursesEvaluate(Vector<Course> coursesEvaluate) {
		this.coursesEvaluated = coursesEvaluate;
		// DATA BASE
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
		// DATA BASE
	}
	public Set<Course> getCoursesTaken() {
		return coursesTaken;
	}
	public void setCoursesTaken(Set<Course> coursesTaken) {
		this.coursesTaken = coursesTaken;
		// DATA BASE
	}
	public Vector<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(Vector<Course> courseList) {
		this.courseList = courseList;
		// DATA BASE
	}
	public ScoreMap getPreferredRatingStyle() {
		return preferredRatingStyle;
	}
	public void setPreferredRatingStyle(ScoreMap preferredRatingStyle) {
		this.preferredRatingStyle = preferredRatingStyle;
		// DATA BASE
	}
	public void addCourseTaken(Course course) {
		addToCoursesTaken(course);
		addToCourseList(course);
		//NO NEED FOR DATABASE
	}
	
	public void setMajor(String major) {
		this.major = major; 
	}
	
	public String getMajor() {
		return major; 
	}
}