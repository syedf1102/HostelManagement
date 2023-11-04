package com.tw.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class SignupRequest {
	@NotBlank
	private String username;

	private List<String> role;

	@NotBlank
	private String password;

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

	public List<String> getRole() {
		return this.role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}
}
