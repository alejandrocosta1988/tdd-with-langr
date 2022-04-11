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
	private Answer answerReimbursesTuition;
	
	private Criteria criteria;
	
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
		this.answerReimbursesTuition = new Answer(questionDoesReimburseTuition, Bool.TRUE);
	}
	
	@BeforeEach
	public void createCriteria() {
		this.criteria = new Criteria();
	}

	@Test
	public void matchesWhenProfileContainsMatchingAnswer() {
		
		profile.add(answerThereIsRelocation);
		Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);
		
		assertTrue(profile.matches(criterion));
		
	}
	
	@Test
	public void doesNotMatchWhenNoMatchingAnswer() {
		
		profile.add(answerThereIsNotRelocation);
		Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);
		
		assertFalse(profile.matches(criterion));
		
	}
	
	@Test
	public void matchesWhenContainsMultipleAnswers() {
		
		profile.add(answerThereIsRelocation);
		profile.add(answerDoesNotReimburseTuition);
		Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);
		
		assertTrue(profile.matches(criterion));
		
	}
	
	@Test
	public void doesNotMatchWhenNoneOfMultipleCriteriaMatch() {
		
		profile.add(answerDoesNotReimburseTuition);
		criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
		criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));
		
		assertFalse(profile.matches(criteria));
		
	}
	
	@Test
	public void matchesWhenAnyOfMultipleCriteriaMatch() {
		
		profile.add(answerReimbursesTuition);
		criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
		criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));
		
		assertTrue(profile.matches(criteria));
		
	}
	
	@Test
	public void doesNotMatchWhenAnyMustMeetCriteriaNotMet() {
		
		profile.add(answerThereIsRelocation);
		profile.add(answerDoesNotReimburseTuition);
		criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
		criteria.add(new Criterion(answerReimbursesTuition, Weight.MustMatch));
		
		assertFalse(profile.matches(criteria));
		
	}
	
	@Test
	public void matchesWhenCriterionIsDontCare() {
		
		profile.add(answerDoesNotReimburseTuition);
		Criterion criterion = new Criterion(answerReimbursesTuition, Weight.DontCare);
		
		assertTrue(profile.matches(criterion));
		
	}

}
