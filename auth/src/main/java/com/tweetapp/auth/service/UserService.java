package com.tweetapp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tweetapp.auth.entity.User;
import com.tweetapp.auth.exception.UserNotFoundException;
import com.tweetapp.auth.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	private static final String USER_CREATED_TOPIC = "user";
	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	@Autowired
	private KafkaTemplate<String, List<User>> template;

	public List<User> findAllUsers() {
		List<User> users = new ArrayList<>();
		users = userRepo.findAll();

		if (users.isEmpty()) {
			throw new UserNotFoundException("No User Found");
		} else {
			template.send(USER_CREATED_TOPIC, users);
			return users;
		}
	}

	public List<User> findAllUsersByUsername(String username) {
		List<User> usersByUsername = new ArrayList<>();
		usersByUsername = userRepo.findByUserName(username);
		if (usersByUsername.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		} else {
//			template.send(USER_CREATED_TOPIC, "Username: " + username);
			return usersByUsername;
		}
	}

	public User findUserByEmail(String email) {
		User userDetails = userRepo.findByEmail(email);
		kafkaTemplate.send(USER_CREATED_TOPIC, userDetails);
		return userDetails;

	}


}
