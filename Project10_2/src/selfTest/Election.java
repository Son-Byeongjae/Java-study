package selfTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Election {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator calculator = new Calculator();
		
		try {
			Scanner scanner = new Scanner(new FileInputStream("resource\\candidate.txt"));
			
			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				calculator.addName(line);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found or already be opened");
			System.exit(0);
		} catch (NoSuchElementException e) {
			System.err.println("Out of Boundary");
			System.exit(0);
		}
		
		try {
			File out = new File("resource\\election.txt");
			
			PrintWriter printWriter = new PrintWriter(new FileOutputStream(out));
			
			printWriter.println(calculator.toStringAllList());
			System.out.println(calculator.toStringAllList());
			
			printWriter.close();
			
		} catch (IOException e) {
			System.err.println("IOException");
			System.exit(0);
		}
	}
}
