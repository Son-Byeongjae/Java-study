
public class DigitObserver implements Observer {
	private NumberGenerator num;
	
	public DigitObserver(NumberGenerator num) {
		this.num = num;
		num.addObserver(this);
	}
	
	
	@Override
	public void update(NumberGenerator generator) {
		// TODO Auto-generated method stub
		System.out.println("DigitObserver: " + generator.getNumber());
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
