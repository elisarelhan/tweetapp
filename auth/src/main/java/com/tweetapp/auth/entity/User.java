package com.tweetapp.auth.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column
	@Getter
	@Setter
	private int userId;
	@Column(nullable=false)
	@Getter
	@Setter
	private String firstName;
	@Column
	@Getter
	@Setter
	private String lastName;
	@Column(nullable=false)
	@Getter
	@Setter
	private String gender;
	@Column
	@Getter
	@Setter
	private Date dob;
	@Column
	@Getter
	@Setter
	private long contactNo;
	@Column(unique=true,nullable=false)
	@Getter
	@Setter
	private String email;
	@Column(nullable=false)
	@Getter
	@Setter
	private String password;

}
