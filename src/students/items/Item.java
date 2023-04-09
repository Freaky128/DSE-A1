package students.items;

public abstract class Item {
	protected int age;
	protected int matureAge;
	protected int deathAge;
	protected int value;
	
	public Item() {
		this.age = 0;
	}
	
	public Item(Item item) {
		this.age = item.age;
		this.matureAge = item.matureAge;
		this.deathAge = item.deathAge;
		this.value = item.value;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public int getMatureAge() {
		return this.matureAge;
	}
	
	public int getDeathAge() {
		return this.deathAge;
	}
	
	public void tick() {
		this.age += 1;
	}
	
	public Item[][] tick(Item[][] field) {
		return field;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean died() {
		return this.age > this.deathAge;
	}
	
	public int getValue() {
		if (this.age >= this.matureAge) {
			return this.value;
		}
		else {
			return 0;
		}
	}
	
	//There is simpler ways to determine if items are equal but this way is comprehensive and rules out edge cases.
	public boolean equals(Item item ) {
		return (this.age == item.getAge() && this.matureAge == item.getMatureAge() && this.deathAge == item.getDeathAge() && this.getValue() == item.getValue());
	}
	
	public void clearTicked() {
		
	}
	
	public abstract Item copy();
	
	@Override
	public abstract String toString();
}
