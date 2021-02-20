package microservices.book.multiplication.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserRepository userRepository;
	
	public UserController(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/{idList}")
	public List<User> getUsersByIdList(@PathVariable final List<Long> idList) {
		return userRepository.findAllByIdIn(idList);
	}
}
