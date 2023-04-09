package students;

public class InvalidInput extends Exception{ // custom exception used when user input doesn't fit expected
	public String message;
	
	public InvalidInput (String str) {
		super(str);
		message = str;
	}

}
