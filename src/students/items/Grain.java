package students.items;

public class Grain extends Food{
	private static int genNum = 0; // Variable used to store the number of grain created
	
	public Grain() {
		super();
		genNum += 1;
		this.matureAge = 2;
		this.deathAge = 6;
		this.value = 2;
	}
	
	public Grain(Item Grain) { // copy constructor
		super(Grain);
	}
	
	public Item copy() { // because each item implements its own copy function there is no chance of casting exceptions
		return new Grain(this); // returns a copy of this object made with the copy constructor
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
