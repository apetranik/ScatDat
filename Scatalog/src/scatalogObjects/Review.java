
package scatalogObjects;
import java.util.Date;

public class Review {
	private String review;
	private Score score;
	private Date date;
	private String username;
	private CourseTime courseTime;
	private String emoji;
	private Name professor;
	public Review(String review, Score score, Date date, String username, CourseTime courseTime, String emoji, Name professor) {
		super();
		this.review = review;
		this.score = score;
		this.date = date;
		this.username = username;
		this.courseTime = courseTime;
		this.emoji = emoji;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public Name getProfessor() {
		return professor;
	}
	public void setProfessor(Name professor) {
		this.professor = professor;
		// DATABASE
	}
	
}
