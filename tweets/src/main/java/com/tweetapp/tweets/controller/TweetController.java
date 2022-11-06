package com.tweetapp.tweets.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.tweets.entity.Tweet;
import com.tweetapp.tweets.repository.TweetRepo;
import com.tweetapp.tweets.service.TweetService;



@RestController
//@RequestMapping("/tweet-service")
public class TweetController {
	 @Autowired 
	 private TweetService tweetService;
	 @Autowired 
	 private TweetRepo postRepository;
	 
	 @GetMapping("/getAllTweets")
	    public Page<Tweet> getAllPosts(@RequestHeader(name = "Authorization", required = true) String token,Pageable pageable) {
	        return tweetService.getAllTweets(pageable);
	    }

	 @GetMapping("/getAllTweets/{userEmail}")
	    public List<Tweet> getAllTweetsByUser(@RequestHeader(name = "Authorization", required = true) String token,@PathVariable String userEmail) {
	        return tweetService.getTweetsByUser(userEmail);
	    }
	    @PostMapping("/postTweet")
	    public Tweet createPost(@RequestHeader(name = "Authorization", required = true) String token, @RequestBody Tweet tweet) {
	        return tweetService.saveTweet(tweet);
	    }

	    @PutMapping("/updateTweet/{tweetId}")
	    public Tweet updateTweet(@RequestHeader(name = "Authorization", required = true) String token,@PathVariable int tweetId,  @RequestBody Tweet tweetRequest) {
	        return tweetService.updateTweet(tweetId, tweetRequest.getTweetContent());
	    }

	    @PutMapping("/updateTweetLikes/{tweetId}")
	    public Tweet updateTweetLikes(@RequestHeader(name = "Authorization", required = true) String token,@PathVariable int tweetId,  @RequestBody Tweet tweetRequest) {
	        return tweetService.updateTweetLikes(tweetId, tweetRequest.getLikes());
	    }


	    @DeleteMapping("/deleteTweet/{tweetId}")
	    public ResponseEntity<?> deletePost(@RequestHeader(name = "Authorization", required = true) String token,@PathVariable int tweetId) {
	        tweetService.deleteTweet(tweetId);
	        return new ResponseEntity("Deleted",HttpStatus.OK);
	    }

	}


