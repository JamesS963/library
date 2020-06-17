package com.springproject.library.web;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.springproject.library.form.BookForm;
import com.springproject.library.form.RentBookForm;
import com.springproject.library.model.Book;
import com.springproject.library.model.User;
import com.springproject.library.security.SecurityConfig;
import com.springproject.library.service.BookService;
import com.springproject.library.service.BookWithdrawService;
import com.springproject.library.service.UserDetailsServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@ContextConfiguration(classes = { BookWithdrawService.class, BookController.class, UserDetailsServiceImpl.class,
		BookService.class, SecurityConfig.class })
public class BookControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@MockBean
	private BookWithdrawService bookWithdrawService;
	@MockBean
	private UserDetailsServiceImpl userService;

	private List<Book> books;

	@Before
	public void setup() {
		when(userService.loadUserByUsername("testUser")).thenReturn(new User("TestUser", "testPassword"));
		books = new ArrayList<Book>();
		books.add(new Book());
		books.add(new Book());
	}

	@Test
	@WithMockUser(username = "testUser", password = "testPassword")
	public void testBooksPage() throws Exception {
		when(bookService.getAll()).thenReturn(this.books);
		mockMvc.perform(get("/books")).andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(model().attribute("books", books));
	}

	@Test
	@WithMockUser(username = "testUser", password = "testPassword")
	public void testGetBookReturnsBook() throws Exception {
		Book testBook = new Book();
		testBook.setBookId(1L);

		when(bookService.get(1L)).thenReturn(Optional.of(testBook));
		mockMvc.perform(get("/book/1")).andExpect(status().isOk()).andExpect(view().name("book"))
				.andExpect(model().attribute("book", testBook));
	}

	@Test
	@WithMockUser(username = "testUser", password = "testPassword")
	public void testGetBookReturnsPageNotFoundWhenBookDoesNotExist() throws Exception {
		when(bookService.get(1L))
				.thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "unable to find resource"));
		mockMvc.perform(get("/book/1")).andExpect(status().isNotFound());
	}

	@Test
	@WithMockUser(username = "testUser", password = "testPassword")
	public void testCreateBookPageIsReturnedWithFormObject() throws Exception {
		mockMvc.perform(get("/book/create")).andExpect(status().isOk()).andExpect(view().name("createBook"))
				.andExpect(model().attribute("book", new BookForm()));
	}

	@Test
	public void testRentBookReturnsPageWhenBookExists() throws Exception {
		when(bookService.get(1L)).thenReturn(Optional.of(new Book()));
		mockMvc.perform(get("/book/rent/1")).andExpect(status().isOk()).andExpect(view().name("rentBook"));

	}

	@Test
	public void testRentBookPostCreatesArentedBook() throws Exception {
		
		User user = new User("username","password");
		user.setUserId(1L);
		
		byte[] cover = new byte[1];
		cover[0]='i';
		Book book = new Book(1L,"testIsbn","testTitle","testAuthor","testDesctiption",225,2,cover);
		int rentDays=12;
		
		RentBookForm rentBookForm = new RentBookForm(book,"username",rentDays);
		
		when(userService.loadUserByUsername("username")).thenReturn(user);
		when(bookService.get(1L)).thenReturn(Optional.of(book));
		
		mockMvc.perform(post("/book/rent").flashAttr("bookForm", rentBookForm).with(csrf())).andExpect(status().isOk());
		
	}

	@Test
	public void testCreateBookPostRedirectsWhenDataIsPost() throws Exception {
		MultipartFile cover = new MockMultipartFile("data", "other-file-name.data", "text/plain",
				"some other type".getBytes());
		BookForm book = new BookForm();
		book.setCover(cover);
		mockMvc.perform(post("/book/create").flashAttr("book", book).with(csrf())).andExpect(status().isOk());
	}

}
