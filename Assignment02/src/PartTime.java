
public class PartTime extends Employee { 
 
	private int hoursWorked; 
	
	public PartTime (String name, String position, String residence, double rate) 
	{ 
		super(name,position,residence,rate);
		this.hoursWorked = 0;
	}
	
	public int getHoursWorked() 
	{
		return this.hoursWorked;
	}

	public void setHoursWorked(int hoursWorked) 
	{
		this.hoursWorked = hoursWorked;
	}

	public void addHours (int moreHours) 
	{ 
		this.hoursWorked = this.hoursWorked + moreHours;
	}
	
	public double pay() 
	{ 
		double ret = this.payRate * this.hoursWorked;
		this.hoursWorked = 0;
		return ret;
	} 
	
	public String toString() 
	{ 
		return (super.toString() + "\nCurrent hours: " + this.hoursWorked);
	} 
} 