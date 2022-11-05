package com.tweetapp.tweets.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.tweets.entity.Reply;
import com.tweetapp.tweets.entity.Tweet;
import com.tweetapp.tweets.service.ReplyService;
import com.tweetapp.tweets.service.TweetService;

@RestController
public class ReplyController {
	 @Autowired 
	 private ReplyService replyService;
	 
	 @PostMapping("/postReply")
	 public ResponseEntity<?> postReply(@RequestBody Reply reply)
	 {
		 
		 return ResponseEntity.ok(replyService.saveReply(reply));
	 }
	  
	 @GetMapping("/getAllReplies/{tweetId}")
	 public ResponseEntity<?> getAllRepliesByTweet(@PathVariable int tweetId)
	 {
		 
		 return ResponseEntity.ok(replyService.getAllRepliesByTweetId(tweetId));
		 
	 }

}
