package tools;

import java.util.Vector;

import scatalogObjects.Score;

/*
	The ScoreMap class is at the heart of the course rating algorithm. 
	It allows for tremendous flexibility in rating method by allowing a user
	to define arbitrary weights for each score for each attribute. It allows 
	an *almost arbitrary function from attribute values (value, enjoyment, workload
	and difficulty) to the overall score with both range and domain [1, 5]. 

	*It is not completely/technically arbitrary as the computeOverallScore() function rounds the
	input attributes from the continuous space of real numbers from [1, 5] to the discrete 
	space of natural numbers from [1, 5]. 
*/
public class ScoreMap{
	Vector<Double> value; 
	Vector<Double> enjoyment; 
	Vector<Double> workload; 
	Vector<Double> difficulty;  

	//default scoreMap weighs value and enjoyment normally
	//it does not include workload and difficulty in the rating
	//except in the case when workload or difficulty are rated as 5
	//In this case the workload/difficulty are weighted as 0.8
	public ScoreMap() {
		value = new Vector<Double>(); 
		enjoyment = new Vector<Double>(); 
		workload = new Vector<Double>(); 
		difficulty = new Vector<Double>(); 
		for(int i = 0; i < 5; i++) {
			value.addElement(1.);
			enjoyment.addElement(1.);
			if(i <= 3) 
				workload.addElement(0.);
			else 
				workload.addElement(0.8);  
	
			if(i <= 3) 
				difficulty.addElement(0.);
			else 
				difficulty.addElement(0.8); 
		}
	}

	//allows a user to define an arbitrary mapping of weights to score by supporting 
	//custom weights
	ScoreMap(Vector<Double> value, Vector<Double> enjoyment, Vector<Double> workload,
		Vector<Double> difficulty) {
		this.value = value;
		this.enjoyment = enjoyment;
		this.workload = workload;
		this.difficulty = difficulty;
	}

	//some predefined mappings
	//choice == 0 should be reserved for default, more choices can be added 
	//in the future
	ScoreMap(int choice) {
		value = new Vector<Double>(); 
		enjoyment = new Vector<Double>(); 
		workload = new Vector<Double>(); 
		difficulty = new Vector<Double>();

		//this configuration weighs value and enjoyment normally 
		//but does not weigh in workload and difficulty
		if(choice == 1) {
			for(int i = 0; i < 5; i++) {
				value.addElement(1.);
				enjoyment.addElement(1.);
				workload.addElement(0.);
				workload.addElement(0.);
			}
		} 

		//this configuration weighs all attributes normally
		else if(choice == 2) {
			for(int i = 0; i < 5; i++) {
				value.addElement(1.);
				enjoyment.addElement(1.);
				workload.addElement(1.);
				difficulty.addElement(1.);
			}
		}

		//default ScoreMap
		else {  
			for(int i = 0; i < 5; i++) {
				value.addElement(1.);
				enjoyment.addElement(1.);
				if(i <= 3) 
					workload.addElement(0.);
				else 
					workload.addElement(0.8);  

				if(i <= 3) 
					difficulty.addElement(0.);
				else 
					difficulty.addElement(0.8);  
			}
		}
	}

	//the overall score is computed by weighing the value and enjoyment by their weights
	//workload and difficulty are also weighed according to their weight; however, after weighing
	//they are subtracted from 5 so as to have the effect of weighing negatively on the overall score
	//All these weighted values are then averaged to get the overall score
	public double computeOverallScore(Score score) {
		if(score.getValue() == 0 || score.getEnjoyment() == 0 || score.getDifficulty() == 0 || score.getWorkload() == 0)
			return 0; 
		double sum = score.getValue()*value.get((int) score.getValue() - 1)
			+ score.getEnjoyment()*enjoyment.get((int) score.getEnjoyment() - 1)
			+ (5 - score.getWorkload()*workload.get((int )score.getWorkload() - 1)
			+ (5 - score.getDifficulty()*difficulty.get((int) score.getDifficulty() - 1))); 

		return sum/4; 
	}
}
