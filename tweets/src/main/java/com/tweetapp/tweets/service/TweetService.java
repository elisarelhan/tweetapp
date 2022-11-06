package com.tweetapp.tweets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.tweetapp.tweets.entity.Tweet;
import com.tweetapp.tweets.repository.TweetRepo;

@Service
public class TweetService {
	

	@Autowired
	private TweetRepo tweetRepo;
	
	public Tweet saveTweet(Tweet tweet)
	{
		return tweetRepo.save(tweet);
	}
	
	public Page<Tweet> getAllTweets(Pageable pageable)
	{
		return tweetRepo.findAll(pageable);
	}
	
	public List<Tweet> getTweetsByUser(String email)
	{
		return tweetRepo.findByUserEmail(email);
	}
	
	public Tweet updateTweet(int tweetId,String tweetContent)
	{
		Tweet tweet = tweetRepo.findByTweetId(tweetId);
		
	    tweet.setTweetContent(tweetContent);
	    return tweetRepo.save(tweet);
		
	}
	public Tweet updateTweetLikes(int tweetId,int likes)
	{
		Tweet tweet = tweetRepo.findByTweetId(tweetId);
	    tweet.setLikes(likes);
	    return tweetRepo.save(tweet);
	}
	public void  deleteTweet(int tweetId)
	{
		tweetRepo.deleteById(tweetId);
		
		 
	}
	

	
	
}
