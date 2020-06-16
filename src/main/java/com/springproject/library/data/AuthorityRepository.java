package com.springproject.library.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springproject.library.model.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	
}
