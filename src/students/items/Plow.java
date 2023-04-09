package students.items;

public class Plow extends Machinery{
	public Plow(String dir, int x, int y) {
		super(dir, x, y);
	}

	public Plow(Machinery machine) {
		super(machine);
	}
	
	public Item copy() {
		return new Plow(this);
	}
	
	public String toString() {
		return "P";
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
			fieldClone[this.yPos][this.xPos] = new Soil();
			
			int newXpos = this.newXPos();
			int newYpos = this.newYPos();
			
			if (newXpos >= 0 && newXpos < fieldClone[this.yPos].length && newYpos >= 0 && newYpos < fieldClone.length) {
				fieldClone[newYpos][newXpos] = this;
			}
			
			this.xPos = newXpos;
			this.yPos = newYpos;
			
			this.ticked = true;
		}
		
		return fieldClone;
	}
}
