package com.tweetapp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.auth.entity.User;
import com.tweetapp.auth.exception.UserNotFoundException;
import com.tweetapp.auth.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public List<User> findAllUsers() {
		List<User> users = new ArrayList<>();
		users = userRepo.findAll();

		if (users.isEmpty()) {
			throw new UserNotFoundException("No User Found");
		} else {
			return users;
		}
	}

	public List<User> findAllUsersByUsername(String username) {
		List<User> usersByUsername = new ArrayList<>();
		usersByUsername = userRepo.findByUserName(username);
		if (usersByUsername.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		} else {
			return usersByUsername;
		}
	}
	public User findUserByEmail(String email)
	{
		User userDetails=userRepo.findByEmail(email);
		return userDetails;
		
	}

}
