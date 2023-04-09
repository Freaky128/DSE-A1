package students.items;

public class PlowSeederF extends Machinery{ // virtually the same as PlowSeeder except it makes seeds mature quicker.
	Item item;								// refer to Seeder or PlowSeeder for better comments
	String seed;
	int charge = 0;
	
	public PlowSeederF(String dir, int x, int y, Item item, String seed) {
		super(dir, x, y);
		this.item = item;
		this.seed = seed;
	}

	public PlowSeederF(PlowSeederF machine) {
		super(machine);
		this.item = machine.item;
		this.seed = machine.seed;
		this.charge = machine.charge;
	}
	
	public Item copy() {
		return new PlowSeederF(this);
	}
	
	public String toString() {
		return "+";
	}
	
	@Override
	public Item[][] tick(Item[][] field, int balance) {		
		Item[][] fieldClone = field;
		
		this.age++;
		
		if (age > 1 && !this.ticked) {
			if (this.seed.equals("a") && balance >= 2) {					
				fieldClone[this.yPos][this.xPos] = new Apples();
				fieldClone[this.yPos][this.xPos].setAge(1); // "Fertilizes" seed by advancing it's age by 1
				this.charge -= 2;
			}
			else if(balance >= 1) {
				fieldClone[this.yPos][this.xPos] = new Grain();
				fieldClone[this.yPos][this.xPos].setAge(1); // "Fertilizes" seed by advancing it's age by 1
				this.charge -= 1;
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
	
	@Override
	public int getCharge() {
		int temp = this.charge;
		this.charge = 0;
		return temp;
	}
}
