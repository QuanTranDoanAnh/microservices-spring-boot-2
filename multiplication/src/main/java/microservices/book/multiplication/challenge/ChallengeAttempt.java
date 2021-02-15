package microservices.book.multiplication.challenge;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import microservices.book.multiplication.user.User;

/**
 * 
 * @author tdanh
 * Identifies the attempt from a {@link User} to solve a challenge
 */
@Entity
public class ChallengeAttempt {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;
	private int factorA;
	private int factorB;
	private int resultAttempt;
	private boolean correct;
	
	public ChallengeAttempt() {
		this.id = null;
		this.user = null;
		this.factorA = 0;
		this.factorB = 0;
		this.resultAttempt = 0;
		this.correct = false;
	}
	
	public ChallengeAttempt(Long id, User user, int factorA, int factorB, int resultAttempt, boolean correct) {
		this.id = id;
		this.user = user;
		this.factorA = factorA;
		this.factorB = factorB;
		this.resultAttempt = resultAttempt;
		this.correct = correct;
	}
	
	public Long getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public int getFactorA() {
		return factorA;
	}
	public int getFactorB() {
		return factorB;
	}
	public int getResultAttempt() {
		return resultAttempt;
	}
	public boolean isCorrect() {
		return correct;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setFactorA(int factorA) {
		this.factorA = factorA;
	}

	public void setFactorB(int factorB) {
		this.factorB = factorB;
	}

	public void setResultAttempt(int resultAttempt) {
		this.resultAttempt = resultAttempt;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		if (obj == this) 
			return true;
		
		if (!(obj instanceof ChallengeAttempt))
			return false;
		
		ChallengeAttempt test = (ChallengeAttempt) obj;
		return (this.id == null ? test.getId() == null : this.id.equals(test.getId())
					&& ( this.user == null ? test.getUser() == null : this.user.equals(test.getUser()) )
					&& this.factorA == test.getFactorA()
					&& this.factorB == test.getFactorB()
					&& this.resultAttempt == this.getResultAttempt()
					&& this.correct == test.isCorrect());
		
	}

	@Override
	public int hashCode() {
		int result = 17;
		if (this.id != null)
			result = 31 * result + this.id.hashCode();
		if (this.user != null)
			result = 31 * result + this.user.hashCode();
		result = 31 * result + this.factorA;
		result = 31 * result + this.factorB;
		result = 31 * result + this.resultAttempt;
		Boolean booleanCorrect = Boolean.valueOf(correct);
		result = 31 * result + booleanCorrect.hashCode();
		return result;
	}
	
}
