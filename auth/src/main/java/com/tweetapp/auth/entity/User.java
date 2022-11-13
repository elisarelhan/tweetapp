package com.tweetapp.auth.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


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
	@Column
	@NotBlank(message="First name is mandatory")
	@Getter
	@Setter
	private String firstName;
	@Column
	@Getter
	@Setter
	private String lastName;
	@Column
	@NotBlank(message="Gender is mandatory")
	@Getter
	@Setter
	private String gender;
	@Column
	@Getter
	@Setter
	private Date dob;
	@Column
	@Pattern(regexp = "^((\\+91)?|91)?[6789][0-9]{9}",message="Contact no should be 10 digits")
	@Getter
	@Setter
	private String contactNo;
	@Column(unique=true)
	@NotBlank(message="Email is mandatory")
	@Email(message="Please enter a valid email address")
	@Getter
	@Setter
	private String email;
	@Column
	@NotBlank(message="Password is mandatory")
	@Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message="Password should containe minimum 8 characters, at least one uppercase letter, one lowercase letter, one number and one special character")
	@Getter
	@Setter
	private String password;

}
