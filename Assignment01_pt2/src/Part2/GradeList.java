package Part2;

public class GradeList { // GradeNode ��ü�� ���Ḯ��Ʈ�� ������ Ŭ����

	GradeNode head; // ���Ḯ��Ʈ�� ù ���
	
	public GradeList() // �⺻ �����ڴ� head ��带 null�� �ʱ�ȭ
	{ 
		this.head = null;
	}
	
	public GradeList(GradeList gradeList) // ���ڷ� ���� gradeList ���Ḯ��Ʈ�� �����ϴ� Copy Constructor ������
	{
		
		if (gradeList == null) // ���ڷ� ���� ���Ḯ��Ʈ�� �������� ���� ���
		{ 
			System.out.println("Nothing to copy");
			return;
		}
		
		/* ���� GradeList�� head ��ü�� ���� ��� head�� ���ڷ� ���� gradeList�� head�� ���� */
		if( this.head == null)
			this.head = new GradeNode(gradeList.head.assignment, gradeList.head.answer, gradeList.head.getScore());
		/* ���ڷ� ���� gradeList ���Ḯ��Ʈ�� ���� (�� ����� �������� ���� ����, ���� ���� X) */
		GradeNode tmpNode = this.head;
		GradeNode copiedNode = gradeList.head.nextNode;
		while( copiedNode != null )
		{
			tmpNode.nextNode = new GradeNode(copiedNode.assignment, copiedNode.answer, copiedNode.getScore());
			tmpNode = tmpNode.nextNode;
			copiedNode = copiedNode.nextNode;
		}
	}
	
	public void addNode(Assignment assignment, Answer answer, String score) // ���ڷ� ���� ���� �� ��ü��� ������ ��带 ���Ḯ��Ʈ�� �߰��ϴ� �޼ҵ�
	{ 
		
		/* ���� GradeList�� head ��ü�� ���� ��� ���ڷ� ���� ���� �� ��ü��� ��� ���� */
		if( this.head == null )
			this.head = new GradeNode(assignment, answer, score);
		else
		{
			/* ���� ����� ���� ��尡 null�� �ƴ� ������ ���� ��带 �ű� �� ������ ����� ���� ��忡 ��� ���� �� ��ü ���� */
			GradeNode currNode = this.head;
			while( currNode.nextNode != null )
				currNode = currNode.nextNode;
			currNode.nextNode = new GradeNode(assignment, answer, score);
		}
	}
	
	public void printList() // ���Ḯ��Ʈ ��� �޼ҵ�
	{ 
		/* ����Ʈ�� ù ������ toString �޼ҵ带 ȣ���Ͽ� ��� */
		GradeNode currNode = this.head;
		while (currNode != null)
		{
			System.out.print(currNode.toString());
			currNode = currNode.nextNode;
		}
		System.out.println();
	}
	
}
