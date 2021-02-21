package microservices.book.gamification.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import microservices.book.gamification.challenge.ChallengeSolvedEvent;

@Service
public class GameEventHandler {

	private static final Logger log = LoggerFactory.getLogger(GameEventHandler.class);
	
	private final GameService gameService;
	
	public GameEventHandler(GameService gameService) {
		this.gameService = gameService;
	}
	
	@RabbitListener(queues = "${amqp.queue.gamification}")
	void handleMultiplicationSolved(final ChallengeSolvedEvent event) {
		log.info("Challenge Solved Event received: {}", event.getAttemptId());
		try {
			gameService.newAttemptForUser(event);
		} catch(final Exception e) {
			log.error("Error when trying to process ChallengeSolvedEvent", e);
			// Avoids the event to be re-queued and reprocessed
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
}
