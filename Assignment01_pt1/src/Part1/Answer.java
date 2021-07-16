package Part1;

public class Answer {
	
	private String solution; // 과제에 대한 답
	
	public Answer() // 모든 변수를 ‘None’으로 초기화하는 기본 생성자
	{ 
		this.solution = "None";
	}
	
	public Answer(String solution) // 모든 변수를 인자로 받은 값으로 설정하는 생성자
	{ 
		this.solution = solution;
	}

	public String getSolution() 
	{
		return this.solution;
	}

	public void setSolution(String solution) 
	{
		this.solution = solution;
	}
	
	public boolean equals(Answer answer) // 인자로 받은 객체의 변수값과 같은지 확인하는 메소드
	{
		return (this.solution.equals(answer.solution));
	}
	
	public String toString() // solution의 값을 출력하는 toString() 메소드를 오버라이딩
	{ 
		return ("Solution: " + this.solution + "\n");
	}
	
}
