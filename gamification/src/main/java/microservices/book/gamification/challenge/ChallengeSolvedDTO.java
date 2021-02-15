package microservices.book.gamification.challenge;

public final class ChallengeSolvedDTO {

	private final long attemptId;
	private final boolean correct;
	private final int factorA;
	private final int factorB;
	private final long userId;
	private final String userAlias;
	
	public ChallengeSolvedDTO() {
		this.attemptId = 0L;
		this.correct = false;
		this.factorA = 0;
		this.factorB = 0;
		this.userId = 0L;
		this.userAlias = null;
	}
	
	public ChallengeSolvedDTO(final long attemptId, final boolean correct, final int factorA, final int factorB, final long userId, final String userAlias) {
		this.attemptId = attemptId;
		this.correct = correct;
		this.factorA = factorA;
		this.factorB = factorB;
		this.userId = userId;
		this.userAlias = userAlias;
	}

	public long getAttemptId() {
		return attemptId;
	}

	public boolean isCorrect() {
		return correct;
	}

	public int getFactorA() {
		return factorA;
	}

	public int getFactorB() {
		return factorB;
	}

	public long getUserId() {
		return userId;
	}

	public String getUserAlias() {
		return userAlias;
	}
	
}
