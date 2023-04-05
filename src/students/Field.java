package students;
import students.items.*;

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
