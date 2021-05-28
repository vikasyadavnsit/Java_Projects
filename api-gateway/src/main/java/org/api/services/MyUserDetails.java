package org.api.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.api.wrapper.generic.UserWrapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private String active;
	private List<GrantedAuthority> authorities;

	public MyUserDetails() {
	}

	public MyUserDetails(UserWrapper user) {
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.active = user.getActive();
		this.authorities = user.getAuthorities().stream().map(x -> x.getRole()).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getAuthorities();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		if ("Y".equalsIgnoreCase(this.active)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		if ("Y".equalsIgnoreCase(this.active)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		if ("Y".equalsIgnoreCase(this.active)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isEnabled() {
		if ("Y".equalsIgnoreCase(this.active)) {
			return true;
		}
		return false;
	}

}
