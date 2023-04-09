package students.items;

public class PlowSeeder extends Machinery{
	Item item;
	String seed;
	int charge = 0;
	
	public PlowSeeder(String dir, int x, int y, Item item, String seed) {
		super(dir, x, y);
		this.item = item;
		this.seed = seed;
	}

	public PlowSeeder(Machinery machine) {
		super(machine);
	}
	
	public Item copy() {
		return new PlowSeeder(this);
	}
	
	public String toString() {
		return "C";
	}
	
	@Override
	public boolean died() {
		return false;
	}
	
	@Override
	public Item[][] tick(Item[][] field, int balance) {		
		Item[][] fieldClone = field;
		
		this.age++;
		
		if (age > 1 && !this.ticked) {
			if (this.seed.equals("a") && balance >= 2) {					
				fieldClone[this.yPos][this.xPos] = new Apples();
				this.charge -= 2;
			}
			else if(balance >= 1) {
				fieldClone[this.yPos][this.xPos] = new Grain();
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
