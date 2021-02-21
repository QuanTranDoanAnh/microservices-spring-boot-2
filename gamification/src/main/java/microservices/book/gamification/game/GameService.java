package microservices.book.gamification.game;

import java.util.List;
import java.util.stream.Collectors;

import microservices.book.gamification.challenge.ChallengeSolvedEvent;
import microservices.book.gamification.game.domain.BadgeType;

public interface GameService {

	/**
	 * Process a new attempt from a given user
	 * 
	 * @param challenge the challenge data with user details, factors, etc.
	 * @return a {@link GameResult} object containing the new score and badge cards
	 *         obtained
	 */
	GameResult newAttemptForUser(ChallengeSolvedEvent challenge);

	final class GameResult {
		private final int score;
		private final List<BadgeType> badges;

		public GameResult() {
			this.score = 0;
			this.badges = List.of();
		}

		public GameResult(final int score, final List<BadgeType> badges) {
			this.score = score;
			this.badges = badges;
		}

		public int getScore() {
			return score;
		}

		public List<BadgeType> getBadges() {
			return badges;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (this == obj)
				return true;
			if (!(obj instanceof GameResult))
				return false;
			GameResult test = (GameResult) obj;
			return ((this.score == test.getScore())
					&& (this.badges == null ? test.getBadges() == null : this.badges.equals(test.getBadges())));
		}

		@Override
		public int hashCode() {
			int result = 17;
			final int PRIME_FACTOR = 31;
			result = result + PRIME_FACTOR * this.score;
			result = result + PRIME_FACTOR * (this.badges == null ? 0 : this.badges.hashCode());
			return result;
		}

		@Override
		public String toString() {
			final CharSequence COMMA_DELIM = "|";
			return "GameResult (score=" + this.score + ", badges=" + (this.badges == null ? "NULL"
					: this.badges.stream().map(BadgeType::getDescription).collect(Collectors.joining(COMMA_DELIM)))
					+ ")";
		}

	}
}
