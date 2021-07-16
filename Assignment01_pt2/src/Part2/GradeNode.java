package Part2;

public class GradeNode { 
	
	Assignment assignment; // 과제
	Answer answer; // 과제에 대한 답
	private String score; // 점수
	GradeNode nextNode; // 연결리스트로 연결된 다음 노드
	
	public GradeNode() // 모든 변수를 null로 초기화하는 기본 생성자
	{ 
		this.assignment = null;
		this.answer = null;
		this.score = null;
		this.nextNode = null;
	}
	
	public GradeNode(Assignment assignment, Answer answer, String score) // nextNode를 제외한 모든 변수를 인자로 받은 값으로 생성하는 생성자 (단, 인자로 객체를 받으면 각 객체의 모든 변수를 전달)
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

	public boolean equals(GradeNode gradeNode) // 인자로 받은 객체의 변수값과 같은지 확인하는 메소드
	{
		return (this.assignment.equals(gradeNode.assignment) && this.answer.equals(gradeNode.answer) && this.score == gradeNode.score);
	}
	
	public String toString() // nextNode를 제외한 모든 변수의 값을 출력하는 toString() 메소드를 오버라이딩 (변수가 객체일 경우 해당 객체의 toString() 메소드 호출)
	{
		return (this.assignment.toString() + this.answer.toString() + "Grade: " + this.score + "\n");
	}
}
