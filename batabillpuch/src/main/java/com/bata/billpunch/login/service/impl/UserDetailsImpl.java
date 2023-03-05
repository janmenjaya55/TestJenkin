package com.bata.billpunch.login.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bata.billpunch.login.model.UserModel;

public class UserDetailsImpl implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String password;
	
	private String userrole;
	
	private String fullname;
	
	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getUserrole() {
		return userrole;
	}
	
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public static UserDetailsImpl build(UserModel user) {
		List<GrantedAuthority> authorities =null;

		return new UserDetailsImpl(
				user.getUsername(), 
				user.getPassword(),
				user.getUserrole(),
				user.getFullname(),
				authorities
				);
	}

	
	public UserDetailsImpl(String username, String password, String userrole, String fullname,
			Collection<? extends GrantedAuthority> authorities) {
		
		this.username = username;
		this.userrole = userrole;
		this.password = password;
		this.fullname=fullname;
		this.authorities = authorities;
	}
}
