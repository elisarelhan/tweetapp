package com.tweetapp.tweets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.tweets.entity.Reply;
import com.tweetapp.tweets.entity.Tweet;
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
	
	
	public Reply saveReply(Reply reply)
	{Tweet tweet=tweetRepo.findByTweetId(reply.getTweet().getTweetId());
	
		tweet.getReplies().add(reply);
		return replyRepo.save(reply);
	}
	public List<Reply> getAllRepliesByTweetId(int tweetId)
	{
		Tweet tweet=tweetRepo.findByTweetId(tweetId);
		List<Reply> replies=tweet.getReplies();
		return replies;
	}

}
