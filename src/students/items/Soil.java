package students.items;

public class Soil extends Item{
	public Soil() {
		this.matureAge = -1;
		this.deathAge = 1;
		this.value = 0;
	}
	
	public Soil(Item soil) {
		super(soil);
	}
	
	public Item copy() {
		return new Soil(this);
	}
	
	@Override
	public void tick() {}
	
	public String toString() {
		return ".";
	}
}
