package scatalogObjects;
import java.util.Vector;

public class Department {
	private String prefix;
	private String name;
	private Vector<Course> courses;
	public Department(String prefix, String name) {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<Course> getCourses() {
		return courses;
	}
}
