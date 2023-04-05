package students.items;

public class Weed extends Item{
	public Weed() {
		this.matureAge = -1;
		this.deathAge = 1;
		this.value = -1;
	}
	
	@Override
	public void tick() {}
	
	public String toString() {
		return "#";
	}
}
