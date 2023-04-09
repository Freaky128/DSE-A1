package students;
import students.items.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Farm {
	private Field field;
	private int balance;
	private boolean running = true;
	private int fieldX;
	private int fieldY;
	
	public Farm(int fieldWidth, int fieldHeight, int startingFunds)
	{
		this.field = new Field(fieldHeight, fieldWidth);
		this.balance = startingFunds;
		this.fieldX = fieldWidth;
		this.fieldY = fieldHeight;
	}
	
	public void run()
	{
		Scanner input = new Scanner(System.in);
		String command;
		int x;
		int y;
		System.out.println("Welcome to Sim Farm!\n");
		
		while (running) {
			System.out.println(this.field);
			System.out.println("Bank Balance: $" + this.balance + "\n");
			System.out.println("Enter your next action:");
			System.out.println("  t x y: till\n"
								+ "  h x y: harvest\n"
								+ "  p x y: plant\n"
								+ "  m: machinery\n"
								+ "  s: field summary\n"
								+ "  w: wait\n"
								+ "  q: quit\n");
			
			while (true) {
				try {
					command = input.next();
					
					if (command.length() > 1) {
						throw new InvalidInput("You must input t,h,p,m,s,w or q\n");
					}
					else if(Pattern.matches("[^thpmswq]", command)) {
						throw new InvalidInput("You must input t,h,p,m,s,w or q\n");
					}
					else if(Pattern.matches("[^thp]", command)) {
						
						if (command.equals("s")) {
							this.summary();
						}
						else if(command.equals("q")) {
							this.running = false;
						}
						else if(command.equals("m")) {
							this.machinery();
						}
						
						input.nextLine();
						break;
					}
					
					x = input.nextInt();
					y = input.nextInt();
					
					if (x > this.fieldX || x < 1 || y > this.fieldY || y < 1) {
						throw new InvalidInput("Entered coordinates are out of range\n");
					}
					else {
						
						if (command.equals("t")) {
							this.till(x - 1, y - 1);
						}
						else if(command.equals("h")) {
							this.harvest(x - 1, y - 1);
						}
						else {
							this.plant(x - 1, y - 1);
						}
						
						input.nextLine();
						break;
					}
					
				}
				catch(InvalidInput e) {
					System.out.println("Invalid input: " + e.message);
					input.nextLine();
				}
				catch(InputMismatchException e) {
					System.out.println("Invalid input: you must enter correct coordinates\n");
					input.nextLine();
				}
			}
			
			this.field.tick(this.balance);
			this.balance += this.field.getCharge();
		}
		
		System.out.println("Thanks for playing!");
	}
	
	public void till(int x, int y) throws InvalidInput{  // considered making it cost money to till weed or untilledSoil
		if (this.field.get(x, y) instanceof Machinery) { // however it makes the game super hard
			throw new InvalidInput("x:" + (x + 1) + " y:" + (y + 1) + " is not tillable\n");
		}
		else {
			this.field.till(x, y);		 
		}
		
		
	}
	
	public void harvest(int x, int y) throws InvalidInput{
		if (this.field.get(x, y) instanceof Food) {			
			int value = this.field.get(x, y).getValue();
			this.balance += value;
			
			System.out.println("Sold '" + this.field.get(x, y) + "' for " + value);
			
			this.field.till(x, y);
		}
		else {
			throw new InvalidInput("x:" + (x + 1) + " y:" + (y + 1) + " is not harvestable\n");
		}
	}
	
	public void plant(int x, int y) throws InvalidInput{
		Scanner input = new Scanner(System.in);
		String command;
		
		System.out.println("Enter:\r\n"
							+ " - 'a' to buy an apple for $2\n"
							+ " - 'g' to buy grain for $1");
		
		command = input.next();
		
		if (command.length() > 1) {
			throw new InvalidInput("You must input a or g\n");
		}
		else if(Pattern.matches("[^ag]", command)) {
			throw new InvalidInput("You must input a or g\n");
		}
		else {
			
			if (command.equals("a")) {
				if (2 > this.balance) {
					System.out.println("Insufficient funds\n");
				}
				else if (this.field.get(x, y).equals(new Soil())){
					this.field.plant(x, y, new Apples());
					balance -= 2;
				}
				else {
					throw new InvalidInput("Cannot plant: location isn't soil");
				}
			}
			else {
				if (1 > this.balance) {
					System.out.println("Insufficient funds\n");
				}
				else if (this.field.get(x, y).equals(new Soil())){
					this.field.plant(x, y, new Grain());
					balance -= 1;
				}
				else {
					throw new InvalidInput("Cannot plant: location isn't soil");
				}

			}
		}
		
		//input.close();
	}
	
	public void summary() {
		System.out.println(this.field.getSummary());
	}
	
	public void machinery() throws InvalidInput{
		Scanner input = new Scanner(System.in);
		String command;
		int x;
		int y;
		String dir;
		String seed;
		
		while(true) {
		
			System.out.println("Machinery\n (press i for help)\n\n"
					+ " - 'w' to buy a weed sprayer for $5\n"
					+ " - 'p' to buy a plow for $10\n"
					+ " - 's' to buy a seeder for $10\n"
					+ " - 'h' to buy a harvester for $10\n"
					+ " - 'c' to buy a plow seeder for $15\n"
					+ " - '+' to buy a plow seeder with fertiliser cart for $20\n");
			
			command = input.next();
			
			if (command.length() > 1) {
				throw new InvalidInput("You must input w,p,s,h,c,+ or i\n");
			}
			else if(Pattern.matches("[^wpshc+i]", command)) {
				throw new InvalidInput("You must input w,p,s,h,c,+ or i\n");
			}
			else if (Pattern.matches("[wpshc+]", command)){
				System.out.println("\nEnter start position x y and direction u,d,l,r");
				
				x = input.nextInt();
				y = input.nextInt();
				dir = input.next();
				
				if (x > this.fieldX || x < 1 || y > this.fieldY || y < 1) {
					throw new InvalidInput("Entered coordinates are out of range\n");
				}
				else if (dir.length() > 1) {
					throw new InvalidInput("You must input u,d,l or r\n");
				}
				else if(Pattern.matches("[^udlr]", dir)) {
					throw new InvalidInput("You must input u,d,l or r\n");
				}
				else {
					if (command.equals("w")) {
						this.field.plant(x - 1, y - 1, new Sprayer(dir, x - 1, y - 1, field.get(x - 1, y - 1)));
					}
					else if(command.equals("p")) {
						this.field.plant(x - 1, y - 1, new Plow(dir, x - 1, y - 1));
					}
					else if(command.equals("s")) {
						System.out.println("\nEnter seed type a or g\n");
						seed = input.next();
						
						if (seed.length() > 1) {
							throw new InvalidInput("You must input a or g\n");
						}
						else if(Pattern.matches("[^ag]", seed)) {
							throw new InvalidInput("You must input a or g\n");
						}
						else {
							this.field.plant(x - 1, y - 1, new Seeder(dir, x - 1, y - 1, field.get(x - 1, y - 1), seed));
						}
					}
					else if(command.equals("h")) {
						this.field.plant(x - 1, y - 1, new Harvester(dir, x - 1, y - 1, field.get(x - 1, y - 1)));
					}
				}
				
				break;
			}
			else {
				System.out.println("\nMachinery infromation\n"
						+ "Machines are items that can be bought to help your farming efforts\n"
						+ "When purchased machines are given a starting position (x, y) and\n"
						+ "a direction (u for up, d for down, r for right and l for left).\n"
						+ "Once bought, each turn a machine will conduct an action and move\n"
						+ "one space in the specified direction. A machine action doesn't\n"
						+ "count as your turn. A machine will do this untill it has moved off\n"
						+ "the field.\n"
						+ "'w' - weed sprayer: Kills weeds in its path leaving untilled soil behind.\n"
						+ "	 Doesn't kill crops\n"
						+ "'p' - Plow: Turns everything in its path into soil including crops.\n"
						+ "'s' - Seeder: Plants specified crop in soil it passes over.\n"
						+ "	 Charges for each seed and will be destroyed if you can't afford\n"
						+ "	 more seeds\n"
						+ "'h' - Harvester: Will harvest everything in its path including weeds\n"
						+ "	 Harvested weeds incur a cost of $1. Leaves untilled soil behind\n"
						+ "'c' - Plow seeder: Plants specified crop in everything it passes over\n"
						+ "'+' - Plow seeder with fertiliser cart: Same as plow seeder except crops\n"
						+ "	 it plants will mature quicker.\n");
			}
		}
		
	}
	
}
