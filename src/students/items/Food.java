package students.items;

public abstract class Food extends Item{
	public Food() { // default constructor
		super();
	}
	
	public Food(Item item) { // constructor needed for copy constructor
		super(item);
	}
}
