package students.items;

public class Seeder extends Machinery{
	Item item;
	String seed;
	int charge = 0; // Represents the amount of money charged for the seeds
	
	public Seeder(String dir, int x, int y, Item item, String seed) {
		super(dir, x, y);
		this.item = item; // represents the item the machine is currently over the top of
		this.seed = seed; // represent the type of seed it will plant
	}

	public Seeder(Seeder machine) { // copy constructor
		super(machine);
		this.item = machine.item;
		this.seed = machine.seed;
		this.charge = machine.charge;
	}
	
	public Item copy() {
		return new Seeder(this);
	}
	
	public String toString() {
		return "S";
	}
	
	@Override
	public Item[][] tick(Item[][] field, int balance) {		
		Item[][] fieldClone = field;
		
		this.age++;
		
		if (age > 1 && !this.ticked) { // makes sure functionality doesn't occur if machine has just been created or if it has already been ticked this turn
			if (this.item instanceof Soil) {				
				if (this.seed.equals("a") && balance >= 2) { // plants the seed or not based on if the player can afford it and charges the player.					
					fieldClone[this.yPos][this.xPos] = new Apples();
					this.charge -= 2;
				}
				else if(balance >= 1) {
					fieldClone[this.yPos][this.xPos] = new Grain();
					this.charge -= 1;
				}
				else {
					fieldClone[this.yPos][this.xPos] = this.item; // if a seed can't be afforded original item is replaced
				}
				
			}
			else {
				fieldClone[this.yPos][this.xPos] = this.item; // if item isn't soil it is placed back into the field
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
	
	@Override
	public int getCharge() { // returns the amount the player should be charged for seeds
		int temp = this.charge;
		this.charge = 0;
		return temp;
	}
}
