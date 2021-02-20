package microservices.book.gamification.game;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import microservices.book.gamification.game.domain.LeaderBoardRow;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

	private final ScoreRepository scoreRepository;
	private final BadgeRepository badgeRepository;
	
	public LeaderBoardServiceImpl(ScoreRepository scoreRepository, BadgeRepository badgeRepository) {
		this.scoreRepository = scoreRepository;
		this.badgeRepository = badgeRepository;
	}
	
	@Override
	public List<LeaderBoardRow> getCurrentLeaderBoard() {
		// get score only
		List<LeaderBoardRow> scoreOnly = scoreRepository.findFirst10();
		// Combine with badges
		return scoreOnly.stream().map(row -> {
			List<String> badges = badgeRepository.findByUserIdOrderByBadgeTimestampDesc(row.getUserId())
					.stream()
					.map(b -> b.getBadgeType().getDescription())
					.collect(Collectors.toList());
			return row.withBadges(badges);
		}).collect(Collectors.toList());
	}

}
