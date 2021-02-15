package microservices.book.multiplication.challenge;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import microservices.book.multiplication.user.User;

@ExtendWith(MockitoExtension.class)
public class ChallengeServiceTest {

	private ChallengeService challengeService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private ChallengeAttemptRepository attemptRepository;

	@BeforeEach
	public void setUp() {
		challengeService = new ChallengeServiceImpl(userRepository, attemptRepository);
		// Keep in mind that we needed to move the
		// given(attemptRepository)... to the test cases
		// that use it to prevent the unused stubs errors.
	}

	@Test
	public void checkCorrectAttemptTest() {
		// given
		given(attemptRepository.save(any())).will(returnsFirstArg());
		given(userRepository.save(any())).will(returnsFirstArg());
		ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "john_doe", 3000);

		// when
		ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

		// then
		then(resultAttempt.isCorrect()).isTrue();
		verify(userRepository).save(new User("john_doe"));
		verify(attemptRepository).save(resultAttempt);
	}

	@Test
	public void checkWrongAttemptTest() {
		// given
		given(attemptRepository.save(any())).will(returnsFirstArg());
		given(userRepository.save(any())).will(returnsFirstArg());
		ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "john_doe", 5000);

		// when
		ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

		// then
		then(resultAttempt.isCorrect()).isFalse();
		verify(userRepository).save(new User("john_doe"));
		verify(attemptRepository).save(resultAttempt);
	}

	@Test
	public void checkExistingUserTest() {
		// given
		given(attemptRepository.save(any())).will(returnsFirstArg());
		User existingUser = new User(1L, "john_doe");
		given(userRepository.findByAlias("john_doe")).willReturn(Optional.of(existingUser));
		ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "john_doe", 5000);

		// when
		ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

		// then
		then(resultAttempt.isCorrect()).isFalse();
		then(resultAttempt.getUser()).isEqualTo(existingUser);
		verify(userRepository, never()).save(any());
		verify(attemptRepository).save(resultAttempt);
	}
	
	@Test
	public void retrieveStatsTest() {
		// given
		User user = new User("john_doe");
		ChallengeAttempt attempt1 = new ChallengeAttempt(1L, user, 50, 60, 3000, true);
		ChallengeAttempt attempt2 = new ChallengeAttempt(2L, user, 50, 60, 3001, false);
		List<ChallengeAttempt> lastAttempts = List.of(attempt1, attempt2);
		given(attemptRepository.findTop10ByUserAliasOrderByIdDesc("john_doe"))
			.willReturn(lastAttempts);
		
		// when
		List<ChallengeAttempt> latestAttemptResults = challengeService.getStatsForUser("john_doe");
		
		// then
		then(latestAttemptResults).isEqualTo(lastAttempts);
	}
}
