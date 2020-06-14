package com.springproject.library.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.library.model.Book;
import com.springproject.library.service.BookService;

@Controller
public class BookController {
	@Autowired
	BookService bookService;

	@GetMapping("/books")
	public ModelAndView getBooks() {

		Iterable<Book> books = bookService.getAll();
		
		return new ModelAndView("index", "books", books);
	}

	@GetMapping("/book/{id}")
	public ModelAndView getBook(@PathVariable Long id) {
		
		Optional<Book> book = bookService.get(id);
		
		return new ModelAndView("book", "book", book.get());
	}
}
