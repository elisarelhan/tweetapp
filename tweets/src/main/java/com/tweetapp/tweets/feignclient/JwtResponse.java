package com.tweetapp.tweets.feignclient;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	@Getter
	private String jwttoken;
	@Getter
	private Long expiresIn;

//	public JwtResponse(String jwttoken, Long expiresIn) {
//		super();
//		this.jwttoken = jwttoken;
//		this.expiresIn = expiresIn;
//	}
//
//	public Long getExpiresIn() {
//		return expiresIn;
//	}
//
//
//	public String getToken() {
//		return this.jwttoken;
//	}
}