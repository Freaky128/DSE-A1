package students;

public class InvalidInput extends Exception{
	public String message;
	
	public InvalidInput (String str) {
		super(str);
		message = str;
	}

}
