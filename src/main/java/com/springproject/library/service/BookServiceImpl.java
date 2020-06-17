package com.springproject.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springproject.library.data.BookRepository;
import com.springproject.library.model.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Iterable<Book> getAll() {
		return bookRepository.findAll();
	}

	@Override
	public Optional<Book> get(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		System.out.print("in hter");
		if (book.isEmpty()) {
			System.out.print("in hter");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "unable to find resource");
		}
		return book;
	}

	@Override
	public Book create(Book book) {
		return bookRepository.save(book);
	}

}
