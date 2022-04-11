package iloveyouboss;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfileTest {
	
	private Profile profile;
	private BooleanQuestion questionIsThereRelocation;
	private Answer answerThereIsRelocation;
	private Answer answerThereIsNotRelocation;
	
	private BooleanQuestion questionDoesReimburseTuition;
	private Answer answerDoesNotReimburseTuition;
	
	@BeforeEach
	public void createProfile() {
		this.profile = new Profile();
	}
	
	@BeforeEach
	public void createQuestionAndAnswerToRelocation() {
		this.questionIsThereRelocation = new BooleanQuestion(1, "Relocation pacakge?");
		this.answerThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
		this.answerThereIsNotRelocation = new Answer(questionIsThereRelocation, Bool.FALSE);
	}
	
	@BeforeEach
	public void createQuestionAndAnswerToReimburse() {
		this.questionDoesReimburseTuition = new BooleanQuestion(1, "Does reimburse tuition?");
		this.answerDoesNotReimburseTuition = new Answer(questionDoesReimburseTuition, Bool.FALSE);
	}

	@Test
	public void matchesNothingWhenProfileEmpty() {
		
		Criterion criterion = new Criterion(answerThereIsRelocation, Weight.DontCare);
		
		boolean result = profile.matches(criterion);
		
		assertFalse(result);
		
	}
	
	@Test
	public void matchesWhenProfileContainsMatchingAnswer() {
		
		profile.add(answerThereIsRelocation);
		Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);
		
		boolean result = profile.matches(criterion);
		
		assertTrue(result);
		
	}
	
	@Test
	public void doesNotMatchWhenNoMatchingAnswer() {
		
		profile.add(answerThereIsNotRelocation);
		Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);
		
		boolean result = profile.matches(criterion);
		
		assertFalse(result);
		
	}
	
	@Test
	public void matchesWhenContainsMultipleAnswers() {
		
		profile.add(answerThereIsRelocation);
		profile.add(answerDoesNotReimburseTuition);
		Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);
		
		boolean result = profile.matches(criterion);
		
		assertTrue(result);
		
	}

}
