package com.tweetapp.tweets.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tweet_details")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tweet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@Getter
	@Setter
	private int tweetId;
	@Column
	@Getter
	@Setter
	private String userEmail;
	@Column
	@Getter
	@Setter
	private String tweetContent;
	@Column
	@Getter
	@Setter
	private int likes;
	@Column
	@Getter
	@Setter
	@CreationTimestamp
	
	private Date dateTime;
	@OneToMany(mappedBy="tweet")
	@Getter
	private List<Reply> replies=new ArrayList<>();
	
	public void addReply(Reply reply)
	{
		this.replies.add(reply);
	}
	public void removeReply(Reply reply)
	{
		this.replies.remove(reply);
	}

}
