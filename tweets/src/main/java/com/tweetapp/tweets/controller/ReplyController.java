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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.tweets.entity.Reply;
import com.tweetapp.tweets.exception.ResourceNotFoundException;
import com.tweetapp.tweets.repository.ReplyRepo;
import com.tweetapp.tweets.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class ReplyController {
	@Autowired
	private ReplyService replyService;

	@Autowired
	private ReplyRepo replyRepo;

	@GetMapping("/{tweetId}/replies")
	public ResponseEntity<?> getAllCommentsByPostId(
			@RequestHeader(name = "Authorization", required = true) String token,
			@Valid @PathVariable(value = "tweetId") int tweetId) {

		try {
			return ResponseEntity.ok(replyService.getAllRepliesByTweetId(tweetId));
		} catch (ResourceNotFoundException e) {
			log.warn(e.getMessage());
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/{tweetId}/postReply")
	public Reply createComment(@RequestHeader(name = "Authorization", required = true) String token,
			@Valid @PathVariable(value = "tweetId") int tweetId,@Valid  @RequestBody Reply reply) {

		return replyService.saveReply(tweetId, reply);

	}

	@DeleteMapping("deleteReply/{replyId}")
	public ResponseEntity<?> deleteReply(@RequestHeader(name = "Authorization", required = true) String token,
			@Valid @PathVariable int replyId) {
		replyService.deleteReply(replyId);
		return new ResponseEntity(1, HttpStatus.OK);
	}

}
