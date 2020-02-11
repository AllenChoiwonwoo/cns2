package com.myspring.cns.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

@Component("MemberVOwithIsFollow")
public class MemberVOwithIsFollow {
	
	private int id=0;
	private String username=null;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String password=null;
//	private String created_at;
//	private String ;
	private Date created_at=null;
//	@JsonInclude(JsonInclude.Include.)
	private boolean isFollow;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
//		return super.toString();
		String info = id+", "+username+", "+password+", "+created_at+", "+isFollow;
		return info;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date date) {
		this.created_at = date;
	}

	public boolean isFollow() {
		return isFollow;
	}

	public void setFollow(boolean isFollow) {
		this.isFollow = isFollow;
	}
	
	

}
