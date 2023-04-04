package students.items;

public class Apples extends Food{
	private static int genNum = 0;
	//not sure this should be stored here
	private static int cost = 2;
	
	public Apples() {
		super();
		genNum += 1;
		this.matureAge = 3;
		this.deathAge = 5;
		this.value = 3;
	}
	
	public String toString() {
		if (this.age <  this.matureAge) {
			return "a";
		}
		else {
			return "A";
		}
	}
	
	public static int getGenerationCount() {
		return genNum;
	}
	
}
