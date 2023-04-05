package students.items;

public class Soil extends Item{
	public Soil() {
		this.matureAge = -1;
		this.deathAge = 1;
		this.value = 0;
	}
	
	@Override
	public void tick() {}
	
	public String toString() {
		return ".";
	}
}
