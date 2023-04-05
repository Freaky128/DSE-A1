package students.items;

public class UntilledSoil extends Item{
	public UntilledSoil() {
		this.matureAge = -1;
		this.deathAge = 1;
		this.value = -1;
	}
	
	@Override
	public void tick() {}
	
	public String toString() {
		return "/";
	}
}
