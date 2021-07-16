package Part2;

public class GradeNode { 
	
	Assignment assignment; // ����
	Answer answer; // ������ ���� ��
	private String score; // ����
	GradeNode nextNode; // ���Ḯ��Ʈ�� ����� ���� ���
	
	public GradeNode() // ��� ������ null�� �ʱ�ȭ�ϴ� �⺻ ������
	{ 
		this.assignment = null;
		this.answer = null;
		this.score = null;
		this.nextNode = null;
	}
	
	public GradeNode(Assignment assignment, Answer answer, String score) // nextNode�� ������ ��� ������ ���ڷ� ���� ������ �����ϴ� ������ (��, ���ڷ� ��ü�� ������ �� ��ü�� ��� ������ ����)
	{ 
		if(assignment != null)
			this.assignment = new Assignment(assignment.getSubject(),assignment.getQuestion());
		if(answer != null)
			this.answer = new Answer(answer.getSolution());
		this.score = score;
	}
	
	public void setScore(String score)
	{ 
		this.score = score;
	}
	
	public String getScore() 
	{
		return this.score;
	}

	public boolean equals(GradeNode gradeNode) // ���ڷ� ���� ��ü�� �������� ������ Ȯ���ϴ� �޼ҵ�
	{
		return (this.assignment.equals(gradeNode.assignment) && this.answer.equals(gradeNode.answer) && this.score == gradeNode.score);
	}
	
	public String toString() // nextNode�� ������ ��� ������ ���� ����ϴ� toString() �޼ҵ带 �������̵� (������ ��ü�� ��� �ش� ��ü�� toString() �޼ҵ� ȣ��)
	{
		return (this.assignment.toString() + this.answer.toString() + "Grade: " + this.score + "\n");
	}
}
