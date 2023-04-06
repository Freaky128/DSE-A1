package students;
import students.items.*;
import java.util.Random;

public class Field {
	private Item[][] field; 
	
	public Field(int height, int width){
		this.field = new Item[height][width];
		
		for(int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				field[i][j] = new Soil();
			}
		}
	}
	
	public void tick() {
		Random rand = new Random();
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				field[i][j].tick();
				
				if (field[i][j].died()) {
					field[i][j] = new UntilledSoil();
				}
				
				if (field[i][j].equals(new Soil())) {
					if (rand.nextInt(100) < 20) {
						field[i][j] = new Weed();
					}
				}
			}
		}
	}
	
	@Override
	public String toString() {
		String fieldStr = new String();
		
		fieldStr += "  ";
		
		for (int i = 1; i <= field[0].length; i++) {
			fieldStr += String.format("%-2d", i);
		}
		
		fieldStr += "\n";
		
		for(int i = 0; i < field.length; i++) {
			fieldStr += String.format("%-2d", i + 1);
			for (int j = 0; j < field[i].length; j++) {
				fieldStr += String.format("%-2s", field[i][j]);
			}
			fieldStr += "\n";
		}
		
		return fieldStr;
	}
}
