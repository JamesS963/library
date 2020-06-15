package com.springproject.library.form;

import java.io.IOException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.springproject.library.model.Book;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class BookForm {
	
	@NotBlank(message="isbn cannot be null")
	private String isbn;
	@NotBlank(message="title cannot be null")
	private String title;
	@NotBlank(message="author cannot be null")
	private String author;
	private String description;
	
	private int size;
	private int stock;
	@NotNull(message="please enter an image")
	private MultipartFile cover;
	
	public Book toBook() throws IOException {
		return new Book(isbn, title, author, description, size, stock, cover.getBytes() );
	}
	
	
}
