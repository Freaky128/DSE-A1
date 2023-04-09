package students.items;

public class Grain extends Food{
	private static int genNum = 0;
	
	public Grain() {
		super();
		genNum += 1;
		this.matureAge = 2;
		this.deathAge = 6;
		this.value = 2;
	}
	
	public Grain(Item Grain) {
		super(Grain);
	}
	
	public Item copy() {
		return new Grain(this);
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
