package com.tweetapp.tweets.entity;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "reply_details")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@Getter
	@Setter
	private int replyId;
	@Column
	@Getter
	@Setter
	private String userEmail;
	@Column
	@Getter
	@Setter
	private String replyContent;
	@Column
	@CreationTimestamp
	@Getter
	@Setter
	
	private Date dateTime;
	@ManyToOne
	@Getter
	@Setter
	private Tweet tweet;

}