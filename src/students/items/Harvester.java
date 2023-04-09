package students.items;

public class Harvester extends Machinery{
	Item item;
	int charge = 0; // Represents the amount of money the play makes of loses from harvest
	
	public Harvester(String dir, int x, int y, Item item) {
		super(dir, x, y);
		this.item = item;
	}

	public Harvester(Harvester machine) {
		super(machine);
		this.item = machine.item;
		this.charge = machine.charge;
	}
	
	public Item copy() {
		return new Harvester(this);
	}
	
	public String toString() {
		return "H";
	}
	
	@Override
	public Item[][] tick(Item[][] field) {
		
		Item[][] fieldClone = field;
		
		this.age++;
		
		if (age > 1 && !this.ticked) { // makes sure functionality doesn't occur if machine has just been created or if it has already been ticked this turn
			if (this.item instanceof Food) { // harvests crops or weeds and adds value to charge and places untilledsoil back into the field
				this.charge += this.item.getValue();
				fieldClone[this.yPos][this.xPos] = new UntilledSoil();
			}
			else if (this.item instanceof Weed) {
				this.charge += this.item.getValue();
				fieldClone[this.yPos][this.xPos] = new UntilledSoil();
			}
			else {
				fieldClone[this.yPos][this.xPos] = this.item;
			}
			
			int newXpos = this.newXPos();// gets new coordinates
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
	
	@Override
	public int getCharge() { // returns the amount the player should receive for harvest
		int temp = this.charge;
		this.charge = 0;
		return temp;
	}
}
