package students.items;

abstract class Item {
	private int age;
	private int matureAge;
	private int deathAge;
	private int value;
	
	public Item() {
		this.age = 0;
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
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean died() {
		return this.age > this.deathAge;
	}
	
	public int getValue() {
		if (this.age > this.matureAge) {
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
	
	public abstract String toString();
}
