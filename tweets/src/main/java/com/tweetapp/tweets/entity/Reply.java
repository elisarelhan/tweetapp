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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
//	  @Temporal(TemporalType.TIMESTAMP)
//	    @Column(name = "created_at", nullable = false, updatable = false)
//	    @CreatedDate
//	    private Date createdAt;
//
//	    @Temporal(TemporalType.TIMESTAMP)
//	    @Column(name = "updated_at", nullable = false)
//	    @LastModifiedDate
//	    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tweet_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	@Getter
	@Setter
	private Tweet tweet;

}