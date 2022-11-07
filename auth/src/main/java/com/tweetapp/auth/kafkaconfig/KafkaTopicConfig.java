package com.tweetapp.auth.kafkaconfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
class KafkaTopicConfig {

	@Bean
	public NewTopic topic1() {
		return TopicBuilder.name("userCreated").build();
	}

	@Bean
	public NewTopic topic2() {
		return TopicBuilder.name("loginSuccessful").build();
	}

	@Bean
	public NewTopic topic3() {
		return TopicBuilder.name("passwordResetSuccessful").build();
	}

	@Bean
	public NewTopic topic4() {
		return TopicBuilder.name("users").build();
	}

}
