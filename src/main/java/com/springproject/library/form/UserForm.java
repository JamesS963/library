package com.springproject.library.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.springproject.library.model.Authority;
import com.springproject.library.model.User;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class UserForm {
	private String username;
	private String password;
	private List<AuthorityForm> authorities;

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = new ArrayList<>();
		authorities.forEach(authority -> {
			log.info("authority is " + authority.toString());
			AuthorityForm authorityForm = new AuthorityForm(authority, false);
			this.authorities.add(authorityForm);
		});
	}

	public User toUser() {
		User user = new User();

		user.setUsername(username);
		user.setPassword(password);
		
		Set<Authority> authorities = new HashSet<Authority>();

		this.authorities.forEach(authority -> {
			if (authority.isChecked()) {
				log.info("adding authority");
				authorities.add(authority.getAuthority());
			}
		});
		user.setAuthorities(authorities);
		log.info("user is" + user.toString());
		return user;
	}
}
