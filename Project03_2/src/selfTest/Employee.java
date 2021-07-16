package selfTest;

public class Employee {

	private String name;
	private int age;
	private String position = "Engineer";
	private int salary = 15000;
	private int vacationDays = 20;
	
	public Employee() {}
	
	public Employee(String name, int age) {
		this.name = name;
		this.age = age;	
	}
	
	public Employee(String name, int age, String position, int salary) {
		this.name = name;
		this.age = age;
		this.position = position;
		this.salary = salary;
	}
	
	public Employee(String name, int age, String position, int salary, int vacationDays) {
		this.name = name;
		this.age = age;
		this.position = position;
		this.salary = salary;
		this.vacationDays = vacationDays;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public void outInfo() {
		System.out.println("name : " + this.name);
		System.out.println("age : " + this.age);
		System.out.println("position : " + this.position);
		System.out.println("salary : " + this.salary);
		System.out.println("vacationDays : " + this.vacationDays);
		System.out.println();
	}
	
	public String vacation(int vacationDays) {
		if( this.vacationDays >= vacationDays )
		{
			this.vacationDays -= vacationDays;
			return "Possible";
		}
		else
			return "Impossible";
	}
	
	public boolean equals(Employee anotherEmployee) {
		return (this.name.equals(anotherEmployee.name) && this.age == anotherEmployee.age && this.position.equals(anotherEmployee.position));
	}
	
}
