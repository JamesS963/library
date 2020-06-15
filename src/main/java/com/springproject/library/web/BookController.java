package com.springproject.library.web;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.library.form.BookForm;
import com.springproject.library.model.Book;
import com.springproject.library.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
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

	@GetMapping("book/create")
	public ModelAndView createBook() {
		return new ModelAndView("createBook", "book", new BookForm());
	}

	@PostMapping("/book/create")
	public String postCreateBook(@Valid @ModelAttribute("book") BookForm book, Errors errors) {
		log.info("book is" + book.toString());

		if (errors.hasErrors()) {
			return "createBook";
		}

		try {
			bookService.create(book.toBook());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/books";
	}
}
