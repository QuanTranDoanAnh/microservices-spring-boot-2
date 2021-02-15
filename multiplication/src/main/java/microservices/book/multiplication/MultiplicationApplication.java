package microservices.book.multiplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultiplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiplicationApplication.class, args);
	}
	/* Doing this way we cannot auto-configuration an Hibernate5Module
	@Bean
	public ObjectMapper objectMapper() {
		var om = new ObjectMapper();
		om.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		return om;
	}*/
	/* But must do as the following way. However for educational purpose as in the 
	 * Learn Microservices with Spring Boot 2, I have to comment out this
	 * --END COMMENT
	@Bean
	public ObjectMapper objectMapper() {
		var om = new ObjectMapper();
		om.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		Hibernate5Module hibernateModule = new Hibernate5Module();
		om.registerModule(hibernateModule);
		return om;
	}
	*/

}
