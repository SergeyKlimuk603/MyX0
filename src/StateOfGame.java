
public class StateOfGame {
	
	String state;// —остоит из дев€ти €чеек
	int weight = 5;
	
	public void setWeight(int delta) {
		weight += weight;
		if(weight < 1) weight = 1;
		if(weight > 9) weight = 9;
	}
	
	public int getWeight() {
		return weight;
	}
	
}
