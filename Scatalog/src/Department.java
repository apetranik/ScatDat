import java.util.Vector;

public class Department {
	private String prefix;
	private Vector<Course> courses;
	public Department(String prefix) {
		this.prefix = prefix;
		// DATABASE
	}
	public void addCourse(Course course) {
		this.courses.add(course);
		// DATABASE
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public Vector<Course> getCourses() {
		return courses;
	}
	public void setCourses(Vector<Course> courses) {
		this.courses = courses;
	}
	
}
