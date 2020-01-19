package org.api.wrapper;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserWrapper {

	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Id
	@Column(name = "user_name")
	private String userName;

	private String password;

	private String active;

	@Column(name = "create_on")
	private Timestamp createdOn;

	@OneToMany(mappedBy = "userName")
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
