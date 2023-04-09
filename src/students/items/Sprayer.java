package students.items;

public class Sprayer extends Machinery{
	public Sprayer(String dir, int x, int y) {
		super(dir, x, y);
	}

	public Sprayer(Machinery machine) {
		super(machine);
	}
	
	public Item copy() {
		return new Sprayer(this);
	}
	
	public String toString() {
		return "S";
	}
	
	@Override
	public Item[][] tick(Item[][] field) {
		Item[][] fieldClone = field;
		
		fieldClone[this.yPos][this.xPos] = new UntilledSoil();
		
		int newXpos = this.newXPos();
		int newYpos = this.newYPos();
		
		if (newXpos >= 0 || newXpos < fieldClone[this.yPos].length || newYpos >= 0 || newYpos < fieldClone.length) {
			fieldClone[newYpos][newXpos] = this;
		}
		
		return fieldClone;
	}
}
