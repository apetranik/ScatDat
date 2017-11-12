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
	}
	public void addToCoursesTaken(Course course) {
		this.coursesTaken.add(course);
	}
	public void addToCourseList(Course course) {
		this.courseList.add(course);
	}
	public void addBadge(Badge badge) {
		this.badges.add(badge);
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getClassStanding() {
		return classStanding;
	}
	public void setClassStanding(String classStanding) {
		this.classStanding = classStanding;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Vector<Badge> getBadges() {
		return badges;
	}
	public void setBadges(Vector<Badge> badges) {
		this.badges = badges;
	}
	public Vector<Course> getWishlist() {
		return wishlist;
	}
	public void setWishlist(Vector<Course> wishlist) {
		this.wishlist = wishlist;
	}
	public Vector<Course> getCoursesEvaluate() {
		return coursesEvaluate;
	}
	public void setCoursesEvaluate(Vector<Course> coursesEvaluate) {
		this.coursesEvaluate = coursesEvaluate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<Course> getCoursesTaken() {
		return coursesTaken;
	}
	public void setCoursesTaken(Set<Course> coursesTaken) {
		this.coursesTaken = coursesTaken;
	}
	public Vector<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(Vector<Course> courseList) {
		this.courseList = courseList;
	}
	public ScoreMap getPreferredRatingStyle() {
		return preferredRatingStyle;
	}
	public void setPreferredRatingStyle(ScoreMap preferredRatingStyle) {
		this.preferredRatingStyle = preferredRatingStyle;
	}
}