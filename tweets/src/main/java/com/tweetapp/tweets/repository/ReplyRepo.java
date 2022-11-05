package com.tweetapp.tweets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweetapp.tweets.entity.Reply;
import com.tweetapp.tweets.entity.Tweet;



public interface ReplyRepo  extends JpaRepository<Reply, Integer> {
 
   
}

