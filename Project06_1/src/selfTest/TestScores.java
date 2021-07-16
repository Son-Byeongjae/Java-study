package selfTest;
import java.util.Scanner;

public class TestScores {
	public static final int MAX_NUMBER_SCORES = 10;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double[] scores = new double[MAX_NUMBER_SCORES];
		int counting = 0;
		
		System.out.println("This program reads test scores and shows");
		System.out.println("how much each differs from the average.");
		System.out.println("Enter test scores:");
		
		counting = fillArray(scores);
		showDifference(scores, counting);
		
	}

	public static int fillArray(double[] a)
	{
		System.out.println("Mark the end of the list with a negative number.");
		Scanner keyboard = new Scanner(System.in);
		
		double next;
		int index = 0;
		
		next = keyboard.nextDouble();
		
		while( (next >= 0) && (index < a.length))
		{
			a[index] = next;
			index++;
			next = keyboard.nextDouble();
		}
		return index;
	}
	
	public static void showDifference(double[] a, int numberUsed)
	{
		double average = computeAverage(a,numberUsed);
		
		System.out.println("Average of the scores = " + average);
		System.out.println("The scores are: ");
		
		for(int index = 0; index < numberUsed; index++)
			System.out.println(a[index] + " differs from average by " + (a[index]-average));
	}
	
	public static double computeAverage(double[] a, int numberUsed)
	{	
		double sum = 0;
		for(int index = 0; index < numberUsed; index++)
			sum += a[index];
		if( numberUsed > 0 )
			return (sum / numberUsed);
		else
			return 0;
	}
}
