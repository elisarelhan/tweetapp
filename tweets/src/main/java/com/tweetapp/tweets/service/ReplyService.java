package com.tweetapp.tweets.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	
	public Reply saveReply(int tweetId,Reply reply)
	{Tweet tweet= tweetRepo.findByTweetId(tweetId);
        reply.setTweet(tweet);
        return replyRepo.save(reply);
//    }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
	}
	public List<Reply> getAllRepliesByTweetId(int tweetId)
	{
		return  replyRepo.findByTweetId(tweetId);
	}
	public void deleteReply( int replyId) {
		
		 replyRepo.deleteById(replyId);
		
	}

}
