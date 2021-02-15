package microservices.book.multiplication.challenge;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ChallengeAttemptDTO {

	@Min(1)
	@Max(99)
	int factorA, factorB;
	@NotBlank
	String userAlias;
	@Positive(message="How could you possibly get a negative result here? Try again.")
	int guess;
	
	public ChallengeAttemptDTO() {
	}
	

	public ChallengeAttemptDTO(int factorA, int factorB, String userAlias, int guess) {
		this.factorA = factorA;
		this.factorB = factorB;
		this.userAlias = userAlias;
		this.guess = guess;
	}

	public int getFactorA() {
		return factorA;
	}

	public int getFactorB() {
		return factorB;
	}

	public String getUserAlias() {
		return userAlias;
	}

	public int getGuess() {
		return guess;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		if (obj == this) 
			return true;
		
		if (!(obj instanceof ChallengeAttemptDTO))
			return false;
		
		ChallengeAttemptDTO test = (ChallengeAttemptDTO) obj;
		return (this.factorA == test.getFactorA()
					&& this.factorB == test.getFactorB()
					&& (this.userAlias == null ? test.getUserAlias() == null : this.userAlias.equals(test.getUserAlias()))
					&& this.getGuess() == test.getGuess());
		
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.factorA;
		result = 31 * result + this.factorB;
		if (this.userAlias != null)
			result = 31 * result + this.userAlias.hashCode();
		result = 31 * result + this.guess;
		return result;
	}
}
