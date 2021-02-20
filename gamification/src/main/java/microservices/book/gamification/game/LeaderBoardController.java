package microservices.book.gamification.game;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices.book.gamification.game.domain.LeaderBoardRow;

@RestController
@RequestMapping("/leaders")
public class LeaderBoardController {

	private final LeaderBoardService leaderBoardService;
	
	public LeaderBoardController(LeaderBoardService leaderBoardService) {
		this.leaderBoardService = leaderBoardService;
	}
	@GetMapping
	public List<LeaderBoardRow> getLeaderBoard() {
		return this.leaderBoardService.getCurrentLeaderBoard();
	}
}
