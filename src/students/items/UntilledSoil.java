package students.items;

public class UntilledSoil extends Item{
	public UntilledSoil() {
		this.matureAge = -1;
		this.deathAge = 1;
		this.value = -1;
	}
	
	public UntilledSoil(Item untilledSoil) { // copy constructor
		super(untilledSoil);
	}
	
	public Item copy() { // because each item implements its own copy function there is no chance of casting exceptions
		return new UntilledSoil(this); // returns a copy of this object made with the copy constructor
	}
	
	@Override
	public void tick() {} // overriding tick to do nothing means untilled soil will never die
	
	public String toString() {
		return "/";
	}
}
