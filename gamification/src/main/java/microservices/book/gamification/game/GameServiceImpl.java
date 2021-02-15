package microservices.book.gamification.game;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microservices.book.gamification.challenge.ChallengeSolvedDTO;
import microservices.book.gamification.game.domain.BadgeCard;
import microservices.book.gamification.game.domain.BadgeType;
import microservices.book.gamification.game.domain.ScoreCard;

@Service
public class GameServiceImpl implements GameService {

	private static final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);
	
	@Autowired
	private ScoreRepository scoreRespository;
	
	public GameServiceImpl(ScoreRepository scoreRepository) {
		this.scoreRespository = scoreRepository;
	}

	@Override
	public GameResult newAttemptForUser(ChallengeSolvedDTO challenge) {
		// We'll only give points if it's correct
		if (challenge.isCorrect()) {
			ScoreCard scoreCard = new ScoreCard(challenge.getUserId(), challenge.getAttemptId());
			scoreRespository.save(scoreCard);
			log.info("User {} scored {} points for attempt id {}", challenge.getUserAlias(), scoreCard.getScore(),
					challenge.getAttemptId());
			List<BadgeCard> badgeCards = processForBadges(challenge);
			return new GameResult(scoreCard.getScore(),
					badgeCards.stream().map(BadgeCard::getBadgeType).collect(Collectors.toList()));
		} else {
			log.info("Attempt id {} is not correct. User {}  does not get score", challenge.getAttemptId(),
					challenge.getUserAlias());
			return new GameResult(0, List.of());
		}
	}

	private List<BadgeCard> processForBadges(ChallengeSolvedDTO challenge) {
		return List.of(new BadgeCard(1L, BadgeType.LUCKY_NUMBER));
	}

}
