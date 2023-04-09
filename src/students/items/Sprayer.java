package students.items;

public class Sprayer extends Machinery{
	Item item;
	
	public Sprayer(String dir, int x, int y, Item item) {
		super(dir, x, y);
		this.item = item; // represents the item the machine is currently over the top of
	}

	public Sprayer(Sprayer machine) { // copy constructor
		super(machine);
		this.item = machine.item;
	}
	
	public Item copy() {
		return new Sprayer(this);
	}
	
	public String toString() {
		return "W";
	}
	
	@Override
	public Item[][] tick(Item[][] field) { // Overridden tick method used to kill weeds
		
		Item[][] fieldClone = field;
		
		this.age++;
		
		if (this.age > 1 && !this.ticked) { // makes sure functionality doesn't occur if machine has just been created or if it has already been ticked this turn
			if (this.item instanceof Weed) {
				fieldClone[this.yPos][this.xPos] = new UntilledSoil(); // replaces weeds with UntilledSoil
			}
			else {
				fieldClone[this.yPos][this.xPos] = this.item; // if item wasn't a weed it is placed back into the field
			}			
			
			int newXpos = this.newXPos(); // gets new coordinates
			int newYpos = this.newYPos();
			
			if (newXpos >= 0 && newXpos < fieldClone[this.yPos].length && newYpos >= 0 && newYpos < fieldClone.length) { // if new coordinates are in the field the next item is grabbed and the machine is moved forwards
				this.item = fieldClone[newYpos][newXpos];
				fieldClone[newYpos][newXpos] = this;
			}
			
			this.xPos = newXpos; // updates position
			this.yPos = newYpos;
			
			this.ticked = true;
		}
		
		return fieldClone; // returns the modified field
	}
}
