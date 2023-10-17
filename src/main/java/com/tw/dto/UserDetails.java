package com.tw.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

	private Long id;

	private String username;

	private String email;

	private String password;
	
    private String roles;

	private String mobileNo;

	private double salary;

	private Date createdOn;

	private Date lastModifiedTime;

	private int status;
}
