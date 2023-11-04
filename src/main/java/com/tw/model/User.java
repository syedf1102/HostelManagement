package com.tw.model;

import java.util.List;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tw.enums.StatusType;
import com.tw.generics.AbstractPersistable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "user")
@Where(clause = "deleted=false")
public class User extends AbstractPersistable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3564476886515592353L;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "mobile", nullable = false)
	private String mobile;

	@Column(name = "status", nullable = true)
	private String status = StatusType.Active.getValue();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	@JsonIgnore
	private List<Role> roles;

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
