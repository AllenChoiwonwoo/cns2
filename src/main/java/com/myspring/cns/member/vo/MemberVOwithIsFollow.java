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
	private Date createdAt=null;
//	@JsonInclude(JsonInclude.Include.)
	private boolean isFollow = true;
	

	
	@Override
	public String toString() {
		return "MemberVOwithIsFollow [id=" + id + ", username=" + username + ", password=" + password + ", createdAt="
				+ createdAt + ", isFollow=" + isFollow + "]";
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
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date date) {
		this.createdAt = date;
	}

	public boolean getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(boolean isFollow) {
		this.isFollow = isFollow;
	}
	
	

}
