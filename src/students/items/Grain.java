package students.items;

public class Grain extends Food{
	private int cost = 1;
	
	public Grain() {
		super();
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
	
}
