package students.items;

public class Plow extends Machinery{
	public Plow(String dir, int x, int y) {
		super(dir, x, y);
	}

	public Plow(Plow machine) { // copy constructor
		super(machine);
	}
	
	public Item copy() {
		return new Plow(this);
	}
	
	public String toString() {
		return "P";
	}
	
	@Override
	public Item[][] tick(Item[][] field) { // Overridden tick method used to turn items to soil
		
		Item[][] fieldClone = field;
		
		this.age++;
		
		if (this.age > 1 && !this.ticked) { // makes sure functionality doesn't occur if machine has just been created or if it has already been ticked this turn
			fieldClone[this.yPos][this.xPos] = new Soil(); // turns current position to soil
			
			int newXpos = this.newXPos(); // gets new coordinates
			int newYpos = this.newYPos();
			
			if (newXpos >= 0 && newXpos < fieldClone[this.yPos].length && newYpos >= 0 && newYpos < fieldClone.length) { // if new coordinates are in the field the machine is moved forwards
				fieldClone[newYpos][newXpos] = this;
			}
			
			this.xPos = newXpos; // updates position
			this.yPos = newYpos;
			
			this.ticked = true;
		}
		
		return fieldClone; // returns the modified field
	}
}
