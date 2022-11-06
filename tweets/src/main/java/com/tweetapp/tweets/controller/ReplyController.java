package com.tweetapp.tweets.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.tweets.entity.Reply;
import com.tweetapp.tweets.entity.Tweet;
import com.tweetapp.tweets.service.ReplyService;
import com.tweetapp.tweets.service.TweetService;
import com.tweetapp.tweets.repository.ReplyRepo;
import com.tweetapp.tweets.repository.TweetRepo;

@RestController
public class ReplyController {
	 @Autowired 
	 private ReplyService replyService;
	 
	 @Autowired
	 private  ReplyRepo replyRepo;
	 
	 @GetMapping("/{tweetId}/replies")
     public List<Reply> getAllCommentsByPostId(@RequestHeader(name = "Authorization", required = true) String token,@PathVariable (value = "tweetId") int tweetId) {
         return replyService.getAllRepliesByTweetId(tweetId);
     }

     @PostMapping("/{tweetId}/postReply")
     public Reply createComment(@RequestHeader(name = "Authorization", required = true) String token,@PathVariable (value = "tweetId") int  tweetId,
                                   @RequestBody Reply reply) {
    	 
    	 return replyService.saveReply(tweetId,reply);
//         return replyRepo.findById(tweetId).map(tweet -> {
//             reply.setTweet(tweet);
//             return commentRepository.save(comment);
//         }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
     }

     @DeleteMapping("deleteReply/{replyId}")
     public ResponseEntity<?> deleteReply(@RequestHeader(name = "Authorization", required = true) String token,@PathVariable int  replyId) {
    	 replyService.deleteReply(replyId);
         return new ResponseEntity("Deleted",HttpStatus.OK);
     }
 
	 
	 


}
