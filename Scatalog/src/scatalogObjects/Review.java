<<<<<<< HEAD:Scatalog/src/scatalogObjects/Review.java
package scatalogObjects;
=======
import java.util.Date;
>>>>>>> 1842f616eb91082964688e1bf5e66c63deea2686:Scatalog/src/Review.java

public class Review {
	private String review;
	private Score score;
	private Date date;
	private User user;
	private CourseTime courseTime;
	private String emoji;
	private String syllabus;
	private Name professor;
	public Review(String review, Score score, Date date, User user, CourseTime courseTime, String emoji,
			String syllabus, Name professor) {
		super();
		this.review = review;
		this.score = score;
		this.date = date;
		this.user = user;
		this.courseTime = courseTime;
		this.emoji = emoji;
		this.syllabus = syllabus;
		this.professor = professor;
		// DATABASE
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
		// DATABASE
	}
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
		// DATABASE
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
		// DATABASE
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
		// DATABASE
	}
	public CourseTime getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(CourseTime courseTime) {
		this.courseTime = courseTime;
		// DATABASE
	}
	public String getEmoji() {
		return emoji;
	}
	public void setEmoji(String emoji) {
		this.emoji = emoji;
		// DATABASE
	}
	public String getSyllabus() {
		return syllabus;
	}
	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
		// DATABASE
	}
	public Name getProfessor() {
		return professor;
	}
	public void setProfessor(Name professor) {
		this.professor = professor;
		// DATABASE
	}
	
}
