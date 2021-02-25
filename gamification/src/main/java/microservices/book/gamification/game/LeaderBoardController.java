package microservices.book.gamification.game;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices.book.gamification.game.domain.LeaderBoardRow;

@RestController
@RequestMapping("/leaders")
public class LeaderBoardController {

	private static final Logger log = LoggerFactory.getLogger(LeaderBoardController.class);
	private final LeaderBoardService leaderBoardService;
	
	public LeaderBoardController(LeaderBoardService leaderBoardService) {
		this.leaderBoardService = leaderBoardService;
	}
	@GetMapping
	public List<LeaderBoardRow> getLeaderBoard() {
		log.info("Retrieving leaderboard");
		return this.leaderBoardService.getCurrentLeaderBoard();
	}
}
