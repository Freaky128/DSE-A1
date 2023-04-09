package students.items;

public class Apples extends Food{
	private static int genNum = 0;
	
	public Apples() {
		super();
		genNum += 1;
		this.matureAge = 3;
		this.deathAge = 5;
		this.value = 3;
	}
	
	public Apples(Item Apple) {
		super(Apple);
	}
	
	public Item copy() {
		return new Apples(this);
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
