package microservices.book.multiplication.challenge;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import microservices.book.multiplication.user.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByAlias(final String alias);
}
