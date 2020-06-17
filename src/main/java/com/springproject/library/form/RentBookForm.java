package com.springproject.library.form;

import com.springproject.library.model.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentBookForm {
	private Book book;
	private String user;
	private int days;
}
