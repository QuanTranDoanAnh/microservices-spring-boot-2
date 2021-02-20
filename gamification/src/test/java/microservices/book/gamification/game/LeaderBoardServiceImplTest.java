package microservices.book.gamification.game;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import microservices.book.gamification.game.domain.BadgeCard;
import microservices.book.gamification.game.domain.BadgeType;
import microservices.book.gamification.game.domain.LeaderBoardRow;

@ExtendWith(MockitoExtension.class)
public class LeaderBoardServiceImplTest {

	private LeaderBoardService leaderBoardService;

	@Mock
	private ScoreRepository scoreRepository;

	@Mock
	private BadgeRepository badgeRepository;

	@BeforeEach
	public void setUp() {
		leaderBoardService = new LeaderBoardServiceImpl(scoreRepository, badgeRepository);
	}
	
	@Test
	public void getCurrentLeaderBoardTest() {
		// given
		LeaderBoardRow scoreRow = new LeaderBoardRow(1L, 30L, List.of());
		given(scoreRepository.findFirst10()).willReturn(List.of(scoreRow));
		given(badgeRepository.findByUserIdOrderByBadgeTimestampDesc(1L))
			.willReturn(List.of(new BadgeCard(1L, BadgeType.LUCKY_NUMBER)));
		
		// when
		List<LeaderBoardRow> leaderBoard = leaderBoardService.getCurrentLeaderBoard();
		
		// then
		List<LeaderBoardRow> expectedLeaderBoard = List.of(
				new LeaderBoardRow(1L, 30L, List.of(BadgeType.LUCKY_NUMBER.getDescription())));
		then(leaderBoard).isEqualTo(expectedLeaderBoard);
	}

}
