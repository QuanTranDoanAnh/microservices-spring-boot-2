package microservices.book.gamification.game;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import microservices.book.gamification.game.domain.BadgeCard;

public interface BadgeRepository extends CrudRepository<BadgeCard, Long> {

	List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(Long userId);

}
