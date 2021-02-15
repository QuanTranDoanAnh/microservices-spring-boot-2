package microservices.book.multiplication.challenge;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attempts")
public class ChallengeAttemptController {

	private static final Logger log = LoggerFactory.getLogger(ChallengeAttemptController.class);
	
	private final ChallengeService challengeService;
	
	public ChallengeAttemptController(ChallengeService challengeService) {
		this.challengeService = challengeService;
	}
	
	@PostMapping
	ResponseEntity<ChallengeAttempt> postResult(@RequestBody @Valid ChallengeAttemptDTO challengeAttemptDTO) {
		ChallengeAttempt attempt = challengeService.verifyAttempt(challengeAttemptDTO);
		return ResponseEntity.ok(attempt);
	}
	
	@GetMapping
	ResponseEntity<List<ChallengeAttempt>> getStatistics(@RequestParam("alias") String alias) {
		return ResponseEntity.ok(challengeService.getStatsForUser(alias));
	}
}
