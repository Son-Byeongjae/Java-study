
public class Cinnamon extends CondimentDecorator {
	Beverage beverage;
	
	public Cinnamon(Beverage beverage) {
		this.beverage = beverage;
	}
	
	public String getDescription() {
		return beverage.getDescription() + ", Cinnamon";
	}
	
	public double cost() {
		return 0.50 + beverage.cost();
	}
}
