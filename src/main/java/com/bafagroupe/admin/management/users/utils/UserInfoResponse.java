package com.bafagroupe.admin.management.users.utils;

import com.bafagroupe.admin.management.users.model.AppUserRole;

import java.util.List;

public class UserInfoResponse {
	private Integer id;
	private String email;
	private List<AppUserRole> roles;

	private String token;

	private String msg;

	public UserInfoResponse(Integer id,String email, List<AppUserRole> roles,String token,String message) {
		this.id = id;
		this.email = email;
		this.roles = roles;
		this.token=token;
		this.msg=message;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public List<AppUserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AppUserRole> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
