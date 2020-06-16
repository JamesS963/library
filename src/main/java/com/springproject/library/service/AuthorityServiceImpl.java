package com.springproject.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.library.data.AuthorityRepository;
import com.springproject.library.model.Authority;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	AuthorityRepository authorityRepository;

	@Override
	public Iterable<Authority> getAuthorities() {
		return authorityRepository.findAll();
	}

}
