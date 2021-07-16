
public class Director extends Employee { 
	
	private double bonus; 
 
	public Director(String name, String position, String residence, double rate) 
	{ 
		super(name,position,residence,rate);
		this.bonus = 0.0;
	}

	public void awardBonus(double execBonus) 
	{ 
		this.bonus = execBonus; 
	} 
	
	public double pay() 
	{ 
		double ret = super.pay() + this.bonus;
		this.bonus = 0.0;
		return ret;
	} 
} 