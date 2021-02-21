package microservices.book.gamification.game;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;

import microservices.book.gamification.challenge.ChallengeSolvedEvent;

@ExtendWith(MockitoExtension.class)
public class GameEventHandlerTest {

	private GameEventHandler gameEventHandler;
	
	@Mock
	private GameService gameService;
	
	@BeforeEach
	public void setUp() {
		this.gameEventHandler = new GameEventHandler(gameService);
	}
	
	@Test
	public void handleMultiplicationSolvedTest() {
		// given
		ChallengeSolvedEvent solvedAttempt = new ChallengeSolvedEvent(1L, true, 30, 40, 10L, "john");
		
		// when
		this.gameEventHandler.handleMultiplicationSolved(solvedAttempt);
		
		// then
		verify(gameService).newAttemptForUser(solvedAttempt);
	}
	
	@Test
	public void handleMultiplicationSolvedTestWithException() throws Exception {
		// given
		ChallengeSolvedEvent solvedAttempt = new ChallengeSolvedEvent(1L, true, 30, 40, 10L, "john");
		willThrow(RuntimeException.class).given(gameService).newAttemptForUser(any(ChallengeSolvedEvent.class));
		
		// when
		assertThrows(AmqpRejectAndDontRequeueException.class, () -> this.gameEventHandler.handleMultiplicationSolved(solvedAttempt));
		
		// then
		
	}
}
