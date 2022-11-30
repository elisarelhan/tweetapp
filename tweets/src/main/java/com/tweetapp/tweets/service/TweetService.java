package com.tweetapp.tweets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tweetapp.tweets.entity.Tweet;
import com.tweetapp.tweets.exception.ResourceNotFoundException;
import com.tweetapp.tweets.repository.TweetRepo;

@Service
public class TweetService {

	@Autowired
	private TweetRepo tweetRepo;

	private static final String USER_CREATED_TOPIC = "tweet";
	@Autowired
	private KafkaTemplate<String, Tweet> kafkaTemplate;
	@Autowired
	private KafkaTemplate<String, List<Tweet>> template;
	@Autowired
	private KafkaTemplate<String, String> templateString;

	public Tweet saveTweet(Tweet tweet) {
		Tweet tweetObj = tweetRepo.save(tweet);
		templateString.send(USER_CREATED_TOPIC, "Tweet posted");
		return tweetObj;
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

			return tweetRepo.findAll();


	public Tweet getTweet(int tweetId) {
		if (tweetRepo.findByTweetIdOrderByUpdatedDateDesc(tweetId) == null) {
			templateString.send(USER_CREATED_TOPIC, "Tweet Not Found");
			throw new ResourceNotFoundException("Tweet Not Found");

		} else {
			Tweet tweetObj = tweetRepo.findByTweetIdOrderByUpdatedDateDesc(tweetId);
			kafkaTemplate.send(USER_CREATED_TOPIC, tweetObj);
			return tweetObj;
		}
	}

	public List<Tweet> getAllTweets() {

		if (tweetRepo.findAllTweetsOrderByUpdatedDateDesc().isEmpty()) {
			templateString.send(USER_CREATED_TOPIC, "No Tweets Found");
			throw new ResourceNotFoundException("No Tweets Found");
		} else {
			List<Tweet> tweetObj = tweetRepo.findAllTweetsOrderByUpdatedDateDesc();
			template.send(USER_CREATED_TOPIC, tweetObj);
			return tweetObj;

		}
	}

	public List<Tweet> getTweetsByUser(String email) {


	
		if (tweetRepo.findByUserEmailOrderByUpdatedDateDesc(email).isEmpty()) {
			templateString.send(USER_CREATED_TOPIC, "No Tweets Found");
			throw new ResourceNotFoundException("No Tweets Found");
		} else {
			List<Tweet> tweetObj = tweetRepo.findByUserEmailOrderByUpdatedDateDesc(email);
			template.send(USER_CREATED_TOPIC, tweetObj);
			return tweetObj;

		}


	public Tweet updateTweet(int tweetId, String tweetContent) {

		Tweet tweet = tweetRepo.findByTweetIdOrderByUpdatedDateDesc(tweetId);

		tweet.setTweetContent(tweetContent);
		Tweet tweetObj = tweetRepo.save(tweet);
		templateString.send(USER_CREATED_TOPIC, "Tweet Updated");
		return tweetObj;

	}

	public Tweet updateTweetLikes(int tweetId, int likes) {
		Tweet tweet = tweetRepo.findByTweetIdOrderByUpdatedDateDesc(tweetId);
		tweet.setLikes(likes);

		Tweet tweetObj = tweetRepo.save(tweet);
		templateString.send(USER_CREATED_TOPIC, "Likes updated");
		return tweetObj;
	}

	public void deleteTweet(int tweetId) {
		tweetRepo.deleteById(tweetId);
		templateString.send(USER_CREATED_TOPIC, "Deleted");

	}

}
