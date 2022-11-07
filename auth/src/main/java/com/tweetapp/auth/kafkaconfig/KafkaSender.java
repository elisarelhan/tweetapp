package com.tweetapp.auth.kafkaconfig;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.tweetapp.auth.entity.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaSender {
	private KafkaTemplate<String, String> kafkaTemplate;
//	private RoutingKafkaTemplate routingKafkaTemplate;
	private KafkaTemplate<String, User> userKafkaTemplate;

	public KafkaSender(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, User> userKafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
		this.userKafkaTemplate = userKafkaTemplate;
	}

//
//	KafkaSender(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, User> userKafkaTemplate) {
//		this.kafkaTemplate = kafkaTemplate;
////		this.routingKafkaTemplate = routingKafkaTemplate;
//		this.userKafkaTemplate = userKafkaTemplate;
//	}

	void sendMessage(String message, String topicName) {
		log.info("Sending : {}", message);
		log.info("--------------------------------");

		kafkaTemplate.send(topicName, message);
	}

//	void sendWithRoutingTemplate(String message, String topicName) {
//		log.info("Sending : {}", message);
//		log.info("--------------------------------");
//
//		routingKafkaTemplate.send(topicName, message.getBytes());
//	}

	void sendCustomMessage(User user, String topicName) {
		log.info("Sending Json Serializer : {}", user);
		log.info("--------------------------------");

		userKafkaTemplate.send(topicName, user);
	}

//	void sendMessageWithCallback(String message, String topicName) {
//		log.info("Sending : {}", message);
//		log.info("---------------------------------");
//
//		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
//
//		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
//			@Override
//			public void onSuccess(SendResult<String, String> result) {
//				log.info("Success Callback: [{}] delivered with offset -{}", message,
//						result.getRecordMetadata().offset());
//			}
//
//			@Override
//			public void onFailure(Throwable ex) {
//				LOG.warn("Failure Callback: Unable to deliver message [{}]. {}", message, ex.getMessage());
//			}
//		});
//	}

}
