package scatalogObjects;
import java.util.Vector;

public class Department {
	private String prefix;
	private String name;
	private String school;
	private Vector<Course> courses;
	public Department(String prefix, String name, String school) {
		this.prefix = prefix;
		this.name = name;
		this.school = school;
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
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSchool() {
		return school;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<Course> getCourses() {
		return courses;
	}
}