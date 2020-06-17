package com.springproject.library.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springproject.library.model.BookWithdraw;
import com.springproject.library.model.User;

@Repository
public interface BookWithdrawRepository extends CrudRepository<BookWithdraw, Long>{
	List<BookWithdraw> findByUser(User user);
	
}
