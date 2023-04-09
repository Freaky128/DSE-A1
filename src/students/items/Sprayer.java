package students.items;

public class Sprayer extends Machinery{
	Item item;
	
	public Sprayer(String dir, int x, int y, Item item) {
		super(dir, x, y);
		this.item = item;
	}

	public Sprayer(Machinery machine) {
		super(machine);
	}
	
	public Item copy() {
		return new Sprayer(this);
	}
	
	public String toString() {
		return "W";
	}
	
	@Override
	public boolean died() {
		return false;
	}
	
	@Override
	public Item[][] tick(Item[][] field) {
		
		Item[][] fieldClone = field;
		
		this.age++;
		
		if (age > 1 && !this.ticked) {
			if (this.item instanceof Weed) {
				fieldClone[this.yPos][this.xPos] = new UntilledSoil();
			}
			else {
				fieldClone[this.yPos][this.xPos] = this.item;
			}			
			
			int newXpos = this.newXPos();
			int newYpos = this.newYPos();
			
			if (newXpos >= 0 && newXpos < fieldClone[this.yPos].length && newYpos >= 0 && newYpos < fieldClone.length) {
				item = fieldClone[newYpos][newXpos];
				fieldClone[newYpos][newXpos] = this;
			}
			
			this.xPos = newXpos;
			this.yPos = newYpos;
			
			this.ticked = true;
		}
		
		return fieldClone;
	}
}
