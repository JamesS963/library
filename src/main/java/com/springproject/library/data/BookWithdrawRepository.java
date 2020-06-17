package com.springproject.library.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springproject.library.model.BookWithdraw;

@Repository
public interface BookWithdrawRepository extends CrudRepository<BookWithdraw, Long>{
	
}
