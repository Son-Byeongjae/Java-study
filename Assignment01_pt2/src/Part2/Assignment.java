package Part2;

public class Assignment { 
	
	private String subject; // 과목명
	private String question; // 과제 내용
	
	public Assignment() // 모든 변수를 ‘None’으로 초기화하는 기본 생성자
	{
		this.subject = "None";
		this.question = "None";
	}
	
	public Assignment(String subject, String question) // 모든 변수를 인자로 받은 값으로 초기화하는 생성자
	{ 
		this.subject = subject;
		this.question = question;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	public String getSubject() 
	{
		return this.subject;
	}
	
	public void setQuestion(String question) 
	{
		this.question = question;
	}
	
	public String getQuestion() 
	{
		return this.question;
	}
	
	public boolean equals(Assignment assignment) // 인자로 받은 객체의 변수값과 같은지 확인하는 메소드
	{
		return (this.subject.equals(assignment.subject) && this.question.equals(assignment.question));
	}
	
	public String toString() // subject, question의 값을 출력하는 toString() 메소드를 오버라이딩
	{
		return ("Subject: " + this.subject + ", Question: " + this.question + "\n");
	}
	
}
