package selfTest;

public class TooMuchStuffException extends Exception {

	private int inputNumber;
	
	public TooMuchStuffException(int number) {
		super("Too much stuff!");
		inputNumber = number;
	}
	
	public TooMuchStuffException() {
		super("Too much stuff!");
	}
	
	public TooMuchStuffException(String message) {
		super(message);
	}
	
	public int getNumber() {
		return inputNumber;
	}
	
}
