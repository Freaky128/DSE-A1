package students;
import students.items.*;
import java.util.Random;

public class Field {
	private Item[][] field; // 2D item array used to represent the field
	
	public Field(int height, int width){
		this.field = new Item[height][width];
		
		for(int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				this.field[i][j] = new Soil();
			}
		}
	}
	
	public void tick(int balance) {
		Random rand = new Random();
		for (int i = 0; i < this.field.length; i++) {
			for (int j = 0; j < this.field[i].length; j++) {
				
				if (this.field[i][j].equals(new Soil())) { // this is correct in terms of the assignment spec wording // spawns weeds
					if (rand.nextInt(100) < 20) {	  // however it makes the game quite hard as the field is overrun
						this.field[i][j] = new Weed();	  // with weeds after 5-7 turns
					}
				}
				
				if (this.field[i][j] instanceof Seeder || this.field[i][j] instanceof PlowSeeder || this.field[i][j] instanceof PlowSeederF) { // call various overloads of items tick methods
					this.field = this.field[i][j].tick(this.field, balance);
				}
				else if (this.field[i][j] instanceof Machinery) {
					this.field = this.field[i][j].tick(this.field);
				}
				else {
					this.field[i][j].tick();
				}
				
				if (this.field[i][j].died()) {
					this.field[i][j] = new UntilledSoil();
				}
			
			}
		}
		
		for (int i = 0; i < field.length; i++) { // as the turn has "ended" the ticked flag needs to be reset (used for machinery) 
			for (int j = 0; j < field[i].length; j++) {
				
				this.field[i][j].clearTicked();
			}
		}
	}
	
	public void till(int x, int y) {
		field[y][x] = new Soil();
	}
	
	public Item get(int x, int y) {
		Item clone = field[y][x].copy();
		return clone;
	}
	
	public void plant(int x, int y, Item item) {
		field[y][x] = item;
	}
	
	public int getValue() {
		int total = 0;
		
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				
				total += field[i][j].getValue();			
			}
		}
		
		return total;
	}
	
	public String getSummary() {
		String sumStr = new String();
		int apples = 0;
		int grain = 0;
		int soil = 0;
		int untilledSoil = 0;
		int weed = 0;
		
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				
				if (field[i][j].equals(new Soil())) {
					soil++;
				}
				else if (field[i][j].equals(new Weed())) {
					weed++;
				}
				else if (field[i][j].equals(new UntilledSoil())) {
					untilledSoil++;
				}
				else if (field[i][j] instanceof Apples) {
					apples++;
				}
				else if (field[i][j] instanceof Grain) {
					grain++;
				}
			}
		}
		
		sumStr += "\n";
		sumStr += String.format("%-15s", "Apples:"); // uses formatting to line up summary elements 
		sumStr += apples + "\n";
		sumStr += String.format("%-15s", "Grain:");
		sumStr += grain + "\n";
		sumStr += String.format("%-15s", "Soil:");
		sumStr += soil + "\n";
		sumStr += String.format("%-15s", "Untilled:");
		sumStr += untilledSoil + "\n";
		sumStr += String.format("%-15s", "Weeds:");
		sumStr += weed + "\n";
		sumStr += String.format("%-15s", "For a total of");
		sumStr += "$" + this.getValue() + "\n";
		sumStr += "Total apples created: " + Apples.getGenerationCount() + "\n";
		sumStr += "Total grain created: " + Grain.getGenerationCount() + "\n";
		
		return sumStr;
	}
	
	public int getCharge() { // used to get the amount the players balance should change by from machine actions
		int sum = 0;
		
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				
				if (field[i][j] instanceof Machinery) {
					sum += field[i][j].getCharge();
				}
			}
		}
		
		return sum;
	}

	@Override
	public String toString() {
		String fieldStr = new String();
		
		fieldStr += "\n  ";
		
		for (int i = 1; i <= field[0].length; i++) { // adds the numbers on top of the field
			fieldStr += String.format("%-2d", i);
		}
		
		fieldStr += "\n";
		
		for(int i = 0; i < field.length; i++) {
			
			fieldStr += String.format("%-2d", i + 1); // adds the numbers on the left side of the field
			
			for (int j = 0; j < field[i].length; j++) {
				fieldStr += String.format("%-2s", field[i][j]); // adds the items to the field string
			}
			
			fieldStr += "\n";
		}
		
		return fieldStr;
	}
}
