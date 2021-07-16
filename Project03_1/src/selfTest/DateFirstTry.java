package selfTest;

public class DateFirstTry {

	/*Write the Code*/
	public String month;
	public int day;
	
	public void makeItNewYears() {
		/*Write the Code*/
		month = "January";
		day = 1;
	}
	
	public String yellIfNewYear() {
		/*Write the Code*/
		if(month.equals("January") && day == 1)
			return "Happy New Year!";
		else
			return "Not New Year's Day.";
	}
	
}
