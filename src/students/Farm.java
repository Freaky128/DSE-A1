package students;
import java.util.Scanner;

public class Farm {
	private Field field;
	private int balance;
	private boolean running = true;
	
	public Farm(int fieldWidth, int fieldHeight, int startingFunds)
	{
		this.field = new Field(fieldHeight, fieldWidth);
		this.balance = startingFunds;
	}
	
	public void run()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Sim Farm!\n");
		
		while (running) {
			System.out.println(this.field + "\n");
			System.out.println("Bank Balance: $" + this.balance + "\n");
			System.out.println("Enter your next action:");
			System.out.println("  t x y: till\n"
								+ "  h x y: harvest\n"
								+ "  p x y: plant\n"
								+ "  s: field summary\n"
								+ "  w: wait\n"
								+ "  q: quit\n");
			//input.
			
			running = false;
		}
	}
	
}
