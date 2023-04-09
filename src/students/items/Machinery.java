package students.items;

public abstract class Machinery extends Item{
	protected String direction;
	protected int xPos;
	protected int yPos;
	
	public Machinery(String dir, int x, int y) {
		super();
		this.direction = dir;
		this.matureAge = -3;
		this.deathAge = 1;
		this.xPos = x;
		this.yPos = y;
	}
	
	public Machinery(Machinery machine) {
		super(machine);
		this.direction = machine.direction;
		this.xPos = machine.xPos;
		this.yPos = machine.yPos;
	}
	
	public int newXPos() {
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
	
	public int newYPos() {
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
	
}
