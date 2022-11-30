package com.tweetapp.tweets.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.tweets.entity.Tweet;
import com.tweetapp.tweets.exception.ResourceNotFoundException;
import com.tweetapp.tweets.repository.TweetRepo;
import com.tweetapp.tweets.service.TweetService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
//@RequestMapping("/tweet-service")
public class TweetController {
	@Autowired
	private TweetService tweetService;
	@Autowired
	private TweetRepo postRepository;

	@GetMapping("/getAllTweets")
	public ResponseEntity<?> getAllPosts(@RequestHeader(name = "Authorization", required = true) String token) {
		
		try {
			return ResponseEntity.ok(tweetService.getAllTweets());
		} catch (ResourceNotFoundException e) {
			log.warn(e.getMessage());
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllTweets/{userEmail}")
	public ResponseEntity<?> getAllTweetsByUser(@RequestHeader(name = "Authorization", required = true) String token,
			@Valid @PathVariable String userEmail) {
		try {
			return ResponseEntity.ok(tweetService.getTweetsByUser(userEmail));
		} catch (ResourceNotFoundException e) {
			log.warn(e.getMessage());
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/getTweet/{tweetId}")
	public ResponseEntity<?> getTweet(@RequestHeader(name = "Authorization", required = true) String token,
			@Valid @PathVariable int tweetId) {
		try {
			return ResponseEntity.ok(tweetService.getTweet(tweetId));
		} catch (ResourceNotFoundException e) {
			log.warn(e.getMessage());
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/postTweet")
	public Tweet createPost(@RequestHeader(name = "Authorization", required = true) String token,
			@Valid @RequestBody Tweet tweet) throws Exception {
		return tweetService.saveTweet(tweet);
	}

	@PutMapping("/updateTweet/{tweetId}")
	public Tweet updateTweet(@RequestHeader(name = "Authorization", required = true) String token,
			@Valid @PathVariable int tweetId,@Valid  @RequestBody Tweet tweetRequest) {
		return tweetService.updateTweet(tweetId, tweetRequest.getTweetContent());
	}

	@PutMapping("/updateTweetLikes/{tweetId}")
	public Tweet updateTweetLikes(@RequestHeader(name = "Authorization", required = true) String token,
			@Valid @PathVariable int tweetId,@Valid  @RequestBody Tweet tweetRequest) {
		return tweetService.updateTweetLikes(tweetId, tweetRequest.getLikes());
	}

	@DeleteMapping("/deleteTweet/{tweetId}")
	public ResponseEntity<?> deletePost(@RequestHeader(name = "Authorization", required = true) String token,
			@Valid @PathVariable int tweetId) {
		tweetService.deleteTweet(tweetId);
		return new ResponseEntity(1,HttpStatus.OK);
	}

}
