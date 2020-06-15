package com.springproject.library.model;

import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String isbn;
	private String title;
	private String author;
	@Column(length = 2000)
	private String description;
	private int size;
	private int stock;

	@Lob
	private byte[] cover;

	public Book(String isbn, String title, String author, String description, int size, int stock, byte[] cover) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.description = description;
		this.size = size;
		this.stock = stock;
		this.cover = cover;
	}
	
	public String coverToBaseSixtyFour() {
		return Base64.getEncoder().encodeToString(this.cover);
	}
	
	

}
