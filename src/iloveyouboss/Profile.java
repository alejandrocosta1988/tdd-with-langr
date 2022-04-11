package iloveyouboss;

import java.util.HashMap;
import java.util.Map;

public class Profile {
	
	private Map<String, Answer> answers = new HashMap<>();
	
	public void add(Answer answer) {
		answers.put(answer.getQuestionText(), answer);
	}

	public boolean matches(Criterion criterion) {
		
		Answer answer = getMatchingProfileAnswer(criterion);
		return criterion.getAnswer().match(answer);
		
	}
	
	private Answer getMatchingProfileAnswer(Criterion criterion) {
		return answers.get(criterion.getAnswer().getQuestionText());
	}

}
