package microservices.book.gamification.game;

import org.springframework.data.repository.CrudRepository;

import microservices.book.gamification.game.domain.ScoreCard;

/**
 * Handles CRUD oeprations with ScoreCards and other related score queries
 * @author tdanh
 *
 */
public interface ScoreRepository extends CrudRepository<ScoreCard, Long> {

}
