package com.springproject.library.service;

import java.util.Optional;

import com.springproject.library.model.Book;

public interface BookService {
	public Iterable<Book> getAll();
	public Optional<Book> get(Long id);
}
