package Part1;

public class Student { 
	
	private String studentName; // 학생 이름
	private int studentId; // 학번
	
	public Student(String studentName, int studentId) // 모든 변수를 인자로 받은 값으로 초기화하는 생성자
	{
		this.studentName = studentName;
		this.studentId = studentId;
	}
	
	public String getStudentName() 
	{
		return this.studentName;
	}

	public void setStudentName(String studentName) 
	{
		this.studentName = studentName;
	}

	public int getStudentId() 
	{
		return this.studentId;
	}

	public void setStudentId(int studentId) 
	{
		this.studentId = studentId;
	}
	
	public Answer makeAnswer(Assignment assignment, String solution) // 인자로 받은 Assignment 객체와 solution의 값 중 solution의 값만 사용하여 생성한 Answer 객체를 리턴하는 메소드
	{
		return new Answer(solution);
	}
	
	public String toString() //  studentName, studentId의 값을 출력하는 toString() 메소드를 오버라이딩
	{
		return ("Student Name: " + studentName + ", Student Id: " + studentId);
	}

}
