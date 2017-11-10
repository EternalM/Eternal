package com.devsun.eternal.entity.user;

import com.devsun.eternal.model.user.User;

public class AdminTicket {

	public AdminTicket(User user){
		if(user==null){
			return;
		}
		this.id = user.getId();
		this.username = user.getUsername();
		this.realname = user.getRealname();
		this.email = user.getEmail();
	}
	
	private int id;
	
	private String username;
	
	private String realname;
	
	private String email;

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

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
