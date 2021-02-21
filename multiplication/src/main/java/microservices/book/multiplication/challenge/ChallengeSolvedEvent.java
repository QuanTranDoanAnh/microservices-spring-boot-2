package microservices.book.multiplication.challenge;

public final class ChallengeSolvedEvent {

	private final long attemptId;
	private final boolean correct;
	private final int factorA;
	private final int factorB;
	private final long userId;
	private final String userAlias;

	public ChallengeSolvedEvent() {
		this.attemptId = 0L;
		this.correct = false;
		this.factorA = 0;
		this.factorB = 0;
		this.userId = 0L;
		this.userAlias = null;
	}

	public ChallengeSolvedEvent(final long attemptId, final boolean correct, final int factorA, final int factorB,
			final long userId, final String userAlias) {
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

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof ChallengeSolvedEvent))
			return false;
		ChallengeSolvedEvent test = (ChallengeSolvedEvent) obj;
		return ((this.attemptId == test.getAttemptId()) && (this.correct == test.isCorrect())
				&& (this.factorA == test.getFactorA()) && (this.factorB == test.getFactorB())
				&& (this.userId == test.getUserId())
				&& (this.userAlias == null ? test.getUserAlias() == null : this.userAlias.equals(test.getUserAlias())));
	}

	@Override
	public int hashCode() {
		int result = 17;
		final int PRIME_FACTOR = 31;
		result = result + PRIME_FACTOR * Long.hashCode(this.attemptId);
		result = result + PRIME_FACTOR * Boolean.hashCode(this.correct);
		result = result + PRIME_FACTOR * this.factorA;
		result = result + PRIME_FACTOR * this.factorB;
		result = result + PRIME_FACTOR * Long.hashCode(this.userId);
		result = result + PRIME_FACTOR * (this.userAlias == null ? 0 : this.userAlias.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "ChallengeSolvedEvent (attemptId=" + this.attemptId + ", correct=" + this.correct + ", factorA="
				+ this.factorA + ", factorB=" + this.factorB + ", userId=" + this.userId + ", userAlias="
				+ this.userAlias + ")";
	}

}
