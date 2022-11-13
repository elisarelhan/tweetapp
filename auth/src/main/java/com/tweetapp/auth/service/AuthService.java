package com.tweetapp.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tweetapp.auth.entity.User;
import com.tweetapp.auth.exception.UserNotFoundException;
import com.tweetapp.auth.exception.UsernamePresentException;
import com.tweetapp.auth.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService implements UserDetailsService {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	private static final String USER_CREATED_TOPIC = "usercreated";
	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
	}

	public User save(User user) {
		if (userRepo.findByEmail(user.getEmail()) == null) {
			user.setPassword(bcryptEncoder.encode(user.getPassword()));
			User userCreated = userRepo.save(user);

//			kafkaTemplate.send(USER_CREATED_TOPIC, userCreated);
			return userCreated;
		} else {
			throw new UsernamePresentException("Email already registered");
		}
	}

	public User forgotPassword(String email, String password) {
		User user = userRepo.findByEmail(email);

		if (user != null) {
			user.setPassword(bcryptEncoder.encode(user.getPassword()));
			return userRepo.save(user);
		} else {
			throw new UserNotFoundException("Email not registered");
		}

	}

}
