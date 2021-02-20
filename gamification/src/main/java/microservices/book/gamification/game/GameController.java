package microservices.book.gamification.game;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import microservices.book.gamification.challenge.ChallengeSolvedDTO;

@RestController
@RequestMapping("/attempts")
public class GameController {

	private final GameService gameService;
	
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	void postResult(@RequestBody ChallengeSolvedDTO challenge) {
		gameService.newAttemptForUser(challenge);
	}
	
	
}
