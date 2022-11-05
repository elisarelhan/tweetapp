package com.tweetapp.tweets.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.tweets.entity.Tweet;
import com.tweetapp.tweets.service.TweetService;



@RestController
public class TweetController {
	 @Autowired 
	 private TweetService tweetService;
	 
	 @PostMapping("/postTweet")
	 public ResponseEntity<?> postTweet(@RequestBody Tweet tweet)
	 {
		 Tweet tweetObj =tweetService.saveTweet(tweet);
		 return ResponseEntity.ok(tweetObj);
	 }
	  
	 @GetMapping("/getAllTweets")
	 public ResponseEntity<?> getAllTweets()
	 {
		 List<Tweet> tweets=new ArrayList<>();
		tweets=tweetService.getAllTweets();
		 return ResponseEntity.ok(tweets);
		 
	 }
	 @GetMapping("/getAllTweetsByUser/{userEmail}")
	 public ResponseEntity<?> getAllTweetsByUser(@PathVariable String userEmail)
	 {
		 List<Tweet> tweetsByUser=new ArrayList<>();
		tweetsByUser=tweetService.getTweetsByUser(userEmail);
		 return ResponseEntity.ok(tweetsByUser);
	 }
	 
	 @PatchMapping("/updateTweet/{tweetId}")
	 public ResponseEntity<?> updateTweet(@PathVariable int tweetId, @RequestBody String tweetContent) 
	 {
		   Tweet tweet= tweetService.updateTweet(tweetId, tweetContent);
		   return ResponseEntity.ok(tweet);
	 
	 
	 }
	 
	 @PatchMapping("/updateTweetLikes/{tweetId}")
	 public ResponseEntity<?> updateLikes(@PathVariable int tweetId, @RequestBody int likes) 
	 {
		   Tweet tweet= tweetService.updateTweetLikes(tweetId, likes);
		   return ResponseEntity.ok(tweet);
	 
	 
	 }
	 
	 @DeleteMapping("/deleteTweet/{tweetId}")
	 public void deleteTweet(@PathVariable int tweetId)
	 {
		 tweetService.deleteTweet(tweetId);
		
	 }
	 

}
