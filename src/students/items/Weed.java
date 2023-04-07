package students.items;

public class Weed extends Item{
	public Weed() {
		this.matureAge = -1;
		this.deathAge = 1;
		this.value = -1;
	}
	
	public Weed(Item weed) {
		super(weed);
	}
	
	public Item copy() {
		return new Weed(this);
	}
	
	@Override
	public void tick() {}
	
	public String toString() {
		return "#";
	}
}
