package selfTest;

public class Contact implements Comparable{
	private String name;
	private String telNum;
	private String email;
	
	public Contact(String name, String telNum, String email) {
		this.name = name;
		this.telNum = telNum;
		this.email = email;
	}
	
	public Contact(String name) {
		this.name = name;
		this.telNum = null;
		this.email = null;
	}
	
	public String getTelNum() {	return telNum; }
	public void setTelNum(String telNum) { this.telNum = telNum; }
	public String getEmail() { return email; }
	public void setEmail(String email) {this.email = email; }
	public String getName() { return name; }
	
	public String toString() {
		return "Name: " + name + "\ttelNum: " + telNum + "\temail: " + email + "\n";
	}
	
	public boolean equals(Object obj) {
		if( obj == null) return false;
		else if( this.getClass() != obj.getClass()) return false;
		else
		{
			Contact otherManager = (Contact) obj;
			if( this.getName().equals(otherManager.getName()) )
				return true;
			else
				return false;
		}
	}
	
	public int compareTo(Object obj) {
		if(obj==null) throw new NullPointerException("Object is null");
		else if(this.getClass()!=obj.getClass()) throw new ClassCastException("Object not of the same type");
		else {
			Contact otherManager = (Contact) obj;
			int compare = this.getName().compareTo(otherManager.getName());
			return compare;
		}
	}
}
