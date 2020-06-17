package com.springproject.library.service;

import java.util.List;

import com.springproject.library.model.BookWithdraw;
import com.springproject.library.model.User;


public interface BookWithdrawService {
		public BookWithdraw create(BookWithdraw bookwithDraw);
		public List<BookWithdraw> getAll();
		public List<BookWithdraw> getByUser(User user);
		public boolean delete(Long id);
}
