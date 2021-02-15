package microservices.book.gamification.game;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import microservices.book.gamification.challenge.ChallengeSolvedDTO;
import microservices.book.gamification.game.GameService.GameResult;
import microservices.book.gamification.game.domain.BadgeType;
import microservices.book.gamification.game.domain.ScoreCard;


@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

	private GameService gameService;
	
	@Mock
	private ScoreRepository scoreRepository;
	
	@BeforeEach
	public void setUp() {
		this.gameService = new GameServiceImpl(scoreRepository);
	}
	
	@Test
	public void processCorrectAttemptTest() {
		// given
		long userId = 1L, attemptId = 10L;
		ChallengeSolvedDTO attempt = new ChallengeSolvedDTO(attemptId, true, 20, 70, userId, "john");
		ScoreCard scoreCard = new ScoreCard(userId, attemptId);

		// when
		final GameResult gameResult = gameService.newAttemptForUser(attempt);
		
		// then - should score one card and win the badge LUCKY_NUMBER
		then(gameResult).isEqualTo(new GameResult(10, List.of(BadgeType.LUCKY_NUMBER)));
		
		verify(scoreRepository).save(scoreCard);
	}
	
	@Test
	public void processWrongAttemptTest() {
		// given
		ScoreCard scoreCard = new ScoreCard(1L, 10L);
		// when
		GameResult gameResult = gameService.newAttemptForUser(new ChallengeSolvedDTO(10L, false, 20, 70, 1L, "john"));
		
		// then
		then(gameResult).isEqualTo(new GameResult(0, List.of()));
		verify(scoreRepository, never()).save(scoreCard);
	}
}
