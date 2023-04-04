package students.items;

public class Grain extends Food{
	private static int genNum = 0;
	//not sure this should be stored here
	private static int cost = 1;
	
	public Grain() {
		super();
		genNum += 1;
		this.matureAge = 2;
		this.deathAge = 6;
		this.value = 2;
	}
	
	public String toString() {
		if (this.age <  this.matureAge) {
			return "g";
		}
		else {
			return "G";
		}
	}
	
	public static int getGenerationCount() {
		return genNum;
	}
	
}
