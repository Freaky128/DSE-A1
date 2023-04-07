package students.items;

public class UntilledSoil extends Item{
	public UntilledSoil() {
		this.matureAge = -1;
		this.deathAge = 1;
		this.value = -1;
	}
	
	public UntilledSoil(Item untilledSoil) {
		super(untilledSoil);
	}
	
	public Item copy() {
		return new UntilledSoil(this);
	}
	
	@Override
	public void tick() {}
	
	public String toString() {
		return "/";
	}
}
