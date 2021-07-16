import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.io.*;

public class Worker {
	
	ArrayList<Member> MemberList = new ArrayList<Member> ();
	
	public Worker() 
	{
		
		Member m1 = new Director("Sam", "director", "Busan", 3650.50);
		Member m2 = new Director("Cliff", "full-time", "Seoul", 2453.15);
		Member m3 = new Employee("Kim", "intern", "America", 1548.15);
		Member m4 = new PartTime("Takuya", "temporary", "China", 12.50);
		Member m5 = new PartTime("Mike", "temporary", "Japan", 12.50);
		Member m6 = new Helper("Hong", "volunteer");
		
		MemberList.add(m1);
		MemberList.add(m2);
		MemberList.add(m3);
		MemberList.add(m4);
		MemberList.add(m5);
		MemberList.add(m6);
		
		for(Member member : MemberList)
		{
			if (member instanceof Director) ((Director) member).awardBonus(300);
			else if ( member instanceof PartTime) ((PartTime) member).addHours(35);
		}
		
	 } 
	
	public void fireSam()
	{
		Iterator iter = MemberList.iterator();
		while(iter.hasNext())
		{
			Member temp = (Member)iter.next();
			if( temp.getName().equals("Sam") )
			{
				iter.remove();
				System.out.println("Bye Sam\n");
				return;
			}
		}
	}
	
	 public void payoff() 
	 { 
		 Iterator iter = MemberList.iterator();
		 PrintWriter outStream = null;
		 
		 try
		 {
			 outStream = new PrintWriter(new FileOutputStream("MemberList.txt"));
		 }
		 catch (FileNotFoundException e)
		 {
			 System.out.println("Error opening the MemberList.txt.");
			 System.exit(0);
		 }
		 
		 while(iter.hasNext())
		 {		 
			 Member temp = (Member)iter.next();
			 System.out.println(temp);
			 outStream.println(temp);
			 
			 double payInfo = temp.pay();
			 
			 if ( payInfo == 0 ) 
			 {
				 System.out.println("Thank you!");
				 outStream.println("Thank you!");
			 }
			 else
			 {
				 System.out.println("Paid: " + payInfo);
				 outStream.println("Paid: " + payInfo);
			 }
			 
			 System.out.println("----------------------------------");
			 outStream.println("----------------------------------");
		 }
		 
		outStream.close();		
	 }
	 
	 public void memberSort() 
	 {
		 Collections.sort(MemberList);
		
		 System.out.println("Sorted Name:");
		 for(int i = 0; i < MemberList.size(); i++)
			 System.out.println(MemberList.get(i).name);
			 
	 }
}

