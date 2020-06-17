package com.springproject.library.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.springproject.library.model.BookWithdraw;
import com.springproject.library.security.SecurityConfig;
import com.springproject.library.service.BookWithdrawService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@ContextConfiguration(classes = { BookWithdrawController.class, BookWithdrawService.class, SecurityConfig.class })
public class BookWithdrawControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private BookWithdrawService bookWithdrawService;
	List<BookWithdraw> bookWithdraws;

	@Before
	public void setup() {
		bookWithdraws = new ArrayList();

	}

	@Test
	public void testGetBookWithdrawsReturnsCorrectData() throws Exception {
		when(bookWithdrawService.getAll()).thenReturn(this.bookWithdraws);
		mockMvc.perform(get("/borrowed")).andExpect(status().isOk()).andExpect(view().name("borrowed"))
				.andExpect(model().attribute("bookWithdraws", bookWithdraws));
	}

}
