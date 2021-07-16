package selftest;

public class Project01_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 
		 * Declare 'name' variable.
		 * 		Initialize variable 'name' as type string which contains your name with small letter.
		 * Declare 'greeting1' and 'greeting2' variables.
		 * 		Initialize variable 'greeting1' as type string and value 'Hello'.
		 * 		Initialize variable 'greeting2' as type string and value 'nice to meet you'.
		 * Declare 'uName' variable.
		 * 		Initialize variable 'uName' as type string which contains your name with upper case.
		 * 		Don't type it manually. Use 'name' variable and String class method.
		 * 
		 */
		
		String name = "byeongjae";
		String greeting1 = "Hello";
		String greeting2 = "nice to meet you";
		String uName = name.toUpperCase();
				
		System.out.println(greeting1 + " " + name + " " + greeting2/* Print 'greeting1', 'name' and 'greeting2'. Add space each variables. */); 
		System.out.println(name.length()/* Print length of 'name' variable by using String class method. */);
		System.out.println(name.equals(uName)/* Print equality of 'name' and 'uName' variables. (Case sensitive) */);
		System.out.println(name.equalsIgnoreCase(uName)/* Print equality of 'name' and 'uName' variables. (Case insensitive) */);
	}

}
