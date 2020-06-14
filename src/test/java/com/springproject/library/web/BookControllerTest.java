package com.springproject.library.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.jni.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import com.springproject.library.data.BookRepository;
import com.springproject.library.data.UserRepository;
import com.springproject.library.model.Book;
import com.springproject.library.model.User;
import com.springproject.library.security.SecurityConfig;
import com.springproject.library.service.BookService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@ContextConfiguration(classes= {BookController.class, UserDetailsService.class, BookService.class, SecurityConfig.class})
public class BookControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	@MockBean
	private UserDetailsService userService;
	
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
	public void testBooksPage() throws Exception{
		when(bookService.getAll()).thenReturn(this.books);
		mockMvc.perform(get("/books")).andExpect(status().isOk()).andExpect(view().name("index")).andExpect(model().attribute("books", books));
	}
	
	@Test
	@WithMockUser(username = "testUser", password = "testPassword")
	public void testGetBookReturnsBook() throws Exception{
		Book testBook = new Book();
		testBook.setId(1L);
		
		when(bookService.get(1L)).thenReturn(Optional.of(testBook));
		mockMvc.perform(get("/book/1")).andExpect(status().isOk()).andExpect(view().name("book")).andExpect(model().attribute("book", testBook));
	}
	

	@Test
	@WithMockUser(username = "testUser", password = "testPassword")
	public void testGetBookReturnsPageNotFoundWhenBookDoesNotExist() throws Exception{
		when(bookService.get(1L)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "unable to find resource"));
		mockMvc.perform(get("/book/1")).andExpect(status().isNotFound());
	}
}
