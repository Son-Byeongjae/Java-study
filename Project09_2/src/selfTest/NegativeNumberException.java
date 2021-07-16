package selfTest;

public class NegativeNumberException extends Exception {
	public NegativeNumberException() {
		super("This number is negative!");
	}
	
	public NegativeNumberException(String message) {
		super(message);
	}
}
