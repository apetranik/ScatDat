package scatalogObjects;
import java.util.Vector;

public class Department {
	private String prefix;
	private Vector<Course> courses;
	public Department(String prefix) {
		this.prefix = prefix;
		courses = new Vector<Course>();
	}
	public void addCourse(Course course) {
		this.courses.add(course);
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
}
