package selfTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class HasNextLineDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner inputStream = null;
		PrintWriter outputStream = null;
		
		try {
			inputStream = new Scanner(new FileInputStream("resource\\BladeRunner.txt"));
			outputStream = new PrintWriter(new FileOutputStream("resource\\NumberedRunner.txt"));	
		} catch (FileNotFoundException e) {
			System.out.println("Problem opening files.");
			System.exit(0);
		}
		
		String line = null;
		int count = 0;
		while(inputStream.hasNextLine())
		{
			line = inputStream.nextLine();
			count++;
			outputStream.println(count + " " + line);
		}
		inputStream.close();
		outputStream.close();
	}

}
