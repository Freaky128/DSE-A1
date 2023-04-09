package students.items;

public class Apples extends Food{
	private static int genNum = 0; // Variable used to store the number of apples created
	
	public Apples() {
		super();
		genNum += 1;
		this.matureAge = 3;
		this.deathAge = 5;
		this.value = 3;
	}
	
	public Apples(Item Apple) { // copy constructor
		super(Apple);
	}
	
	public Item copy() { // because each item implements its own copy function there is no chance of casting exceptions
		return new Apples(this); // returns a copy of this object made with the copy constructor
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
