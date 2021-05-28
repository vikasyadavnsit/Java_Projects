package org.api.wrapper.generic;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserWrapper implements Serializable {

	private static final long serialVersionUID = -1039655267186118366L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_name", unique = true, nullable = false, length = 50)
	private String userName;

	@Column(nullable = false, length = 50)
	private String password;

	@Column(nullable = false, length = 1)
	private String active;

	@Column(name = "create_on", nullable = false)
	private Timestamp createdOn;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_name")
	private List<UserRolesWrapper> authorities;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public List<UserRolesWrapper> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<UserRolesWrapper> authorities) {
		this.authorities = authorities;
	}

}
