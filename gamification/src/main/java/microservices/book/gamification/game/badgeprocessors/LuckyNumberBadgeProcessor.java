package microservices.book.gamification.game.badgeprocessors;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import microservices.book.gamification.challenge.ChallengeSolvedDTO;
import microservices.book.gamification.game.domain.BadgeType;
import microservices.book.gamification.game.domain.ScoreCard;

@Component
public class LuckyNumberBadgeProcessor implements BadgeProcessor {

	private static final int LUCKY_FACTOR = 42;
	
	@Override
	public Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList,
			ChallengeSolvedDTO solvedChallenge) {
		return solvedChallenge.getFactorA() == LUCKY_FACTOR ||
				solvedChallenge.getFactorB() == LUCKY_FACTOR ?
				Optional.of(BadgeType.LUCKY_NUMBER) :
				Optional.empty();
	}

	@Override
	public BadgeType badgeType() {
		return BadgeType.LUCKY_NUMBER;
	}

}
