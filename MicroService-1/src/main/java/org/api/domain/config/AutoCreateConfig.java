package org.api.domain.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("dev")
public class AutoCreateConfig {

	@Bean
	public NewTopic getLibaryEventsTopic() {
		return TopicBuilder.name("library-events").partitions(3).replicas(1).build();
	}

}
