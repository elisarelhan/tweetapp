package com.tweetapp.tweets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tweetapp.tweets.entity.Reply;
import com.tweetapp.tweets.entity.Tweet;
import com.tweetapp.tweets.exception.ResourceNotFoundException;
import com.tweetapp.tweets.repository.ReplyRepo;
import com.tweetapp.tweets.repository.TweetRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReplyService {
	@Autowired
	private ReplyRepo replyRepo;
	@Autowired
	private TweetRepo tweetRepo;

	private static final String USER_CREATED_TOPIC = "tweet";
	@Autowired
	private KafkaTemplate<String, Reply> kafkaTemplate;

	@Autowired
	private KafkaTemplate<String, String> templateString;

	public Reply saveReply(int tweetId, Reply reply) {
		Tweet tweet = tweetRepo.findByTweetId(tweetId);
		reply.setTweet(tweet);
		Reply replyObj = replyRepo.save(reply);
		templateString.send(USER_CREATED_TOPIC, "Reply posted");
		return replyObj;

//    }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
	}

	public List<Reply> getAllRepliesByTweetId(int tweetId) {
		if (replyRepo.findByTweetId(tweetId).isEmpty())
			throw new ResourceNotFoundException("Tweet not found");
		else
			return replyRepo.findByTweetId(tweetId);

	}

	public void deleteReply(int replyId) {

		replyRepo.deleteById(replyId);
		templateString.send(USER_CREATED_TOPIC, "Deleted");

	}

}
