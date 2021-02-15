package microservices.book.multiplication.challenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

	private final ChallengeGeneratorService challengeGeneratorService;
	private static final Logger log = LoggerFactory.getLogger(ChallengeController.class);
	
	public ChallengeController(ChallengeGeneratorService challengeGeneratorService) {
		this.challengeGeneratorService = challengeGeneratorService;
	}
	
	@GetMapping("/random")
	Challenge getRandomChallenge() {
		Challenge challenge = challengeGeneratorService.randomChallenge();
		
		log.info("Generating a random challenge: {}", challenge);
		return challenge;
	}
}
