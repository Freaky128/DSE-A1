package students.items;

public class Soil extends Item{
	public Soil() {
		this.matureAge = -1;
		this.deathAge = 1;
		this.value = 0;
	}
	
	public Soil(Item soil) { // copy constructor
		super(soil);
	}
	
	public Item copy() { // because each item implements its own copy function there is no chance of casting exceptions
		return new Soil(this); // returns a copy of this object made with the copy constructor
	}
	
	@Override
	public void tick() {} // overriding tick to do nothing means soil will never die
	
	public String toString() {
		return ".";
	}
}
