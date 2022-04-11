package iloveyouboss;

public class Profile {
	
	private Answer answer;
	
	public void add(Answer answer) {
		this.answer = answer;
	}

	public boolean matches(Criterion criterion) {
		
		return answer != null && answer.match(criterion.getAnswer());
		
	}

}
