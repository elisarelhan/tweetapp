package com.tweetapp.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.auth.exception.UserNotFoundException;
import com.tweetapp.auth.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@SecurityRequirement(name = "auth-service")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/getAllUsers")
	public ResponseEntity<?> getAllUsers(@RequestHeader(name = "Authorization", required = true) String token) {
		try {
			return ResponseEntity.ok(userService.findAllUsers());
		} catch (UserNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "/userByUsername/{email}")
	public ResponseEntity<?> getAllUsersByUsername(@RequestHeader(name = "Authorization", required = true) String token,
			@PathVariable String email) throws Exception {
		try {
			return ResponseEntity.ok(userService.findAllUsersByUsername(email));
		} catch (UserNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

}
