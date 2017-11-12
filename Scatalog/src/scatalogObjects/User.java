package scatalogObjects;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class User {
	private Name name;
	private String password;
	private String classStanding;
	private String email;
	private Vector<Badge> badges;
	private Vector<Course> wishlist;
	private Vector<Course> coursesEvaluate;
	private String username;
	private Set<Course> coursesTaken;
	private Vector<Course> courseList;
	private ScoreMap preferredRatingStyle;
	public User() {
		// DATA BASE
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
		return coursesEvaluate;
	}
	public void setCoursesEvaluate(Vector<Course> coursesEvaluate) {
		this.coursesEvaluate = coursesEvaluate;
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
}