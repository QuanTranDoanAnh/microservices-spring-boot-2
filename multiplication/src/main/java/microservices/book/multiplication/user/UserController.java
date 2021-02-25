package microservices.book.multiplication.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	private final UserRepository userRepository;
	
	public UserController(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/{idList}")
	public List<User> getUsersByIdList(@PathVariable final List<Long> idList) {
		log.info("Resolving aliases for users {}", idList);
		return userRepository.findAllByIdIn(idList);
	}
}
