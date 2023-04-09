package students.items;

public abstract class Machinery extends Item{ // base class of all machines
	protected String direction; // direction the machines will travel across the field
	protected int xPos;
	protected int yPos;
	protected boolean ticked = false; // indicates if this machine has already been ticked this turn
	
	public Machinery(String dir, int x, int y) { // default constructor
		super();
		this.value = 0;
		this.direction = dir;
		this.matureAge = -3;
		this.deathAge = 1;
		this.xPos = x;
		this.yPos = y;
	}
	
	public Machinery(Machinery machine) { // copy constructor
		super(machine);
		this.direction = machine.direction;
		this.xPos = machine.xPos;
		this.yPos = machine.yPos;
		this.ticked = machine.ticked;
	}
	
	@Override
	public boolean died() { // stops machines from dying
		return false;
	}
	
	public int newXPos() { // calculates the machines next x position
		if (this.direction.equals("r")) {
			return this.xPos + 1;
		}
		else if (this.direction.equals("l")) {
			return this.xPos - 1;
		}
		else {
			return this.xPos;
		}
	}
	
	public int newYPos() { // calculates the machines next y position
		if (this.direction.equals("d")) {
			return this.yPos + 1;
		}
		else if (this.direction.equals("u")) {
			return this.yPos - 1;
		}
		else {
			return this.yPos;
		}
	}
	
	@Override
	public void clearTicked() { // used to clear ticked flag when it is a new turn
		this.ticked = false;
	}
	
}
