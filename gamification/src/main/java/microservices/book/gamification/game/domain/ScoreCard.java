package microservices.book.gamification.game.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class represents the Score linked to an attempt in the game,
 * with an associated user and the timestamp in which the score
 * is registered.
 * @author tdanh
 *
 */
@Entity
public class ScoreCard {

	// the default score assigned to this card, if not specified
	public static final int DEFAULT_SCORE = 10;
	
	@Id
	@GeneratedValue
	private Long cardId;
	private Long userId;
	private Long attemptId;
	private long scoreTimestamp;
	private int score;
	
	public ScoreCard() {
		this.cardId = null;
		this.userId = null;
		this.attemptId = null;
		this.scoreTimestamp = System.currentTimeMillis();
		this.score = DEFAULT_SCORE;
	}
	
	public ScoreCard(final Long cardId, final Long userId, final Long attemptId, final long scoreTimestamp, final int score) {
		this.cardId = cardId;
		this.userId = userId;
		this.attemptId = attemptId;
		this.scoreTimestamp = scoreTimestamp;
		this.score = score;
	}
	
	public ScoreCard(final Long userId, final Long attemptId) {
		this(null,userId, attemptId, System.currentTimeMillis(), DEFAULT_SCORE);
	}
	
	
	
	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAttemptId() {
		return attemptId;
	}

	public void setAttemptId(Long attemptId) {
		this.attemptId = attemptId;
	}

	public long getScoreTimestamp() {
		return scoreTimestamp;
	}

	public void setScoreTimestamp(long scoreTimestamp) {
		this.scoreTimestamp = scoreTimestamp;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null )
			return false;
		
		if (this == obj)
			return true;
		
		if (!(obj instanceof ScoreCard))
			return false;
		
		ScoreCard test = (ScoreCard) obj;
		return ( (this.cardId == null ? test.getCardId() == null : this.cardId.equals(test.getCardId())) 
				&& (this.userId == null ? test.getUserId() == null : this.userId.equals(test.getUserId()))
				&& (this.attemptId == null ? test.getAttemptId() == null : this.attemptId.equals(test.getAttemptId())) 
				&& (this.score == test.getScore()) );
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		if (this.cardId != null)
			result = 31 * result + this.cardId.hashCode();
		if (this.userId != null)
			result = 31 * result + this.userId.hashCode();
		if (this.attemptId != null)
			result = 31 * result + this.attemptId.hashCode();
		result = 31 * result + score;
		return result;
	}
	
}
