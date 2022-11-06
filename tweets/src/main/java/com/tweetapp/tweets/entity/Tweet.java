package com.tweetapp.tweets.entity;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import javax.validation.constraints.Size;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "created_at", updatable = false)
//	@CreatedDate
//	@Getter
//	@Setter
//	private Date createdAt;
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "updated_at")
//	@LastModifiedDate
//	@Getter
//	@Setter
//	private Date updatedAt;
	

	

}
