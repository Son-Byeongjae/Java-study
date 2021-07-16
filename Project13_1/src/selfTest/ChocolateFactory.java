package selfTest;

public class ChocolateFactory {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//ChocolateBoiler a = new ChocolateBoiler();
		//ChocolateBoiler b = new ChocolateBoiler();
		// Change the above code
		ChocolateBoiler a = ChocolateBoiler.getInstance();
		ChocolateBoiler b = ChocolateBoiler.getInstance();
		
		a.fill();
		b.fill();
		a.boil();
		b.boil();
		a.drain();
		b.drain();
		
	}
}
