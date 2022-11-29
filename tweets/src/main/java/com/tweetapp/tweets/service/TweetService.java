package com.tweetapp.tweets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.tweets.entity.Tweet;
import com.tweetapp.tweets.exception.ResourceNotFoundException;
import com.tweetapp.tweets.repository.TweetRepo;

@Service
public class TweetService {

	@Autowired
	private TweetRepo tweetRepo;

	public Tweet saveTweet(Tweet tweet) {
		return tweetRepo.save(tweet);
	}
	public Tweet getTweet(int tweetId) {
		if( tweetRepo.findByTweetIdOrderByUpdatedDateDesc(tweetId)==null) {
			throw new ResourceNotFoundException("Tweet Not Found");
		} else {
			return tweetRepo.findByTweetIdOrderByUpdatedDateDesc(tweetId);
		}
	}

	public List<Tweet> getAllTweets() {

		if (tweetRepo.findAllTweetsOrderByUpdatedDateDesc().isEmpty()) {
			throw new ResourceNotFoundException("No Tweets Found");
		} else {
			return tweetRepo.findAllTweetsOrderByUpdatedDateDesc();
		}
	}

	public List<Tweet> getTweetsByUser(String email) {

		if (tweetRepo.findByUserEmailOrderByUpdatedDateDesc(email).isEmpty())
			throw new ResourceNotFoundException("No Tweets Found");
		else
			return tweetRepo.findByUserEmailOrderByUpdatedDateDesc(email);
	}

	public Tweet updateTweet(int tweetId, String tweetContent) {

		Tweet tweet = tweetRepo.findByTweetIdOrderByUpdatedDateDesc(tweetId);

		tweet.setTweetContent(tweetContent);
		return tweetRepo.save(tweet);

	}

	public Tweet updateTweetLikes(int tweetId, int likes) {
		Tweet tweet = tweetRepo.findByTweetIdOrderByUpdatedDateDesc(tweetId);
		tweet.setLikes(likes);
		return tweetRepo.save(tweet);
	}

	public void deleteTweet(int tweetId) {
		tweetRepo.deleteById(tweetId);

	}

}
