package com.tweetapp.auth.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.tweetapp.auth.config.JwtTokenUtil;
import com.tweetapp.auth.entity.JwtRequest;
import com.tweetapp.auth.entity.JwtResponse;
import com.tweetapp.auth.entity.User;
import com.tweetapp.auth.service.AuthService;
import com.tweetapp.auth.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@SecurityRequirement(name = "auth-service")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthService authService;
	@Autowired
	private UserService userService;

	@PostMapping(value = "/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = authService.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		final long expiresIn=JwtTokenUtil.JWT_VALIDITY;

		return ResponseEntity.ok(new JwtResponse(token,expiresIn));
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
		return ResponseEntity.ok(authService.save(user));
	}
	@PatchMapping(value="/forgotPassword")
	public ResponseEntity<?> saveUser(@RequestBody JwtRequest request) throws Exception {
		return new ResponseEntity<>(authService.forgotPassword(request.getEmail(),request.getPassword()), HttpStatus.CREATED);
	}

//	@GetMapping(value="/getAllUsers")
//	public ResponseEntity<?> getAllUsers(@RequestHeader(name = "Authorization", required = true) String token )throws Exception
//	{
//		
//		return ResponseEntity.ok(userService.findAllUsers());
//		
//	}
//	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
}
