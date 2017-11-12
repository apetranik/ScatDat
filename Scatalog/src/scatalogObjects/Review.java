package scatalogObjects;

public class Review {
	private String review;
	private Score score;
	private Date date;
	private User user;
	private CourseTime courseTime;
	private String emoji;
	private String syllabus;
	private Name professor;
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public CourseTime getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(CourseTime courseTime) {
		this.courseTime = courseTime;
	}
	public String getEmoji() {
		return emoji;
	}
	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}
	public String getSyllabus() {
		return syllabus;
	}
	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}
	public Name getProfessor() {
		return professor;
	}
	public void setProfessor(Name professor) {
		this.professor = professor;
	}
	
}
