package microservices.book.gamification.game.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BadgeCard {

	@Id
	@GeneratedValue
	private Long badgeId;
	
	private Long userId;
	private long badgeTimestamp;
	private BadgeType badgeType;
	
	public BadgeCard() {
		this.badgeId = null;
		this.userId = null;
		this.badgeTimestamp = System.currentTimeMillis();
		this.badgeType = null;
	}
	
	public BadgeCard(final Long badgeId, final Long userId, final long badgeTimestamp, final BadgeType badgeType) {
		this.badgeId = badgeId;
		this.userId = userId;
		this.badgeTimestamp = badgeTimestamp;
		this.badgeType = badgeType;
	}
	
	public BadgeCard(final Long userId, final BadgeType badgeType) {
		this(null, userId, System.currentTimeMillis(), badgeType);
	}

	public Long getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(Long badgeId) {
		this.badgeId = badgeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public long getBadgeTimestamp() {
		return badgeTimestamp;
	}

	public void setBadgeTimestamp(long badgeTimestamp) {
		this.badgeTimestamp = badgeTimestamp;
	}

	public BadgeType getBadgeType() {
		return badgeType;
	}

	public void setBadgeType(BadgeType badgeType) {
		this.badgeType = badgeType;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null )
			return false;
		
		if (this == obj)
			return true;
		
		if (!(obj instanceof BadgeCard))
			return false;
		
		BadgeCard test = (BadgeCard) obj;
		return ( (this.badgeId == null ? test.getBadgeId() == null : this.badgeId.equals(test.getBadgeId())) 
				&& (this.userId == null ? test.getUserId() == null : this.userId.equals(test.getUserId()))
				&& (this.badgeType == null ? test.getBadgeType() == null : this.badgeType.equals(test.getBadgeType())) );
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		if (this.badgeId != null)
			result = 31 * result + this.badgeId.hashCode();
		if (this.userId != null)
			result = 31 * result + this.userId.hashCode();
		if (this.badgeType != null)
			result = 31 * result + this.badgeType.hashCode();
		return result;
	}
	
}
