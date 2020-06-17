package com.springproject.library.service;

import java.util.List;

import com.springproject.library.model.BookWithdraw;


public interface BookWithdrawService {
		public BookWithdraw create(BookWithdraw bookwithDraw);
		public List<BookWithdraw> getAll();
}
