package com.airsystem.sample.api.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Entity
@Table(name = "tbl_users")
public class Users implements Serializable {
	private static final long serialVersionUID = 6683990570681720292L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@OneToOne(mappedBy = "users")
	@JsonManagedReference
	private Roles roles;

	public Users() {
	}

	public Users(Long pId, String pUsername, String pPassword, Roles pRoles) {
		id = pId;
		username = pUsername;
		password = pPassword;
		roles = pRoles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long pId) {
		id = pId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String pUsername) {
		username = pUsername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pPassword) {
		password = pPassword;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles pRoles) {
		roles = pRoles;
	}
}