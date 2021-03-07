package microservices.book.logs.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration {

	@Bean
	public TopicExchange logsExchange() {
		return ExchangeBuilder.topicExchange("logs.topic").durable(true).build();
	}

	@Bean
	public Queue logsQueue() {
		return QueueBuilder.durable("logs.queue").build();
	}

	@Bean
	public Binding logsBinding(final Queue logsQueue, final TopicExchange logsExchange) {
		return BindingBuilder.bind(logsQueue).to(logsExchange).with("#");
	}
}