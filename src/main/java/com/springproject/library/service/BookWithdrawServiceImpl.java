package com.springproject.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.library.data.BookWithdrawRepository;
import com.springproject.library.model.BookWithdraw;
import com.springproject.library.model.User;

@Service
public class BookWithdrawServiceImpl implements BookWithdrawService {

	private BookWithdrawRepository bookWithdrawRepository;

	@Autowired
	public BookWithdrawServiceImpl(BookWithdrawRepository bookWithdrawRepository) {

		this.bookWithdrawRepository = bookWithdrawRepository;
	}

	@Override
	public BookWithdraw create(BookWithdraw bookWithdraw) {
		return bookWithdrawRepository.save(bookWithdraw);
	}

	@Override
	public List<BookWithdraw> getAll() {
		return (List<BookWithdraw>) bookWithdrawRepository.findAll();
	}

	public List<BookWithdraw> getByUser(User user){
		return bookWithdrawRepository.findByUser(user);
	}
	
	@Override
	public boolean delete(Long id) {
		bookWithdrawRepository.deleteById(id);
		return true;
	}

}
