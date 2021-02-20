package microservices.book.gamification.game;

import java.util.ArrayList;
import java.util.Collections;
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

import microservices.book.gamification.game.domain.LeaderBoardRow;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(LeaderBoardController.class)
public class LeaderBoardControllerTest {

	@MockBean
	private LeaderBoardService leaderBoardService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private JacksonTester<List<LeaderBoardRow>> json;
	
	@Test
	public void getLeaderBoardTest() throws Exception {
		// given
		LeaderBoardRow leaderBoardRow1 = new LeaderBoardRow(1L, 500L);
		LeaderBoardRow leaderBoardRow2 = new LeaderBoardRow(1L, 400L);
		List<LeaderBoardRow> leaderBoard = new ArrayList<LeaderBoardRow>();
		Collections.addAll(leaderBoard, leaderBoardRow1, leaderBoardRow2);
		given(leaderBoardService.getCurrentLeaderBoard()).willReturn(leaderBoard);
		
		// when
		MockHttpServletResponse response = mockMvc.perform(
				get("/leaders").accept(MediaType.APPLICATION_JSON))
			.andDo(MockMvcResultHandlers.print())
			.andReturn().getResponse();
		
		// then
		then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		then(response.getContentAsString()).isEqualTo(json.write(leaderBoard).getJson());
	}
	
}