package com.tweetapp.tweets.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
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
	@Email(message="Please enter a valid email address")
//	@NotBlank(message="Email is mandatory")
	@Getter
	@Setter
	private String userEmail;
	@Column(length=144)
	@Size(max=144, message="Not more than 144 characters")
	@Getter
	@Setter
	private String tweetContent;
	@Column
	@NotNull
	@Getter
	@Setter
	private int likes;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	@Getter
	@Setter
	private Date creationDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	@Getter
	@Setter
	private Date updatedDate;
	
	@OneToMany(mappedBy = "tweet")
	@Getter
	private List<Reply> reply;
	

	

}
