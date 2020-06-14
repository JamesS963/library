package com.springproject.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Entity
@Data
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String isbn;
	private String title;
	private String author;
	@Column(length=2000)
	private String description;
	private int size;
	private int stock;

	@Lob
	private byte[] cover;

}
