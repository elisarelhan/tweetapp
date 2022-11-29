package com.tweetapp.tweets.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tweetapp.tweets.entity.Reply;
import com.tweetapp.tweets.entity.Tweet;



public interface ReplyRepo  extends JpaRepository<Reply, Integer> {
	
	@Query(value="SELECT * FROM reply_details where tweet_id=?;",nativeQuery=true)
	List<Reply> findByTweetIdOrderByCreationDateDesc(int tweetId);
//    Optional<Reply> findByIdAndPostId(Long id, Long postId);

	
 
   
}

