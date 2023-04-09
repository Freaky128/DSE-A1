package students.items;

public class Weed extends Item{
	public Weed() {
		this.matureAge = -2;
		this.deathAge = 1;
		this.value = -1;
	}
	
	public Weed(Item weed) { // copy constructor
		super(weed);
	}
	
	public Item copy() { // because each item implements its own copy function there is no chance of casting exceptions
		return new Weed(this); // returns a copy of this object made with the copy constructor
	}
	
	@Override
	public void tick() {} // overriding tick to do nothing means weeds will never die
	
	public String toString() {
		return "#";
	}
}
