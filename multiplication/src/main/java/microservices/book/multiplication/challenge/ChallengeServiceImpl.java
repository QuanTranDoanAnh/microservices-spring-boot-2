package microservices.book.multiplication.challenge;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import microservices.book.multiplication.user.User;

@Service
public class ChallengeServiceImpl implements ChallengeService {
	
	private static Logger log = LoggerFactory.getLogger(ChallengeGeneratorServiceImpl.class);
	
	private final UserRepository userRepository;
	private final ChallengeAttemptRepository attemptRepository;
	
	public ChallengeServiceImpl(UserRepository userRepository, ChallengeAttemptRepository attemptRepository) {
		this.userRepository = userRepository;
		this.attemptRepository = attemptRepository;
	}

	@Override
	public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO) {
		// Check if the user already exists for that alias, otherwise create it
		User user = userRepository.findByAlias(attemptDTO.getUserAlias())
						.orElseGet(() -> {
							log.info("Creating new user with alias {}",
									attemptDTO.getUserAlias());
							return userRepository.save(
									new User(attemptDTO.getUserAlias())
							);
						});
		// check if the attempt is correct
		boolean isCorrect = attemptDTO.getGuess() == attemptDTO.getFactorA() * attemptDTO.getFactorB();
		
		// Build the domain object. Null id for now
		ChallengeAttempt checkedAttempt = new ChallengeAttempt(null, 
				user, 
				attemptDTO.getFactorA(), 
				attemptDTO.getFactorB(), 
				attemptDTO.getGuess(), 
				isCorrect);
		
		// stores the attempt
		ChallengeAttempt storedAttempt = attemptRepository.save(checkedAttempt);
		return storedAttempt;
	}

	@Override
	public List<ChallengeAttempt> getStatsForUser(String userAlias) {
		return attemptRepository.findTop10ByUserAliasOrderByIdDesc(userAlias);
	}

}
