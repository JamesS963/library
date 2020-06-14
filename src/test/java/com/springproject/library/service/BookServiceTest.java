package com.springproject.library.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import com.springproject.library.data.BookRepository;
import com.springproject.library.model.Book;

@RunWith(SpringRunner.class)
public class BookServiceTest {
	@InjectMocks
	private BookServiceImpl bookService;

	@Mock
	BookRepository bookRepository;

	List<Book> books;

	@Before
	public void init() {
		books = new ArrayList<Book>();
		books.add(new Book());
		books.add(new Book());
		books.add(new Book());

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testBookListIsRetreived() throws Exception {
		when(bookRepository.findAll()).thenReturn(this.books);
		List<Book> allBooks = (ArrayList<Book>) bookService.getAll();
		assertEquals(allBooks.size(), 3);
	}

	@Test
	public void TestBookIsRetreivedWhenRetreiveById() throws Exception {
		Book book = new Book();
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		assertNotNull(bookService.get(1L).get());
	}

	@Test(expected = ResponseStatusException.class)
	public void testExceptionIsThrownWhenRetreiveNonExistentBook() throws Exception {
		when(bookRepository.findById(1L)).thenReturn(Optional.empty());
		bookService.get(1L);
	}

}
