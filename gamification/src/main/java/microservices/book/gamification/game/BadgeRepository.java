package microservices.book.gamification.game;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import microservices.book.gamification.game.domain.BadgeCard;

/**
 * Handles data operations with BadgeCards
 * @author tdanh
 *
 */
public interface BadgeRepository extends CrudRepository<BadgeCard, Long> {

	/**
	 * Retrieve all BadgeCards for a given user
	 * @param userId the id of the user to look for BadgeCards
	 * @return the list of BadgeCards, ordered by most recent first
	 */
	List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(Long userId);

}
