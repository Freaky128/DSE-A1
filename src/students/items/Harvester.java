package students.items;

public class Harvester extends Machinery{
	Item item;
	int charge = 0;
	
	public Harvester(String dir, int x, int y, Item item) {
		super(dir, x, y);
		this.item = item;
	}

	public Harvester(Machinery machine) {
		super(machine);
	}
	
	public Item copy() {
		return new Harvester(this);
	}
	
	public String toString() {
		return "H";
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
			if (this.item instanceof Food) {
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
