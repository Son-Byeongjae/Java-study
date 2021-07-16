
abstract public class Member implements Comparable{
	
	protected String name; 
	protected String position; 
	
	public Member(String name, String position) 
	{
		this.name = name;
		this.position = position;
	} 
	
	public String getName() 
	{
		return this.name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getPosition() 
	{
		return this.position;
	}

	public void setPosition(String position) 
	{
		this.position = position;
	}

	public String toString() 
	{
		return ("Name: " + this.name + "\nPosition: " + this.position);
	}
	
	public abstract double pay(); 
	
	public int compareTo(Object compareMember) 
	{		
		Member toCompare = (Member)compareMember;
		return this.name.compareTo(toCompare.name);
		
	}
}
