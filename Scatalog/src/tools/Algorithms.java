package tools;

import java.util.ArrayList;
import java.util.Vector;

import scatalogObjects.Course;
import scatalogObjects.Score;
import scatalogObjects.User;

public class Algorithms{

	//returns the 3 most popular GE's or Free Electives in O(n^2) runtime in the worst case
	//However, the worst case is extremely unlikely. A better runtime estimate for this 
	//algorithm is an O(n) amortized runtime in the worst case. This discrepancy in runtime
	//estimates is due to the use of hashSets to find whether a user has already taken a course.
	public static Vector<Course> getRecommendedCourses(User user, ArrayList<Course> courses) {

		//recommendations will contain the 3 recommended courses and will be returned
		Vector<Course> recommendations = new Vector<Course>(3); 

		//create a dummy user with 0 users taking it to initialize recommendations
		Course dummy = new Course(); 
		dummy.setNumUsers(0); 

		//initialize the recommendations vector
		for(int i = 0; i < 3; i++){ 
			recommendations.addElement(dummy); 
		}
		
		for(Course course : courses) { 
			//A course's type is 1 if it is a GE and 2 if it is a free elective
			//We only recommend free electives or GE's to a user and only if the
			//the user has not already taken the course 
			if((course.getType() == 1 || course.getType() == 2) 
				&& !user.getCoursesTaken().contains(course)) {

				int numUsers = course.getNumUsers(); 

				//recommendations contain the courses that are most popular
				//so we choose the three with the most number of users
				if(numUsers > recommendations.get(0).getNumUsers())
					recommendations.set(0, course); 
				else if(numUsers > recommendations.get(1).getNumUsers())
					recommendations.set(1, course); 
				else if(numUsers > recommendations.get(2).getNumUsers())
					recommendations.set(2, course); 
			}		
		}

		return recommendations; 
	}

	//The algorithm takes in a score object and returns the overall score.
	//For proper functionality, when requesting the overall score, the Score object 
	//must have all but its overallScore member variable initialized to a valid instance.
	//The runtime of this algorithm is O(1).
	//This function uses the default ScoreMap to rate as defined in the ScoreMap class.
	public static double getScore(Score score) {
		return getScore(score, new ScoreMap());  
	}

	//This is the same as the above function but allows a custom ScoreMap for rating
	public static double getScore(Score score, ScoreMap scoreMap) {
		return scoreMap.computeOverallScore(score); 
	}
}