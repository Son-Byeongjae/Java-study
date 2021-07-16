package Part2;

public class Assignment { 
	
	private String subject; // �����
	private String question; // ���� ����
	
	public Assignment() // ��� ������ ��None������ �ʱ�ȭ�ϴ� �⺻ ������
	{
		this.subject = "None";
		this.question = "None";
	}
	
	public Assignment(String subject, String question) // ��� ������ ���ڷ� ���� ������ �ʱ�ȭ�ϴ� ������
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
	
	public boolean equals(Assignment assignment) // ���ڷ� ���� ��ü�� �������� ������ Ȯ���ϴ� �޼ҵ�
	{
		return (this.subject.equals(assignment.subject) && this.question.equals(assignment.question));
	}
	
	public String toString() // subject, question�� ���� ����ϴ� toString() �޼ҵ带 �������̵�
	{
		return ("Subject: " + this.subject + ", Question: " + this.question + "\n");
	}
	
}
