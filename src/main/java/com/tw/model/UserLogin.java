package com.tw.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name ="userLogin")
public class UserLogin {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	
	private String username;
	
	 private String password;
	 
	 @ManyToMany(fetch = FetchType.LAZY)
	  @JoinTable(  name = "userLogin_roleLogin", 
	        joinColumns = @JoinColumn(name = "userLogin_id"), 
	        inverseJoinColumns = @JoinColumn(name = "roleLogin_id"))
	  private Set<RoleLogin> roles = new HashSet<>();

	 public UserLogin(String username,  String password) {
		    this.username = username;
		    this.password = password;
		  }

}
