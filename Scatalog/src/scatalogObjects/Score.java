package scatalogObjects;

public class Score {
	double overallRating; 
	double enjoyment; 
	double difficulty; 
	double value; 
	double workload;
	
	//// DATABASE FOR SETTERS ////
	
	public double getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(double overallRating) {
		this.overallRating = overallRating;
	}
	public double getEnjoyment() {
		return enjoyment;
	}
	public void setEnjoyment(double enjoyment) {
		this.enjoyment = enjoyment;
	}
	public double getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getWorkload() {
		return workload;
	}
	public void setWorkload(double workload) {
		this.workload = workload;
	} 
	
}
