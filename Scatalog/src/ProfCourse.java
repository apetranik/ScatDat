import java.util.ArrayList;

import javax.xml.bind.SchemaOutputResolver;



public class ProfCourse {
	private Name name; 
	private Score overallScore; 
	private ArrayList<Review> reviews; 
	
	public ProfCourse() {
		//// DATABASE CONSTRUCTOR ////
	}
	
	public ProfCourse(Name professor) {
		name = professor; 
		overallScore = 0;
		reviews = new ArrayList<Review>(); 
	}
	
	public void updateOverallScore() { 
		long enjoyment = 0; 
		long value = 0; 
		long difficulty = 0; 
		long workload = 0; 
		for(Review review : reviews) {
			Score score = review.getScore(); 
			enjoyment += score.getEnjoyment(); 
			difficulty += score.getDifficulty(); 
			value += score.getValue(); 
			workload += score.getWorkload(); 
		}
		overallScore.setWorkload(workload/((double) reviews.size()));
		overallScore.setEnjoyment(enjoyment/((double) reviews.size()));
		overallScore.setValue(value/((double) reviews.size()));
		overallScore.setDifficulty(difficulty/((double) reviews.size()));
		ScoreMap calculator = new ScoreMap(); 
		overallScore.setOverallRating(calculator.computeOverallScore(overallScore)); 
		
		//// UPDATE IN DATABASE ////
	}
	
	public addReview(Review review) {
		reviews.add(review); 
		
		//// ADD TO DATABASE ////
		
		updateOverallScore();
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
		
	//// DATABASE ////
	}

	public Score getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(Score overallScore) {
		this.overallScore = overallScore;
		
	//// DATABASE ////
	}

	public ArrayList<Review> getReviews() {
		return Reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		Reviews = reviews;
		
	//// DATABASE ////
	}
	
	
	
}
