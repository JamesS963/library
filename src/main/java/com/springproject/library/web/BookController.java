package com.springproject.library.web;

import java.io.IOException;
import java.time.LocalDate;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.library.form.BookForm;
import com.springproject.library.form.RentBookForm;
import com.springproject.library.model.Book;
import com.springproject.library.model.BookWithdraw;
import com.springproject.library.model.User;
import com.springproject.library.service.BookService;
import com.springproject.library.service.BookWithdrawService;
import com.springproject.library.service.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BookController {

	private BookService bookService;
	private UserDetailsServiceImpl userService;
	private BookWithdrawService bookWithdrawService;

	@Autowired
	public BookController(BookService bookService, UserDetailsServiceImpl userService,
			BookWithdrawService bookWithdrawService) {
		this.bookService = bookService;
		this.userService = userService;
		this.bookWithdrawService = bookWithdrawService;
	}

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

	@GetMapping("/book/rent/{id}")
	public ModelAndView rentBook(@PathVariable long id) {
		Optional<Book> book = bookService.get(id);
		RentBookForm bookForm = new RentBookForm();
		bookForm.setBook(book.get());

		return new ModelAndView("rentBook").addObject("bookForm", bookForm);

	}

	@PostMapping("/book/rent")
	public String rentBook(@Valid @ModelAttribute("bookForm") RentBookForm bookForm, Errors errors, Model model) {
		log.info("book form is " + bookForm.toString());
		User user = (User) userService.loadUserByUsername(bookForm.getUser());
		BookWithdraw bookWithdraw = new BookWithdraw();
		bookWithdraw.setUser(user);

		
		Optional<Book> book = bookService.get(bookForm.getBook().getBookId());
		bookWithdraw.setBook(book.get());

		LocalDate rentDate = LocalDate.now();
		bookWithdraw.setRentDate(rentDate);

		LocalDate dueDate = LocalDate.now().plusDays(bookForm.getDays());
		bookWithdraw.setDueDate(dueDate);

		// log.info("bookwithdraw is " + bookWithdraw.toString());
		bookWithdrawService.create(bookWithdraw);
		return "index";
	}

}
