package com.myspring.cns.member.vo;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component("TokenVO2")
public class TokenVO2 {
	
	private String username;
	private String password;
	
	public TokenVO2() {
		super();
	}
	
	public TokenVO2(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		return " "+username+" , "+password;
	}
	
}
