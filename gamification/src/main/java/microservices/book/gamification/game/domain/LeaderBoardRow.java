package microservices.book.gamification.game.domain;

import java.util.List;


public final class LeaderBoardRow {

	private Long userId;
	private Long totalScore;
	private List<String> badges;
	
	public LeaderBoardRow() {
		this.userId = null;
		this.totalScore = 0L;
		this.badges = null;
	}
	
	public LeaderBoardRow(final Long userId, final Long totalScore, final List<String> badges) {
		this.userId = userId;
		this.totalScore = totalScore;
		this.badges = badges;
	}
	
	public LeaderBoardRow(final Long userId, final Long totalScore) {
		this.userId = userId;
		this.totalScore = totalScore;
		this.badges = List.of();
	}

	public Long getUserId() {
		return userId;
	}

	public Long getTotalScore() {
		return totalScore;
	}

	public List<String> getBadges() {
		return badges;
	}
	
	// for Lombok's @With on object's badges properties
	public LeaderBoardRow withBadges(List<String> badges) {
		return this.badges == badges ? this : new LeaderBoardRow(this.userId, this.totalScore, badges);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null )
			return false;
		
		if (this == obj)
			return true;
		
		if (!(obj instanceof LeaderBoardRow))
			return false;
		
		LeaderBoardRow test = (LeaderBoardRow) obj;
		return ( (this.userId == null ? test.getUserId() == null : this.userId.equals(test.getUserId()))
				&& (this.totalScore == null ? test.getTotalScore() == null : this.totalScore.equals(test.getTotalScore())) 
				&& (this.badges == null ? test.getBadges() == null : this.badges.equals(test.getBadges())) );
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		if (this.userId != null)
			result = 31 * result + this.userId.hashCode();
		if (this.totalScore != null)
			result = 31 * result + this.totalScore.hashCode();
		if (this.badges != null)
			result = 31 * result + this.badges.hashCode();
		return result;
	}
}
