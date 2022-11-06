package com.tweetapp.tweets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.tweets.feignclient.AuthClient;

@Service
public class AuthTokenService {
	@Autowired
	private AuthClient authClient;
	static boolean isValid;

	public boolean checkTokenValidity(String token) {

		if (authClient.getValidity(token)) {
			isValid = true;
		} else {
			isValid = false;

		}
		return isValid;

	}
}