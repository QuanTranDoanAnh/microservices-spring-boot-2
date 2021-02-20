package microservices.book.gamification.game;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import microservices.book.gamification.challenge.ChallengeSolvedDTO;
import microservices.book.gamification.game.GameService.GameResult;
import microservices.book.gamification.game.domain.BadgeType;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(GameController.class)
public class GameControllerTest {

	@MockBean
	private GameService gameService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private JacksonTester<ChallengeSolvedDTO> jsonChallengSolvedDTO;
	
	@Test
	public void postResultTest() throws Exception {
		// given
		GameResult gameResult = new GameResult(10, List.of(BadgeType.FIRST_WON));
		ChallengeSolvedDTO challenge = new ChallengeSolvedDTO(10L, true, 20, 40, 1L, "john");
		given(gameService.newAttemptForUser(eq(challenge))).willReturn(gameResult);
		
		// when
		MockHttpServletResponse response = mockMvc.perform(
				post("/attempts").contentType(MediaType.APPLICATION_JSON)
				.content(jsonChallengSolvedDTO.write(challenge).getJson()))
			.andDo(MockMvcResultHandlers.print())
			.andReturn().getResponse();
		
		// then
		then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		verify(gameService).newAttemptForUser(challenge);
	}
}
