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
								+ "  s: field summary\n"
								+ "  w: wait\n"
								+ "  q: quit\n");
			
			while (true) {
				try {
					command = input.next();
					
					if (command.length() > 1) {
						throw new InvalidInput("You must input t,h,p,s,w or q\n");
					}
					else if(Pattern.matches("[^thpswq]", command)) {
						throw new InvalidInput("You must input t,h,p,s,w or q\n");
					}
					else if(Pattern.matches("[^thp]", command)) {
						
						if (command.equals("s")) {
							this.summary();
						}
						else if(command.equals("q")) {
							this.running = false;
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
			
			this.field.tick();
		}
		
		System.out.println("Thanks for playing!");
	}
	
	public void till(int x, int y) { // considered making it cost money to till weed or untilledSoil
		this.field.till(x, y);		 // however it makes the game super hard
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
	
}
