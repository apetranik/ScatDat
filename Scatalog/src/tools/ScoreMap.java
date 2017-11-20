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
			value.addElement(1. * (i + 1));
			enjoyment.addElement(1. * (i + 1));
			if(i <= 3) 
				workload.addElement(0. * (i + 1));
			else 
				workload.addElement(5 - (0.8 * (i + 1)));  
	
			if(i <= 3) 
				difficulty.addElement(0. * (5 - (i + 1)));
			else 
				difficulty.addElement(5 - (0.8 * (i + 1))); 
		}
	}

	//allows a user to define an arbitrary mapping of weights to score by supporting 
	//custom weights
	public ScoreMap(Vector<Double> value, Vector<Double> enjoyment, Vector<Double> workload,
		Vector<Double> difficulty) {
		this.value = value;
		this.enjoyment = enjoyment;
		this.workload = workload;
		this.difficulty = difficulty;
	}

	//some predefined mappings
	//choice == 0 should be reserved for default, more choices can be added 
	//in the future
	public ScoreMap(int choice) {
		value = new Vector<Double>(); 
		enjoyment = new Vector<Double>(); 
		workload = new Vector<Double>(); 
		difficulty = new Vector<Double>();

		//this configuration weighs value and enjoyment normally 
		//but does not weigh in workload and difficulty
		if(choice == 1) {
			for(int i = 0; i < 5; i++) {
				value.addElement(1. * (i + 1));
				enjoyment.addElement(1. * (i + 1));
				workload.addElement(0. * (i + 1));
				workload.addElement(0. * (i + 1));
			}
		} 

		//this configuration weighs all attributes normally
		else if(choice == 2) {
			for(int i = 0; i < 5; i++) {
				value.addElement(1. * (i + 1));
				enjoyment.addElement(1. * (i + 1));
				workload.addElement(1. * (i + 1));
				difficulty.addElement(1. * (i + 1));
			}
		}
		//Try-hard configuration. Weighs lower difficulties as lower, difficulties of 3 and 4
		//as the highest, and a difficulty of 5 as decently high
		else if(choice == 3) {
			for(int i = 0; i < 5; i++) {
				value.addElement(1. * (i + 1));
				enjoyment.addElement(1. * (i + 1));
				workload.addElement(1. * (i + 1));
				if(i == 0)
					difficulty.addElement(2.);
				else if(i == 1)
					difficulty.addElement(3.);
				else if(i == 2)
					difficulty.add(5.); 
				else if(i == 3)
					difficulty.add(5.);
				else 
					difficulty.add(4.); 
				
			}
		}

		//default ScoreMap
		else {  
			for(int i = 0; i < 5; i++) {
				value.addElement(1.);
				enjoyment.addElement(1.);
				if(i <= 3) 
					workload.addElement(0.* (i + 1));
				else 
					workload.addElement(5 - (0.8 * (i + 1)));  

				if(i <= 3) 
					difficulty.addElement(0. * (5 - (i + 1)));
				else 
					difficulty.addElement(5 - (0.8 * (i + 1)));  
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
		double sum = value.get((int) score.getValue() - 1)
			+ enjoyment.get((int) score.getEnjoyment() - 1)
			+ workload.get((int )score.getWorkload() - 1)
			+ difficulty.get((int) score.getDifficulty() - 1); 
		int total = 0; 
		if(value.get((int) score.getValue() - 1) != 0)
			total++;
		if(enjoyment.get((int) score.getEnjoyment() - 1) != 0)
			total++; 
		if(workload.get((int) score.getEnjoyment() - 1) != 0)
			total++; 
		if(difficulty.get((int) score.getDifficulty() - 1) != 0)
			total++; 
		
		return sum/total; 
	}
}
