package microservices.book.gamification.game;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import microservices.book.gamification.game.domain.ScoreCard;

/**
 * Handles CRUD oeprations with ScoreCards and other related score queries
 * @author tdanh
 *
 */
public interface ScoreRepository extends CrudRepository<ScoreCard, Long> {

	Optional<Integer> getTotalScoreForUser(long userId);

	List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(Long userId);

}
