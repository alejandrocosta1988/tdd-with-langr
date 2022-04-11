package iloveyouboss;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfileTest {
	
	private Profile profile;
	private BooleanQuestion questionIsThereRelocation;
	private Answer answerThereIsRelocation;
	
	@BeforeEach
	public void createProfile() {
		this.profile = new Profile();
	}
	
	@BeforeEach
	public void createQuestionAndAnswer() {
		this.questionIsThereRelocation = new BooleanQuestion(1, "Relocation pacakge?");
		this.answerThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
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
		Criterion criterion = new Criterion(new Answer(questionIsThereRelocation, Bool.TRUE), Weight.Important);
		
		boolean result = profile.matches(criterion);
		
		assertTrue(result);
		
	}

}
