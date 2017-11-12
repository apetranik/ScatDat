

public class CourseTime {
	private String term;
	private String year;
	
	public CourseTime(String term, String year) {
		super();
		this.term = term;
		this.year = year;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
		// DATABASE
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
		// DATABASE
	}
	
}
