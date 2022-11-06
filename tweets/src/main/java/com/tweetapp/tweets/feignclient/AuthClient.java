package com.tweetapp.tweets.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

//@FeignClient(name = "auth-service", url = "${AUTH_SERVICE_URI:http://localhost:8000}")
@FeignClient(name = "auth-service", url = "http://localhost:8000")
public interface AuthClient {

	@GetMapping(value = "/validate")
	public boolean getValidity(@RequestHeader("Authorization") String token);

}
