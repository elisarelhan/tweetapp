package com.tweetapp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.auth.entity.User;
import com.tweetapp.auth.repository.UserRepo;
@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;

	public List<User> findAllUsers() {
		List<User> users=new ArrayList<>();
		users=userRepo.findAll();
		return users;
	}
	public List<User> findAllUsersByUsername(String username) {
		List<User> usersByUsername=new ArrayList<>();
		usersByUsername= userRepo.findByUserName(username);
		return usersByUsername;
	}
	
}
