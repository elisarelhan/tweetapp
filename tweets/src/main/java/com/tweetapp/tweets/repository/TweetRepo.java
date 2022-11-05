package com.tweetapp.tweets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tweetapp.tweets.entity.Tweet;



public interface TweetRepo  extends JpaRepository<Tweet, Integer> {
    List<Tweet> findByUserEmail(String userEmail);
     List<Tweet> findAll();
     Tweet findByTweetId(int tweetId);
     
     
     
     
   
}

